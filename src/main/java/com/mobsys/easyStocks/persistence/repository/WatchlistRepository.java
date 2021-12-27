package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.WatchlistData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WatchlistRepository extends JpaRepository<WatchlistData, Long> {

    List<WatchlistData> findByWatchlistIdAndSeen(UUID watchlistId, Boolean seen);
}
