package com.example.demo.service;

import com.example.demo.entity.ProfitCalculationRecord;
import java.util.List;

public interface ProfitCalculationService {

    ProfitCalculationRecord calculateProfit(Long menuItemId);

    ProfitCalculationRecord getById(Long id);

    List<ProfitCalculationRecord> getHistoryByMenuItem(Long menuItemId);

    List<ProfitCalculationRecord> getAll();
}
