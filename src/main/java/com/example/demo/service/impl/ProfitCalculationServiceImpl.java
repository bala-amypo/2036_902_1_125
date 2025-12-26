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

    private final MenuItemRepository menuItemRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final ProfitCalculationRecordRepository profitCalculationRecordRepository;

    // ✅ EXACT ORDER REQUIRED BY TESTS
    public ProfitCalculationServiceImpl(
            MenuItemRepository menuItemRepository,
            RecipeIngredientRepository recipeIngredientRepository,
            IngredientRepository ingredientRepository,
            ProfitCalculationRecordRepository profitCalculationRecordRepository
    ) {
        this.menuItemRepository = menuItemRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.profitCalculationRecordRepository = profitCalculationRecordRepository;
    }

    // rest of your methods stay SAME
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

