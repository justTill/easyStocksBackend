package com.mobsys.easyStocks.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="stocks")
public class Stock implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private String symbol;

    @OneToMany
    @JoinColumn(name="sectorId")
    private Sector sector;

    @ManyToOne
    @JoinColumn(name="symbol")
    private List<StockData> data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sectorId) {
        this.sector = sector;
    }
}
