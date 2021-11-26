package com.mobsys.easyStocks.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "stocks")
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String symbol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectorId", referencedColumnName = "sectorId")
    private Sector sector;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "symbol", referencedColumnName = "symbol")
    private List<StockData> data;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(final Sector sectorId) {
        this.sector = sector;
    }

    public List<StockData> getData() {
        return data;
    }

    public void setData(final List<StockData> data) {
        this.data = data;
    }
}
