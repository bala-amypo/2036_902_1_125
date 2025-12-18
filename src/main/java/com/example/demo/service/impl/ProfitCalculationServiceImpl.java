package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.service.ProfitCalculationService;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuRepo;
    private final RecipeIngredientRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final ProfitCalculationRecordRepository recordRepo;

    public ProfitCalculationServiceImpl(
            MenuItemRepository menuRepo,
            RecipeIngredientRepository recipeRepo,
            IngredientRepository ingredientRepo,
            ProfitCalculationRecordRepository recordRepo
    ){
        this.menuRepo = menuRepo;
        this.recipeRepo = recipeRepo;
        this.ingredientRepo = ingredientRepo;
        this.recordRepo = recordRepo;
    }

    @Override
    public ProfitCalculationRecord calculateProfit(Long menuItemId){

        MenuItem item = menuRepo.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        BigDecimal cost = recipeRepo.findAll().stream()
                .filter(r -> r.getMenuItem().getId().equals(menuItemId))
                .map(r -> r.getIngredient().getCostPerUnit()
                        .multiply(BigDecimal.valueOf(r.getQuantityRequired())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(item);
        record.setTotalCost(cost);
        record.setProfitMargin(item.getSellingPrice().subtract(cost));

        return recordRepo.save(record);
    }

    @Override
    public ProfitCalculationRecord getCalculationById(Long id){
        return recordRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId){
        return recordRepo.findByMenuItemId(menuItemId);
    }

    @Override
    public List<ProfitCalculationRecord> getAllCalculations(){
        return recordRepo.findAll();
    }
}
