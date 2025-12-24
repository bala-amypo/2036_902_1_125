package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.ProfitCalculationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ProfitCalculationRecordRepository recordRepository;

    public ProfitCalculationServiceImpl(
            RecipeIngredientRepository recipeIngredientRepository,
            ProfitCalculationRecordRepository recordRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public ProfitCalculationRecord calculateProfit(MenuItem menuItem, double sellingPrice) {

        double totalCost = 0;

        List<RecipeIngredient> recipeIngredients =
                recipeIngredientRepository.findByMenuItem(menuItem);

        for (RecipeIngredient ri : recipeIngredients) {
            totalCost += ri.getQuantityRequired()
                    * ri.getIngredient().getCostPerUnit();
        }

        double profit = sellingPrice - totalCost;

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profit);

        return recordRepository.save(record);
    }
}
