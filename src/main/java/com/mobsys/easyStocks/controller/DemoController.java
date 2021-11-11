package com.mobsys.easyStocks.controller;

import com.mobsys.easyStocks.persistence.model.DemoModel;
import com.mobsys.easyStocks.persistence.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {
    @Autowired
    private DemoRepository demoRepository;

    @GetMapping("/demomodel")
    public List<DemoModel> getDemoModel() {
        return demoRepository.findAll();
    }
}
