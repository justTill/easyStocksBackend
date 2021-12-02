package com.mobsys.easyStocks.controller;

import com.mobsys.easyStocks.models.dtos.GetWatchlistResponseDto;
import com.mobsys.easyStocks.models.dtos.PostWatchlistResponseDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatchlistController {

    @GetMapping(path = "/watchlist")
    public GetWatchlistResponseDto getWatchlist() {
        return null;
    }

    @PostMapping(path = "/watchlist/{symbol}")
    public PostWatchlistResponseDto addToWatchlist(@PathVariable String symbol) {
        return null;
    }

    @DeleteMapping(path = "/watchlist/{symbol}")
    public String deleteFromWatchlist(@PathVariable String symbol) {
        return "OK";
    }
}