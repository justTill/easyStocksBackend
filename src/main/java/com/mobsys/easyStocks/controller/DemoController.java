package com.mobsys.easyStocks.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mobsys.easyStocks.marketstack.MarketStackApi;
import com.mobsys.easyStocks.persistence.model.DemoModel;
import com.mobsys.easyStocks.persistence.repository.DemoRepository;
import com.mobsys.easyStocks.persistence.repository.MockData;
import com.mobsys.marketstack.model.EndOfDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
    @Autowired
    private DemoRepository demoRepository;
    @Autowired
    private MarketStackApi api;

    @GetMapping("/demomodel")
    public List<DemoModel> getDemoModel() {
        return demoRepository.findAll();
    }

    public MockData mockData1 = new MockData("Adidas","Textil", "2021-11-24", 100);
    public MockData mockData2 = new MockData("Adidas","Textil", "2021-11-23", 102);
    public MockData mockData3 = new MockData("Adidas","Textil", "2021-11-22", 105);
    public MockData mockData4 = new MockData("Adidas","Textil", "2021-11-21", 109);
    public MockData mockData5 = new MockData("Adidas","Textil", "2021-11-20", 123);

    // /search/stocks/?begin = date1 & end = date2
    @GetMapping("/search/{name}")
    @ResponseBody
    public List<DemoModel> getDemoModelByName(@PathVariable String name) {
        List<DemoModel> stockBySector = demoRepository.findBySector(name);
        List<DemoModel> stockByName = demoRepository.findByName(name);
        List<DemoModel> resultList = Stream.concat(stockBySector.stream(), stockByName.stream()).collect(Collectors.toList());
        return resultList;
    };

    @GetMapping("/search/stocks")
    @ResponseBody
    public List<DemoModel> getDemoModel(@RequestParam String begin, @RequestParam String end) {
        List<DemoModel> stockBySector = demoRepository.findBySector(name);
        List<DemoModel> stockByName = demoRepository.findByName(name);
        List<DemoModel> resultList = Stream.concat(stockBySector.stream(), stockByName.stream()).collect(Collectors.toList());
        return resultList;
    };

    @GetMapping("/marketstack")
    @ResponseBody
    public EndOfDay getEOD(@RequestParam("symbol") String symbol) {
        return api.eodGet(List.of(symbol), null, null, null, null, null, null).block();
    }
}
