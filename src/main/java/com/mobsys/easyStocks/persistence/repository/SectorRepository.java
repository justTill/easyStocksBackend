package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Long> {
}
