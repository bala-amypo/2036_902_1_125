package com.example.demo.service;

import com.example.demo.entity.ProfitCalculationRecord;
import java.util.List;

public interface ProfitCalculationService {

    ProfitCalculationRecord calculateProfit(Long menuItemId);

    ProfitCalculationRecord getCalculationById(Long id);

    List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId);

    List<ProfitCalculationRecord> getAllCalculations();
    public List<ProfitCalculationRecord>
findRecordsWithMarginGreaterThanEqual(double value) {

    return recordRepository.findByProfitMarginGreaterThanEqual(
            BigDecimal.valueOf(value)
    );
}


    // ✅ REQUIRED BY TESTS
    List<ProfitCalculationRecord> findRecordsWithMarginBetween(double min, double max);
}
