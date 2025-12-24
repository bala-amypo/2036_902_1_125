package com.example.demo.service.impl;

import com.example.demo.entity.Profit;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfitCalculationServiceImpl {

    public Profit calculateProfit(double sellingPrice, double cost) {

        Profit profit = new Profit();
        profit.setTotalCost(cost);
        profit.setProfitMargin(sellingPrice - cost);
        profit.setCalculatedAt(LocalDateTime.now());

        return profit;
    }
}
