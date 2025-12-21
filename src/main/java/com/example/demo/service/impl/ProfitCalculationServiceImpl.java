package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.entity.Ingredient;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.service.ProfitCalculationService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuRepo;
    private final RecipeIngredientRepository recipeRepo;
    private final ProfitCalculationRecordRepository recordRepo;

    public ProfitCalculationServiceImpl(
            MenuItemRepository menuRepo,
            RecipeIngredientRepository recipeRepo,
            ProfitCalculationRecordRepository recordRepo
    ){
        this.menuRepo = menuRepo;
        this.recipeRepo = recipeRepo;
        this.recordRepo = recordRepo;
    }

    @Override
    public ProfitCalculationRecord calculateProfit(Long menuItemId) {

        MenuItem menuItem = menuRepo.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));

        List<RecipeIngredient> recipeIngredients = recipeRepo.findByMenuItem(menuItem);

        BigDecimal totalCost = BigDecimal.ZERO;

        for (RecipeIngredient ri : recipeIngredients) {

            Ingredient ing = ri.getIngredient();

            BigDecimal costPerUnit = ing.getCostPerUnit();
            BigDecimal qtyGram = BigDecimal.valueOf(ri.getQuantityRequired());

            BigDecimal qtyKg = qtyGram.divide(BigDecimal.valueOf(1000), 4, RoundingMode.HALF_UP);

            BigDecimal ingredientCost = costPerUnit.multiply(qtyKg);

            totalCost = totalCost.add(ingredientCost);
        }

        BigDecimal sellingPrice = menuItem.getSellingPrice();
        BigDecimal profit = sellingPrice.subtract(totalCost);

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profit);
        record.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return recordRepo.save(record);
    }

    @Override
    public ProfitCalculationRecord getById(Long id) {
        return recordRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    @Override
    public List<ProfitCalculationRecord> getHistoryByMenuItem(Long menuItemId) {
        return recordRepo.findByMenuItemId(menuItemId);
    }

    @Override
    public List<ProfitCalculationRecord> getAll() {
        return recordRepo.findAll();
    }
}
