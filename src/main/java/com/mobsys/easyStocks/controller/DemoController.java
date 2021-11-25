package com.mobsys.easyStocks.controller;

import com.mobsys.easyStocks.persistence.model.Sector;
import com.mobsys.easyStocks.persistence.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {
    @Autowired
    private SectorRepository sectorRepository;

    @GetMapping("/demomodel")
    public List<Sector> getDemoModel() {
        return sectorRepository.findAll();
    }
}
