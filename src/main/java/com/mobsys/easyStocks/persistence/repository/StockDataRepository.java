package com.mobsys.easyStocks.persistence.repository;

import java.util.List;

import com.mobsys.easyStocks.persistence.model.StockData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDataRepository extends JpaRepository<StockData, Long> {
    List<StockData> findAll();
}
