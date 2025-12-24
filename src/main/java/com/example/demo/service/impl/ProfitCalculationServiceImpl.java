package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.service.ProfitCalculationService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final ProfitCalculationRecordRepository recordRepository;
    private final MenuItemRepository menuItemRepository;

    public ProfitCalculationServiceImpl(
            ProfitCalculationRecordRepository recordRepository,
            MenuItemRepository menuItemRepository
    ) {
        this.recordRepository = recordRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
public ProfitCalculationRecord calculateProfit(MenuItem menuItem, double sellingPrice) {

    double totalCost = 0;

    List<RecipeIngredient> recipeIngredients =
            recipeIngredientRepository.findByMenuItem(menuItem);

    if (recipeIngredients.isEmpty()) {
        totalCost = 0; // no ingredients added yet
    } else {
        for (RecipeIngredient ri : recipeIngredients) {
            totalCost += ri.getQuantityRequired()
                    * ri.getIngredient().getCostPerUnit();
        }
    }

    double profit = sellingPrice - totalCost;

    ProfitCalculationRecord record = new ProfitCalculationRecord();
    record.setMenuItem(menuItem);
    record.setTotalCost(totalCost);
    record.setProfitMargin(profit);

    return recordRepository.save(record);
}
}