package com.mobsys.easyStocks.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatchlistController {

    @GetMapping(path="/watchlist")
    public String getWatchlist(){
        return "Das ist die Watchlist";
    }

    @PostMapping(path="/watchlist")
    public String addToWatchlist(){
        return "Stock zur Watchlist hinzugefügt";
    }

    @DeleteMapping(path="/watchlist")
    public String deleteFromWatchlist(){
        return "Stock von der Watchlist entfernt";
    }
}