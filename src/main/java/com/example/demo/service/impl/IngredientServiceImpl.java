package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.service.IngredientService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {

        if (ingredient.getCostPerUnit() == null
                || ingredient.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Cost per unit");
        }

        ingredient.setActive(true);

        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient updateIngredient(Long id, Ingredient updated) {

        Ingredient ing = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (updated.getCostPerUnit() == null
                || updated.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Cost per unit");
        }

        ing.setName(updated.getName());
        ing.setUnit(updated.getUnit());
        ing.setCostPerUnit(updated.getCostPerUnit());

        return ingredientRepository.save(ing);
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public void deactivateIngredient(Long id) {

        Ingredient ing = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        ing.setActive(false);

        ingredientRepository.save(ing);
    }
}
