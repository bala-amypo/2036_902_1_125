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

    private final IngredientRepository repo;

    public IngredientServiceImpl(IngredientRepository repo){
        this.repo = repo;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {

        if (ingredient.getCostPerUnit() == null ||
                ingredient.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Invalid cost per unit");
        }

        ingredient.setActive(true);

        return repo.save(ingredient);
    }

    @Override
    public Ingredient updateIngredient(Long id, Ingredient updated) {

        Ingredient ing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

        if (updated.getCostPerUnit() == null ||
                updated.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Invalid cost per unit");
        }

        ing.setName(updated.getName());
        ing.setUnit(updated.getUnit());
        ing.setCostPerUnit(updated.getCostPerUnit());

        return repo.save(ing);
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return repo.findAll();
    }

    @Override
    public void deactivateIngredient(Long id) {

        Ingredient ing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

        ing.setActive(false);

        repo.save(ing);
    }
}
