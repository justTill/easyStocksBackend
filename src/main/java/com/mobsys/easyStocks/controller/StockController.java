package com.mobsys.easyStocks.controller;

//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.StockData;
import com.mobsys.easyStocks.persistence.repository.StockDataRepository;
import com.mobsys.easyStocks.persistence.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(path="/stocks")
public class StockController {

    @Autowired
    private StockDataRepository stockDataRepository;

    @Autowired
    private StockRepository stockRepository;

    @GetMapping(path="/stocks")
    public List<Stock> getStocks(){
        return stockRepository.findAll();
    }

    @GetMapping(path="/stockData")
    public List<StockData> stockData(){
        return stockDataRepository.findAll();
    }

    @GetMapping(path="/test")
    public List<Stock> stockTest(){
        return stockRepository.findStock();
    }
}