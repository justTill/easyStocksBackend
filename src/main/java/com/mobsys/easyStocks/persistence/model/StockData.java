package com.mobsys.easyStocks.persistence.model;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stocks_data")
public class StockData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String symbol;

    @Column
    @Nullable
    private Float adjustedClose;

    @Column
    @Nullable
    private Float close;

    @Column
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public Float getAdjustedClose() {
        return adjustedClose;
    }

    public void setAdjustedClose(final Float adjustedClose) {
        this.adjustedClose = adjustedClose;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Float getClose() {
        return close;
    }

    public void setClose(Float close) {
        this.close = close;
    }
}
