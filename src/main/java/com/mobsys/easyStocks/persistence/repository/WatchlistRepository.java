package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.models.dtos.GetWatchlistResponseDto;
import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.StockLatestData;
import com.mobsys.easyStocks.persistence.model.WatchlistData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface WatchlistRepository extends JpaRepository<StockLatestData, Long> {


    @Query(value = "select s.id, s.name, s.symbol, s.sector_id, adjusted_close, date, s2.name_de as sector_name_de ,s2.name_en as sector_name_en from watchlist_data wd, stocks s join (\n" +
            "    select distinct on (symbol) date, adjusted_close, symbol from stocks_data sd \n" +
            "    order by symbol desc \n" +
            ") as most\n" +
            "on most.symbol = s.symbol\n" +
            "join sectors s2 \n" +
            "on s2.sector_id = s.sector_id \n" +
            "where s.symbol = wd.symbol \n" +
            "and wd.watchlist_id = :param_watchlist_id", nativeQuery = true)
    List<StockLatestData> findStockDataLatestWatchlist(@Param("param_watchlist_id") UUID param_watchlist_id);



    @Modifying
    @Query(value= "INSERT INTO watchlist_data (watchlist_id, symbol) values (:watchlist_id, :symbol)", nativeQuery = true)
    @Transactional
    void addToWatchlist (@Param("symbol") String symbol,@Param("watchlist_id") UUID watchlist_id);

    @Modifying
    @Query(value= "DELETE FROM watchlist_data values WHERE symbol = :symbol AND watchlist_id = :watchlist_id", nativeQuery = true)
    @Transactional
    void deleteFromWatchlist (@Param("symbol") String symbol,@Param("watchlist_id") UUID watchlist_id);


    /*@Transactional
    public void addToWatchlist(String symbol, UUID watchlist_id){
        entityManager.createNativeQuery("INSERT INTO watchlist_data (watchlist_id, symbol) VALUES (?,?,?)")
                .setParameter(1, watchlist_id)
                .setParameter(2, symbol)
                .executeUpdate();
    }*/

}
