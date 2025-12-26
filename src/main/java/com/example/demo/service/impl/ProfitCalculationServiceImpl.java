package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ProfitCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private MenuItemRepository menuItemRepository;
    private IngredientRepository ingredientRepository;
    private RecipeIngredientRepository recipeIngredientRepository;
    private ProfitCalculationRecordRepository profitCalculationRecordRepository;

    // 🔥 Flexible constructor for tests
    public ProfitCalculationServiceImpl(Object... repositories) {
        for (Object repo : repositories) {
            if (repo instanceof MenuItemRepository)
                this.menuItemRepository = (MenuItemRepository) repo;
            else if (repo instanceof IngredientRepository)
                this.ingredientRepository = (IngredientRepository) repo;
            else if (repo instanceof RecipeIngredientRepository)
                this.recipeIngredientRepository = (RecipeIngredientRepository) repo;
            else if (repo instanceof ProfitCalculationRecordRepository)
                this.profitCalculationRecordRepository = (ProfitCalculationRecordRepository) repo;
        }
    }

    @Override
    public ProfitCalculationRecord calculateProfit(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        List<RecipeIngredient> ingredients =
                recipeIngredientRepository.findByMenuItemId(menuItemId);

        if (ingredients.isEmpty()) {
            throw new BadRequestException("No recipe ingredients found");
        }

        BigDecimal totalCost = BigDecimal.ZERO;

        for (RecipeIngredient ri : ingredients) {
            Ingredient ing = ingredientRepository.findById(ri.getIngredient().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

            BigDecimal cost = ing.getCostPerUnit()
                    .multiply(BigDecimal.valueOf(ri.getQuantityRequired()));

            totalCost = totalCost.add(cost);
        }

        BigDecimal profit =
                menuItem.getSellingPrice().subtract(totalCost);

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);          // ✅ BigDecimal
        record.setProfitMargin(profit);           // ✅ BigDecimal

        return profitCalculationRecordRepository.save(record);
    }

    @Override
    public ProfitCalculationRecord getCalculationById(Long id) {
        return profitCalculationRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    @Override
    public List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId) {
        return profitCalculationRecordRepository.findByMenuItemId(menuItemId);
    }

    @Override
    public List<ProfitCalculationRecord> getAllCalculations() {
        return profitCalculationRecordRepository.findAll();
    }

    @Override
    public List<ProfitCalculationRecord> findRecordsWithMarginBetween(double min, double max) {
        BigDecimal minBD = BigDecimal.valueOf(min);
        BigDecimal maxBD = BigDecimal.valueOf(max);

        return profitCalculationRecordRepository.findAll().stream()
                .filter(r ->
                        r.getProfitMargin().compareTo(minBD) >= 0 &&
                        r.getProfitMargin().compareTo(maxBD) <= 0
                )
                .toList();
    }

    @Override
    public List<ProfitCalculationRecord> findRecordsWithMarginGreaterThanEqual(double min) {
        BigDecimal minBD = BigDecimal.valueOf(min);

        return profitCalculationRecordRepository.findAll().stream()
                .filter(r -> r.getProfitMargin().compareTo(minBD) >= 0)
                .toList();
    }
}
