package com.mobsys.easyStocks.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "watchlist_data")
public class WatchlistData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID watchlistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "symbol", referencedColumnName = "symbol")
    private Stock symbol;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public UUID getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(final UUID watchlistId) {
        this.watchlistId = watchlistId;
    }

    public Stock getSymbol() {
        return symbol;
    }

    public void setSymbol(final Stock symbol) {
        this.symbol = symbol;
    }
}