package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.StockLatestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockLatestDataRepository extends JpaRepository<StockLatestData, Long> {

    @Query(value = "select s.id, s.name, s.symbol, s.sector_id, close, date, s2.name_de as sector_name_de ,s2.name_en as sector_name_en from stocks s join (\n"
            +
            "    select distinct on (symbol) date, close, symbol from stocks_data sd \n" +
            "    order by symbol desc \n" +
            ") as most\n" +
            "on most.symbol = s.symbol\n" +
            "join sectors s2 \n" +
            "on s2.sector_id = s.sector_id;", nativeQuery = true)
    List<StockLatestData> findStockDataLatest();

}
