package com.example.demo.controller;

import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.service.ProfitCalculationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profit")
public class ProfitCalculationController {

    private final ProfitCalculationService service;

    public ProfitCalculationController(ProfitCalculationService service) {
        this.service = service;
    }

    // POST /api/profit/calculate/{menuItemId}
    @PostMapping("/calculate/{menuItemId}")
    public ProfitCalculationRecord calculate(@PathVariable Long menuItemId) {
        return service.calculateProfit(menuItemId);
    }

    // GET /api/profit/{id}
    @GetMapping("/{id}")
    public ProfitCalculationRecord get(@PathVariable Long id) {
        return service.getCalculationById(id);
    }

    // GET /api/profit/menu-item/{menuItemId}
    @GetMapping("/menu-item/{menuItemId}")
    public List<ProfitCalculationRecord> history(@PathVariable Long menuItemId) {
        return service.getCalculationsForMenuItem(menuItemId);
    }

    // GET /api/profit
    @GetMapping
    public List<ProfitCalculationRecord> getAll() {
        return service.getAllCalculations();
    }

    // ✅ REQUIRED BY TESTS
    // GET /api/profit/margin?min=10&max=100
    @GetMapping("/margin")
    public List<ProfitCalculationRecord> byMarginRange(
            @RequestParam double min,
            @RequestParam double max
    ) {
        return service.findRecordsWithMarginBetween(min, max);
    }

    // ✅ REQUIRED BY TESTS
    // GET /api/profit/margin/min/{min}
    @GetMapping("/margin/min/{min}")
    public List<ProfitCalculationRecord> byMarginMin(@PathVariable double min) {
        return service.findRecordsWithMarginGreaterThanEqual(min);
    }
}
