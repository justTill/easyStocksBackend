package com.mobsys.easyStocks.persistence.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class StockMin {
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

}
