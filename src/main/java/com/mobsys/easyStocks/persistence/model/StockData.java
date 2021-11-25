package com.mobsys.easyStocks.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stocks_data")
public class StockData {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String symbol;

    @Column
    private Float adjustedClose;

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
}
