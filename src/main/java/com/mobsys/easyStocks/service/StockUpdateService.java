package com.mobsys.easyStocks.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.context.event.EventListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class StockUpdateService {
    @Autowired
    private AlphavantageApi api;

    @Autowired
    private StockDataRepository stockDataRepository;
    @Autowired
    private StockRepository stockRepository;

    private static final Logger logger = LoggerFactory.getLogger(StockUpdateService.class);

    @EventListener(ApplicationReadyEvent.class)
    public void checkExistingHistory() {
        logger.info("Checking existing stock data history");
        List<StockData> stocksData = stockDataRepository.findAll();
        if (stocksData.isEmpty()) {
            logger.info("No stock data history found, starting update");
            List<Stock> stocks = stockRepository.findAll();
            for (Stock stock : stocks) {
                try {
                    DailyDataResponse response = api.getDailyData(stock.getSymbol(), "full", null).block();
                    if (response == null) {
                        logger.error("Failed to get data for stock {}", stock.getSymbol());
                        continue;
                    }
                    Map<String, DataEntry> newStockData = response.getTimeSeriesDaily();
                    List<StockData> mappedStockData = newStockData.entrySet().stream().map((entry) -> {
                        try {
                            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(entry.getKey());
                            StockData stockData = new StockData();
                            stockData.setSymbol(stock.getSymbol());
                            stockData.setDate(date);
                            stockData.setAdjustedClose(Float.parseFloat(entry.getValue().get5adjustedClose()));
                            return stockData;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }).collect(Collectors.toList());
                    stockDataRepository.saveAll(mappedStockData);
                } catch (DuplicateKeyException e) {
                    // We ignore this error
                    logger.error("Duplicate key exception for {}", stock.getSymbol());
                }
            }
        } else {
            logger.info("Stock data exist, no need to initial fetch");
        }
    }
}
