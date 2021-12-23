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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class WatchlistController {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @GetMapping(path = "/watchlist")
    @ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
    public List<StockLatestData> getWatchlist() {
        UUID watchlist_id = UUID.fromString("0a0399e6-9a71-4bab-976a-5ac0e2ac516d");

        return watchlistRepository.findStockDataLatestWatchlist(watchlist_id);
    }

    @RequestMapping (path = "/watchlist/{symbol}")
    @ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
    public String  addToWatchlist(@PathVariable String symbol) {
        UUID watchlist_id = UUID.fromString("0a0399e6-9a71-4bab-976a-5ac0e2ac516d");
        watchlistRepository.addToWatchlist(symbol, watchlist_id);

        return "created";
    }

    @DeleteMapping(path = "/watchlist/{symbol}")
    @ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
    public String deleteFromWatchlist(@PathVariable String symbol) {
        return "OK";
    }
}