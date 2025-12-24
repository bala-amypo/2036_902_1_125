package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ProfitCalculationService;

import java.math.BigDecimal;
import java.util.List;

public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuRepo;
    private final RecipeIngredientRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final ProfitCalculationRecordRepository recordRepo;

    public ProfitCalculationServiceImpl(MenuItemRepository menuRepo,
                                        RecipeIngredientRepository recipeRepo,
                                        IngredientRepository ingredientRepo,
                                        ProfitCalculationRecordRepository recordRepo) {
        this.menuRepo = menuRepo;
        this.recipeRepo = recipeRepo;
        this.ingredientRepo = ingredientRepo;
        this.recordRepo = recordRepo;
    }

    @Override
    public ProfitCalculationRecord calculateProfit(Long menuItemId) {
        MenuItem menuItem = menuRepo.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        List<RecipeIngredient> ingredients = recipeRepo.findAll()
                .stream()
                .filter(r -> r.getMenuItem().getId().equals(menuItemId))
                .toList();

        if (ingredients.isEmpty()) {
            throw new BadRequestException("No ingredients found");
        }

        BigDecimal totalCost = BigDecimal.ZERO;

        for (RecipeIngredient ri : ingredients) {
            totalCost = totalCost.add(
                    ri.getIngredient().getCostPerUnit()
                            .multiply(BigDecimal.valueOf(ri.getQuantityRequired()))
            );
        }

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfitMargin(menuItem.getSellingPrice().subtract(totalCost));

        return recordRepo.save(record);
    }

    @Override
    public ProfitCalculationRecord getCalculationById(Long id) {
        return recordRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
    }

    @Override
    public List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId) {
        return recordRepo.findByMenuItemId(menuItemId);
    }

    @Override
    public List<ProfitCalculationRecord> getAllCalculations() {
        return recordRepo.findAll();
    }
}
