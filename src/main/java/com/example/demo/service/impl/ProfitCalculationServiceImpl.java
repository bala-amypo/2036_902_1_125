package com.example.demo.service.impl;

import com.example.demo.entity.ProfitCalculationRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfitCalculationServiceImpl {

    public ProfitCalculationRecord calculateProfit(long menuItemId) {
        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setTotalCost(0.0);
        record.setProfitMargin(0.0);
        record.setCalculatedAt(LocalDateTime.now());
        return record;
    }

    public ProfitCalculationRecord getById(long id) {
        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setId(id);
        return record;
    }

    public List<ProfitCalculationRecord> getAll() {
        return new ArrayList<>();
    }

    public List<ProfitCalculationRecord> getByMenuItem(long menuItemId) {
        return new ArrayList<>();
    }
}
