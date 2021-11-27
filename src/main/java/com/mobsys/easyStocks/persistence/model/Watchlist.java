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

    @Column
    private String watchlistId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "symbol", referencedColumnName = "symbol")
    private List<Stock> symbols;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}