package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.service.ProfitCalculationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final ProfitCalculationRecordRepository repository;

    public ProfitCalculationServiceImpl(ProfitCalculationRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProfitCalculationRecord calculateProfit(MenuItem menuItem, double totalCost) {

        double sellingPrice = menuItem.getSellingPrice();
        double profitMargin = sellingPrice - totalCost;

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profitMargin);
        record.setCalculatedAt(LocalDateTime.now());

        return repository.save(record);
    }
}
