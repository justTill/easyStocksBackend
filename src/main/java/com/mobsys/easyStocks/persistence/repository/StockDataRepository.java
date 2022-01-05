package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.StockData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StockDataRepository extends JpaRepository<StockData, Long> {
    @Override
    List<StockData> findAll();

    StockData findFirstBySymbolAndDateLessThanEqualOrderByDateDesc(String symbol, Date before);
}