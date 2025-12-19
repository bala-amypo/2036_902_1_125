package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.entity.Ingredient;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.service.ProfitCalculationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuItemRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ProfitCalculationRecordRepository recordRepository;

    @Override
    public ProfitCalculationRecord calculateProfit(Long menuItemId) {

        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu Item Not Found"));

        List<RecipeIngredient> recipeIngredients =
                recipeIngredientRepository.findByMenuItem(menuItem);

        BigDecimal totalCost = BigDecimal.ZERO;

        for (RecipeIngredient ri : recipeIngredients) {

            Ingredient ing = ri.getIngredient();

            BigDecimal costPerKg = ing.getCostPerUnit();   // stored per KG
            BigDecimal qtyGram   = BigDecimal.valueOf(ri.getQuantityRequired());

            // convert grams → kg
            BigDecimal qtyKg = qtyGram.divide(
                    BigDecimal.valueOf(1000),
                    4,
                    RoundingMode.HALF_UP
            );

            BigDecimal ingredientCost = costPerKg.multiply(qtyKg);

            totalCost = totalCost.add(ingredientCost);
        }

        BigDecimal sellingPrice = BigDecimal.valueOf(menuItem.getSellingPrice());
        BigDecimal profit       = sellingPrice.subtract(totalCost);

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profit);
        record.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return recordRepository.save(record);
    }

    @Override
    public ProfitCalculationRecord getCalculationById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    @Override
    public List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId) {
        return recordRepository.findByMenuItemId(menuItemId);
    }

    @Override
    public List<ProfitCalculationRecord> getAllCalculations() {
        return recordRepository.findAll();
    }
}
