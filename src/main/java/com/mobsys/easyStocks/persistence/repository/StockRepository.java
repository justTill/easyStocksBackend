package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.StockData;
import com.mobsys.easyStocks.persistence.model.StockLatestData;
import com.mobsys.easyStocks.persistence.model.StockMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAll();
    Stock findBySymbol(String symbol);
    @Query(value = "SELECT id, name, symbol, sector_id FROM stocks", nativeQuery = true)
    List<Stock> findStocks();




    @Query(value ="SELECT * FROM stocks s inner join stocks_data sd on s.symbol = sd.symbol where sd.date between '2020-12-02' and '2021-12-02' and s.symbol = ?1", nativeQuery = true)
    List<Stock> findSymbolOneYearDate(String symbol, Date from, Date to);





    // "SELECT * FROM stocks AS s INNER JOIN stocks_data  AS d ON s.symbol = (SELECT d.symbol FROM stocks_data ORDER BY date ASC LIMIT 1)"

    /*@Query(value = "SELECT * FROM stocks" , nativeQuery = true)
    List<StockMin> findStock();*/

    //List<Stock> findByDateBetween(LocalDateTime from, LocalDateTime to);

}
