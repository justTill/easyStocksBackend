package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
