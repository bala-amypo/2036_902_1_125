package com.example.demo.controller;

import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.service.ProfitCalculationService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profit")
public class ProfitCalculationController {

    private final ProfitCalculationService service;

    public ProfitCalculationController(ProfitCalculationService service){
        this.service = service;
    }

    @PostMapping("/calculate/{menuItemId}")
    public ProfitCalculationRecord calculate(@PathVariable Long menuItemId){
        return service.calculateProfit(menuItemId);
    }

    @GetMapping("/{id}")
    public ProfitCalculationRecord get(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/menu-item/{menuItemId}")
    public List<ProfitCalculationRecord> listByMenu(@PathVariable Long menuItemId){
        return service.getHistoryByMenuItem(menuItemId);
    }

    @GetMapping("/")
    public List<ProfitCalculationRecord> all(){
        return service.getAll();
    }
}
