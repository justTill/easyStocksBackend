package com.mobsys.easyStocks.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "watchlists")
public class Watchlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "watchlistId", referencedColumnName = "watchlistId")
    private User watchlistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "symbol", referencedColumnName = "symbol")
    private Stock symbol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(User watchlistId) {
        this.watchlistId = watchlistId;
    }

    public Stock getSymbol() {
        return symbol;
    }

    public void setSymbol(Stock symbol) {
        this.symbol = symbol;
    }
}