package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAll();

    @Query(value = "SELECT * FROM stocks" , nativeQuery = true)
    List<Stock> findStock();
}
