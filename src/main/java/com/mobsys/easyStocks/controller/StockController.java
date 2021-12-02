package com.mobsys.easyStocks.controller;

//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.StockData;
import com.mobsys.easyStocks.persistence.model.StockLatestData;
import com.mobsys.easyStocks.persistence.model.StockMin;
import com.mobsys.easyStocks.persistence.repository.StockDataRepository;
import com.mobsys.easyStocks.persistence.repository.StockLatestDataRepository;
import com.mobsys.easyStocks.persistence.repository.StockMinRepository;
import com.mobsys.easyStocks.persistence.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping(path="/stocks")
public class StockController {

    @Autowired
    private StockDataRepository stockDataRepository;
    @Autowired
    private StockMinRepository stockMinRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockLatestDataRepository stockLatestDataRepository;


    @GetMapping(path = "/stocksAllData")
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    @GetMapping(path = "/stocks")
    public List<StockLatestData> test() {
        return stockLatestDataRepository.findStockDataLatest();
    }

    @GetMapping(path = "/stocksList")
    public List<StockMin> test1() {
        return stockMinRepository.findStock();
    }

    @GetMapping(path = "/stockData")
    public List<StockData> stockData() {
        return stockDataRepository.findAll();
    }

    @GetMapping(path = "/stocks/{symbol}")
    public Stock stockData(@PathVariable String symbol) {
        final Date to = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        final Date from = Date.from(c.toInstant());
        Stock stock = stockRepository.findBySymbol(symbol);
        stock.setData(stock.getData().stream().sorted((data1, data2) -> -data1.getDate().compareTo(data2.getDate())).filter(data -> data.getDate().after(from) && data.getDate().before(to)).collect(Collectors.toList()));
        return stock;
    }


    //LocalDateTime localDateTimeFrom = LocalDateTime.of(2021, 11, 27, 00, 00, 00);
    //LocalDateTime localDateTimeTo = LocalDateTime.now();

    /*@GetMapping(path="/test2")
    public List<Stock> stockTest2(){
        return stockRepository.findByDateBetween(localDateTimeFrom, localDateTimeTo);
    }*/

}