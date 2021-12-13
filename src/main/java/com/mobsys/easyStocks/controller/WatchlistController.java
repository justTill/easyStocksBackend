package com.mobsys.easyStocks.controller;

import com.mobsys.easyStocks.models.dtos.GetWatchlistResponseDto;
import com.mobsys.easyStocks.models.dtos.PostWatchlistResponseDto;

import com.mobsys.easyStocks.persistence.model.StockLatestData;
import com.mobsys.easyStocks.persistence.repository.StockRepository;
import com.mobsys.easyStocks.persistence.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class WatchlistController {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @GetMapping(path = "/watchlist")
    public List<StockLatestData> getWatchlist() {
        UUID watchlist_id = UUID.fromString("0a0399e6-9a71-4bab-976a-5ac0e2ac516d");

        return watchlistRepository.findStockDataLatestWatchlist(watchlist_id);
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