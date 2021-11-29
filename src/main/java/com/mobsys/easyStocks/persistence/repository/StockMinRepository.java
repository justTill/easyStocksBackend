package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.StockMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockMinRepository extends JpaRepository<StockMin, Long> {

    @Query(value = "SELECT * FROM stocks" , nativeQuery = true)
    List<StockMin> findStock();
}
