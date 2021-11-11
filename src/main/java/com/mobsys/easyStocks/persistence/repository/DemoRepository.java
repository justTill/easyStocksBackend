package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.DemoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemoRepository extends JpaRepository<DemoModel, Long> {
    @Override
    List<DemoModel> findAll();
}
