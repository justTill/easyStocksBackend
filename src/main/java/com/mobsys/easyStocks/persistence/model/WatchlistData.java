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

    @Column
    private Integer DayInterval;

    @Column
    private Boolean seen;

    @Column
    private Float percentage;

    public Integer getDayInterval() {
        return DayInterval;
    }

    public void setDayInterval(final Integer dayInterval) {
        DayInterval = dayInterval;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(final Boolean seen) {
        this.seen = seen;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(final Float percentage) {
        this.percentage = percentage;
    }

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