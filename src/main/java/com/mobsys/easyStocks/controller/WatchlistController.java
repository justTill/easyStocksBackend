package com.mobsys.easyStocks.controller;

import java.util.List;
import java.util.UUID;

import com.mobsys.easyStocks.persistence.model.StockLatestData;
import com.mobsys.easyStocks.persistence.repository.UserRepository;
import com.mobsys.easyStocks.persistence.repository.WatchlistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController()
public class WatchlistController {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/watchlist")
    @ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
    public List<StockLatestData> getWatchlist() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID watchlistId = userRepository.findFirstByMail(user.getUsername()).getWatchlistId();
        return watchlistRepository.findStockDataLatestWatchlist(watchlistId);
    }

    @PostMapping(path = "/watchlist/{symbol}")
    @ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
    public List<StockLatestData> addToWatchlist(@PathVariable String symbol) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID watchlistId = userRepository.findFirstByMail(user.getUsername()).getWatchlistId();
        watchlistRepository.addToWatchlist(symbol, watchlistId);
        return watchlistRepository.findStockDataLatestWatchlist(watchlistId);
    }

    @DeleteMapping(path = "/watchlist/{symbol}")
    @ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
    public String deleteFromWatchlist(@PathVariable String symbol) {
        return "OK";
    }
}