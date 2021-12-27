package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.Stock;
import com.mobsys.easyStocks.persistence.model.WatchlistData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface WatchlistRepository extends JpaRepository<WatchlistData, Long> {

    List<WatchlistData> findByWatchlistIdAndSeen(UUID watchlistId, Boolean seen);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE WatchlistData wd SET wd.seen = false, wd.dayInterval = :interval , wd.percentage = :percentage where symbol = :symbol")
    void setNotifications(@Param(value = "symbol") Stock Symbol, @Param(value = "interval") Integer dayInterval, @Param(value = "percentage") Float percentage);
}
