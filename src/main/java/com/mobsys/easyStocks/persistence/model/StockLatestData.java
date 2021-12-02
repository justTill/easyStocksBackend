package com.mobsys.easyStocks.persistence.model;

import liquibase.pro.packaged.C;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class StockLatestData implements Serializable {
    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String symbol;

    @Column
    private Float adjustedClose;

    @Column
    private Date date;

    @Column
    private String sectorNameDe;

    @Column
    private String sectorNameEn;


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

    public Float getAdjustedClose() {
        return adjustedClose;
    }

    public void setAdjustedClose(Float adjustedClose) {
        this.adjustedClose = adjustedClose;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSectorNameDe() {
        return sectorNameDe;
    }

    public void setSectorNameDe(String sectorNameDe) {
        this.sectorNameDe = sectorNameDe;
    }

    public String getSectorNameEn() {
        return sectorNameEn;
    }

    public void setSectorNameEn(String sectorNameEn) {
        this.sectorNameEn = sectorNameEn;
    }
}
