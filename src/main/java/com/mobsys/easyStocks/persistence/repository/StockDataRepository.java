package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockDataRepository extends JpaRepository<StockData, Long> {
    List<StockData> findAll();

    @Query(value =  "SELECT id, symbol, adjusted_close, date, COUNT(symbol) FROM stocks_data sd GROUP BY id,symbol ORDER BY date DESC LIMIT 40;", nativeQuery = true)
    List<StockData> findStocks();



}
