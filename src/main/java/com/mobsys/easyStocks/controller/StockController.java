package com.mobsys.easyStocks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/stocks")
public class StockController {

    @GetMapping(path="/")
    public String getStocks(){
        return "returned die Stocks";
    }

    @GetMapping(path="/")
    public String stockData(){
        return "returned Daten für bestimmten Stock";
    }

}