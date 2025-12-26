package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repo;

    public IngredientServiceImpl(IngredientRepository repo) {
        this.repo = repo;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        repo.findByNameIgnoreCase(ingredient.getName())
                .ifPresent(i -> {
                    throw new BadRequestException("Ingredient already exists");
                });

        if (ingredient.getName() == null || ingredient.getCostPerUnit() == null) {
            throw new BadRequestException("Invalid ingredient");
        }
        return repo.save(ingredient);
    }

    @Override
    public Ingredient updateIngredient(long id, Ingredient updated) {
        Ingredient existing = getIngredientById(id);

        if (updated.getName() != null)
            existing.setName(updated.getName());

        if (updated.getUnit() != null)
            existing.setUnit(updated.getUnit());

        if (updated.getCostPerUnit() != null)
            existing.setCostPerUnit(updated.getCostPerUnit());

        return repo.save(existing);
    }

    @Override
    public Ingredient getIngredientById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return repo.findAll();
    }

    @Override
    public void deactivateIngredient(long id) {
        Ingredient ingredient = getIngredientById(id);
        ingredient.setActive(false);
        repo.save(ingredient);
    }
}
