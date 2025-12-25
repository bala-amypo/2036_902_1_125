package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.ProfitCalculationService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuItemRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final ProfitCalculationRecordRepository recordRepository;

   public ProfitCalculationServiceImpl(
        IngredientRepository ingredientRepository,
        MenuItemRepository menuItemRepository,
        RecipeIngredientRepository recipeIngredientRepository,
        ProfitCalculationRecordRepository recordRepository) {

    this.ingredientRepository = ingredientRepository;
    this.menuItemRepository = menuItemRepository;
    this.recipeIngredientRepository = recipeIngredientRepository;
    this.recordRepository = recordRepository;
}


    @Override
    public ProfitCalculationRecord calculateProfit(Long menuItemId) {

        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        List<RecipeIngredient> ingredients =
                recipeIngredientRepository.findByMenuItemId(menuItemId);

        if (ingredients.isEmpty()) {
            throw new BadRequestException("No ingredients for menu item");
        }

        BigDecimal totalCost = BigDecimal.ZERO;

        for (RecipeIngredient ri : ingredients) {
            Ingredient ing = ingredientRepository.findById(ri.getIngredient().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

           BigDecimal cost =
        BigDecimal.valueOf(ing.getCostPerUnit())
                .multiply(BigDecimal.valueOf(ri.getQuantityRequired()));

            totalCost = totalCost.add(cost);
        }

        BigDecimal profitMargin =
                menuItem.getSellingPrice().subtract(totalCost);

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profitMargin);

        return recordRepository.save(record);
    }

    @Override
    public ProfitCalculationRecord getCalculationById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
    }

    @Override
    public List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId) {
        return recordRepository.findByMenuItemId(menuItemId);
    }

    @Override
    public List<ProfitCalculationRecord> getAllCalculations() {
        return recordRepository.findAll();
    }
    @Override
public List<ProfitCalculationRecord>
findRecordsWithMarginBetween(double min, double max) {

    return recordRepository.findByProfitMarginBetween(
            BigDecimal.valueOf(min),
            BigDecimal.valueOf(max)
    );
}

}
