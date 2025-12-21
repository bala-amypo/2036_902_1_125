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

    /**
     * POST  /calculate/{menuItemId}
     * Calculate profit for a menu item and save record
     */
    @PostMapping("/calculate/{menuItemId}")
    public ProfitCalculationRecord calculateProfit(@PathVariable Long menuItemId){
        return service.calculateProfit(menuItemId);
    }

    /**
     * GET /{id}
     * Get a single profit record
     */
    @GetMapping("/{id}")
    public ProfitCalculationRecord getProfitRecord(@PathVariable Long id){
        return service.getCalculationById(id);
    }

    /**
     * GET /menu-item/{menuItemId}
     * Get profit history for a menu item
     */
    @GetMapping("/menu-item/{menuItemId}")
    public List<ProfitCalculationRecord> getMenuItemProfitHistory(@PathVariable Long menuItemId){
        return service.getCalculationsForMenuItem(menuItemId);
    }

    /**
     * GET /
     * Get all profit calculations
     */
    @GetMapping("/")
    public List<ProfitCalculationRecord> getAllProfitRecords(){
        return service.getAllCalculations();
    }
}
