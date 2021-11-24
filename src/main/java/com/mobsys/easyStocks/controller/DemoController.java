package com.mobsys.easyStocks.controller;

import java.util.List;

import com.mobsys.alphavantage.model.DailyDataResponse;
import com.mobsys.easyStocks.alphavantage.AlphavantageApi;
import com.mobsys.easyStocks.persistence.model.DemoModel;
import com.mobsys.easyStocks.persistence.repository.DemoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private DemoRepository demoRepository;
    @Autowired
    private AlphavantageApi api;

    @GetMapping("/demomodel")
    public List<DemoModel> getDemoModel() {
        return demoRepository.findAll();
    }

    @GetMapping("/alphavantage")
    @ResponseBody
    public DailyDataResponse getAlphavantageQuery(@RequestParam("symbol") String symbol) {
        return api.getDailyData(symbol, null, null).block();
    }
}
