package com.mobsys.easyStocks.controller;

import com.mobsys.easyStocks.marketstack.MarketStackApi;
import com.mobsys.marketstack.model.EndOfDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarketStackController {
    @Autowired
    private MarketStackApi api;

    @GetMapping("/marketstack")
    @ResponseBody
    public EndOfDay getEOD(@RequestParam("symbol") final String symbol) {
        return api.eodGet(List.of(symbol), null, null, null, null, null, null).block();
    }
}
