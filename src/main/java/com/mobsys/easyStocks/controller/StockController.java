package com.mobsys.easyStocks.controller;

import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.StockLatestData;
import com.mobsys.easyStocks.persistence.repository.StockLatestDataRepository;
import com.mobsys.easyStocks.persistence.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/stocks")
public class StockController {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockLatestDataRepository stockLatestDataRepository;

    @GetMapping()
    public List<StockLatestData> getLatestStockData() {
        return stockLatestDataRepository.findStockDataLatest();
    }

    @GetMapping(path = "/{symbol}")
    public Stock stockData(@PathVariable String symbol, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {

        final Date fromDate;
        final Date toDate;

        if (from != null) {
            LocalDate fromLocal = LocalDate.parse(from);
            fromDate = Date.from(fromLocal.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.YEAR, -1);
            fromDate = Date.from(c.toInstant());
        }
        if (to != null) {
            LocalDate toLocal = LocalDate.parse(to);
            toDate = Date.from(toLocal.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        } else {
            toDate = new Date();
        }
        Stock stock = stockRepository.findBySymbol(symbol);
        stock.setData(stock.getData().stream().sorted((data1, data2) -> -data1.getDate().compareTo(data2.getDate())).filter(data -> data.getDate().after(fromDate) && data.getDate().before(toDate)).collect(Collectors.toList()));
        return stock;
    }
}