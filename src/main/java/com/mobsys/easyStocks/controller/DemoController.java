package com.mobsys.easyStocks.controller;

import java.util.List;

import com.mobsys.easyStocks.marketstack.MarketStackApi;
import com.mobsys.easyStocks.persistence.model.DemoModel;
import com.mobsys.easyStocks.persistence.repository.DemoRepository;
import com.mobsys.marketstack.model.EndOfDay;

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
    private MarketStackApi api;

    @GetMapping("/demomodel")
    public List<DemoModel> getDemoModel() {
        return demoRepository.findAll();
    }

    @GetMapping("/marketstack")
    @ResponseBody
    public EndOfDay getEOD(@RequestParam("symbol") String symbol) {
        return api.eodGet(List.of(symbol), null, null, null, null, null, null).block();
    }
}
