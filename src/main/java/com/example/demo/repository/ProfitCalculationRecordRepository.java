package com.example.demo.repository;

import com.example.demo.entity.ProfitCalculationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
public interface ProfitCalculationRecordRepository
        extends JpaRepository<ProfitCalculationRecord, Long> {

    List<ProfitCalculationRecord> findByMenuItemId(Long menuItemId);

    List<ProfitCalculationRecord>
        findByProfitMarginBetween(BigDecimal min, BigDecimal max);
        List<ProfitCalculationRecord>
findByProfitMarginGreaterThanEqual(java.math.BigDecimal min);


    // ✅ REQUIRED BY TESTS
    List<ProfitCalculationRecord>
        findByProfitMarginGreaterThanEqual(BigDecimal value);
}

