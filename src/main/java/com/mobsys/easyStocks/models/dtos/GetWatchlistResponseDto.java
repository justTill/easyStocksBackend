package com.mobsys.easyStocks.models.dtos;

import java.util.List;

import com.mobsys.easyStocks.persistence.model.Stock;

public class GetWatchlistResponseDto {
    private Integer id;

    private List<Stock> symbols;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Stock> getSymbols() {
        return symbols;
    }

    public void setSymbol(List<Stock> symbols) {
        this.symbols = symbols;
    }
}
