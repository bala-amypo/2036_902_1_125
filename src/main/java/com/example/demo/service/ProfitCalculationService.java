package com.example.demo.service;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;

public interface ProfitCalculationService {

    ProfitCalculationRecord calculateProfit(MenuItem menuItem, double totalCost);
}
