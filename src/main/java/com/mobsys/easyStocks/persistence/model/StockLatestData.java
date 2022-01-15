package com.mobsys.easyStocks.persistence.model;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class StockLatestData implements Serializable {
    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String symbol;

    @Column
    @Nullable
    private Float close;

    @Column
    @Nullable
    private Date date;

    @Column
    private String sectorNameDe;

    @Column
    private String sectorNameEn;

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

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public String getSectorNameDe() {
        return sectorNameDe;
    }

    public void setSectorNameDe(final String sectorNameDe) {
        this.sectorNameDe = sectorNameDe;
    }

    public String getSectorNameEn() {
        return sectorNameEn;
    }

    public void setSectorNameEn(final String sectorNameEn) {
        this.sectorNameEn = sectorNameEn;
    }

    public Float getClose() {
        return close;
    }

    public void setClose(final Float close) {
        this.close = close;
    }
}
