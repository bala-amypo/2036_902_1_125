package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.ProfitCalculationService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final ProfitCalculationRecordRepository recordRepository;
    private final MenuItemRepository menuItemRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public ProfitCalculationServiceImpl(
            ProfitCalculationRecordRepository recordRepository,
            MenuItemRepository menuItemRepository,
            RecipeIngredientRepository recipeIngredientRepository
    ) {
        this.recordRepository = recordRepository;
        this.menuItemRepository = menuItemRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @Override
    public ProfitCalculationRecord calculateProfit(Long menuItemId) {

        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        double sellingPrice = menuItem.getSellingPrice();

        // ✅ REAL TOTAL COST
        double totalCost = 0.0;

        List<RecipeIngredient> recipeIngredients =
                recipeIngredientRepository.findByMenuItem(menuItem);

        for (RecipeIngredient ri : recipeIngredients) {
            double quantity = ri.getQuantityRequired();
            double costPerUnit = ri.getIngredient().getCostPerUnit();
            totalCost += quantity * costPerUnit;
        }

        double profitMargin = sellingPrice - totalCost;

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profitMargin);
        record.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return recordRepository.save(record);
    }

    @Override
    public ProfitCalculationRecord getCalculationById(Long id) {
        return recordRepository.findById(id).orElse(null);
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
