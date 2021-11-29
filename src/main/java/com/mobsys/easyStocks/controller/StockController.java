package com.mobsys.easyStocks.controller;

//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.StockData;
import com.mobsys.easyStocks.persistence.model.StockMin;
import com.mobsys.easyStocks.persistence.repository.StockDataRepository;
import com.mobsys.easyStocks.persistence.repository.StockMinRepository;
import com.mobsys.easyStocks.persistence.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
//@RequestMapping(path="/stocks")
public class StockController {

    @Autowired
    private StockDataRepository stockDataRepository;
    @Autowired
    private StockMinRepository stockMinRepository;
    @Autowired
    private StockRepository stockRepository;

    public StockController() {
    }

    @GetMapping(path="/stocksAllData")
    public List<Stock> getStocks(){
        return stockRepository.findAll();
    }

    @GetMapping(path="/stockData")
    public List<StockData> stockData(){
        return stockDataRepository.findAll();
    }

    @GetMapping(path="/stocks")
    public List<StockMin> stockTest(){
        return stockMinRepository.findStock();
    }


    //LocalDateTime localDateTimeFrom = LocalDateTime.of(2021, 11, 27, 00, 00, 00);
    //LocalDateTime localDateTimeTo = LocalDateTime.now();

    /*@GetMapping(path="/test2")
    public List<Stock> stockTest2(){
        return stockRepository.findByDateBetween(localDateTimeFrom, localDateTimeTo);
    }*/

}