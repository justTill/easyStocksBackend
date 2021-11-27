package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Stock, Long> {

}
