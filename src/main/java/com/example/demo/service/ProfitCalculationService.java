package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;

import java.math.BigDecimal;
import java.util.List;
@Service
public class ProfitCalculationService {

    private final MenuItemRepository menuItemRepo;
    private final RecipeIngredientRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final ProfitCalculationRecordRepository recordRepo;

    public ProfitCalculationService(MenuItemRepository menuItemRepo,
                                    RecipeIngredientRepository recipeRepo,
                                    IngredientRepository ingredientRepo,
                                    ProfitCalculationRecordRepository recordRepo) {
        this.menuItemRepo = menuItemRepo;
        this.recipeRepo = recipeRepo;
        this.ingredientRepo = ingredientRepo;
        this.recordRepo = recordRepo;
    }

    public ProfitCalculationRecord calculateProfit(Long menuItemId) {

        MenuItem menuItem = menuItemRepo.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        List<RecipeIngredient> ingredients = recipeRepo.findAll();

        BigDecimal totalCost = BigDecimal.ZERO;

        for (RecipeIngredient ri : ingredients) {
            if (ri.getMenuItem().getId().equals(menuItemId)) {
                BigDecimal cost = ri.getIngredient()
                        .getCostPerUnit()
                        .multiply(BigDecimal.valueOf(ri.getQuantityRequired()));
                totalCost = totalCost.add(cost);
            }
        }

        BigDecimal profit = menuItem.getSellingPrice().subtract(totalCost);

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profit);

        return recordRepo.save(record);
    }

    public ProfitCalculationRecord getCalculationById(Long id) {
        return recordRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
    }

    public List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId) {
        return recordRepo.findByMenuItemId(menuItemId);
    }

    public List<ProfitCalculationRecord> getAllCalculations() {
        return recordRepo.findAll();
    }
}
