package com.mobsys.easyStocks.service;

import com.mobsys.alphavantage.model.DailyDataResponse;
import com.mobsys.alphavantage.model.DataEntry;
import com.mobsys.easyStocks.alphavantage.AlphavantageApi;
import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.StockData;
import com.mobsys.easyStocks.persistence.repository.StockDataRepository;
import com.mobsys.easyStocks.persistence.repository.StockLatestDataRepository;
import com.mobsys.easyStocks.persistence.repository.StockRepository;
import com.mobsys.easyStocks.persistence.repository.WatchlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockUpdateService {

    private final String UNIQUECONTRAINT = "stock-date-unique-constraint";

    @Autowired
    private AlphavantageApi api;

    @Autowired
    private StockDataRepository stockDataRepository;

    @Autowired
    private StockLatestDataRepository stockLatestDataRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private WatchlistRepository watchlistRepository;

    private boolean shouldInitHistory = true;
    private int dayInterval = 3;
    private float percentage = 10;

    public StockUpdateService(final Environment env) {
        if (env.getProperty("init_history") != null) {
            this.shouldInitHistory = Objects.equals(env.getProperty("init_history"), "true");
        }
        if (env.getProperty("percentage") != null) {
            this.percentage = Float.parseFloat(Objects.requireNonNull(env.getProperty("percentage")));
        }
        if (env.getProperty("Interval") != null) {
            this.dayInterval = Integer.parseInt(Objects.requireNonNull(env.getProperty("Interval")), 10);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(StockUpdateService.class);

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        final ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(2);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void checkExistingHistory() {
        logger.info("Checking existing stock data history");
        final long stocksDataRowCount = stockDataRepository.count();
        if (stocksDataRowCount == 0 && shouldInitHistory) {
            logger.info("No stock data history found, starting update");
            saveDailyStocksData(true);
        } else {
            logger.info("Stock data exist, no need to initial fetch");
        }
    }

    @Scheduled(cron = "0 30 23 * * *", zone = "EST")
    public final void updateDailyStockData() {
        logger.info("Start getting Daily Stock Data");
        saveDailyStocksData(false);
        logger.info("Finished getting Daily Stock Data");
        logger.info("Calculating Notifications");
        saveNotifications();
    }

    private final void saveDailyStocksData(final boolean withHistory) {
        final String outputSize = withHistory ? "full" : "compact";
        final List<Stock> stocks = stockRepository.findAll();
        stocks.forEach(stock -> {
            try {
                final var mappedStockData = getMappedDailyStockData(stock, outputSize);
                if (!mappedStockData.isEmpty()) {
                    mappedStockData.forEach(stockDataRepository::save);
                }
            } catch (final DataIntegrityViolationException e) {
                if (e.getMessage() == null || !e.getMessage().contains(UNIQUECONTRAINT)) {
                    throw e;
                }
                logger.warn("Stock data for current date and symbol already exits so -> do not save");
            }
        });

    }

    private final List<StockData> getMappedDailyStockData(final Stock stock, final String outputSize) {
        final Map<String, DataEntry> dailyStockData = getStockDataFromStockApi(stock, outputSize);
        if (dailyStockData == null || dailyStockData.isEmpty()) {
            return new ArrayList<>();
        }
        return dailyStockData.entrySet().stream().map(e -> mapDailyStockData(e, stock)).collect(Collectors.toList());
    }

    private final Map<String, DataEntry> getStockDataFromStockApi(final Stock stock, final String outputSize) {
        final DailyDataResponse response = api.getDailyData(stock.getSymbol(), outputSize, null).block();
        if (response == null) {
            logger.error("Failed to get data for stock {}", stock.getSymbol());
            return null;
        }
        return response.getTimeSeriesDaily();
    }

    private final StockData mapDailyStockData(final Map.Entry<String, DataEntry> entry, final Stock stock) {
        try {
            final Date date = new SimpleDateFormat("yyyy-MM-dd").parse(entry.getKey());
            final StockData stockData = new StockData();
            stockData.setSymbol(stock.getSymbol());
            stockData.setDate(date);
            stockData.setClose(Float.parseFloat(entry.getValue().get4close()));
            return stockData;
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private final void saveNotifications() {
        final var stocks = stockLatestDataRepository.findStockDataLatest();
        stocks.forEach(latestStockData -> {
            final var stockDataToCheckWith = this.getStockDataForIntervalAndSymbol(latestStockData.getDate(),
                    latestStockData.getSymbol());
            if (stockDataToCheckWith != null) {
                final float margin = (stockDataToCheckWith.getClose() / 100) * percentage;
                final boolean stockRisen = latestStockData.getClose() >= stockDataToCheckWith.getAdjustedClose()
                        + margin;
                final boolean stockFallen = latestStockData
                        .getAdjustedClose() <= stockDataToCheckWith.getAdjustedClose() - margin;
                if (stockRisen || stockFallen) {
                    final int dayDifference = getDayDifference(latestStockData.getDate(),
                            stockDataToCheckWith.getDate());
                    saveNotificationForSymbol(latestStockData.getSymbol(), dayDifference);
                }
            }
        });
    }

    private final int getDayDifference(final Date first, final Date second) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s.A");
        final LocalDateTime date1 = LocalDateTime.parse(first.toString(), dtf);
        final LocalDateTime date2 = LocalDateTime.parse(second.toString(), dtf);
        return (int) Duration.between(date2, date1).toDays();
    }

    private final StockData getStockDataForIntervalAndSymbol(final Date latestStockDataDate, final String symbol) {
        final LocalDate dayMinusInterval = latestStockDataDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().minusDays(dayInterval);
        return stockDataRepository.findFirstBySymbolAndDateLessThanEqualOrderByDateDesc(symbol,
                Date.from(dayMinusInterval.atStartOfDay(ZoneId.systemDefault()).toInstant()));

    }

    private final void saveNotificationForSymbol(final String symbol, final int dayInterval) {
        final var tmpStock = new Stock();
        tmpStock.setSymbol(symbol);
        watchlistRepository.setNotifications(tmpStock, dayInterval, percentage);
    }
}
