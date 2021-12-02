package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.StockData;
import com.mobsys.easyStocks.persistence.model.StockMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAll();

    @Query(value =  "SELECT id, name, symbol, sector_id FROM stocks", nativeQuery = true)
    List<Stock> findStocks();

    // "SELECT * FROM stocks AS s INNER JOIN stocks_data  AS d ON s.symbol = (SELECT d.symbol FROM stocks_data ORDER BY date ASC LIMIT 1)"

    /*@Query(value = "SELECT * FROM stocks" , nativeQuery = true)
    List<StockMin> findStock();*/

    //List<Stock> findByDateBetween(LocalDateTime from, LocalDateTime to);

}
