package com.mobsys.easyStocks.service;

import com.mobsys.alphavantage.model.DailyDataResponse;
import com.mobsys.alphavantage.model.DataEntry;
import com.mobsys.easyStocks.alphavantage.AlphavantageApi;
import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.StockData;
import com.mobsys.easyStocks.persistence.repository.StockDataRepository;
import com.mobsys.easyStocks.persistence.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockUpdateService {

    private final String UNIQUECONTRAINT = "stock-date-unique-constraint";
    @Autowired
    private AlphavantageApi api;

    @Autowired
    private StockDataRepository stockDataRepository;
    @Autowired
    private StockRepository stockRepository;

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
        if (stocksDataRowCount == 0) {
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
            stockData.setAdjustedClose(Float.parseFloat(entry.getValue().get5adjustedClose()));
            return stockData;
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
