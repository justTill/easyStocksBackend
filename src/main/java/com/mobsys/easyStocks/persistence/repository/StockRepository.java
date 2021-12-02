package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAll();
    Stock findBySymbol(String symbol);
}
