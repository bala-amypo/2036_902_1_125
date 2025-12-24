package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.service.IngredientService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;

    public IngredientServiceImpl(IngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ingredient create(Ingredient ingredient) {
        ingredient.setActive(true);
        return repository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAll() {
        return repository.findAll();
    }

    @Override
    public Ingredient getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }

    @Override
    public Ingredient update(Long id, Ingredient ingredient) {

        Ingredient existing = getById(id);

        existing.setName(ingredient.getName());
        existing.setUnit(ingredient.getUnit());
        existing.setCostPerUnit(ingredient.getCostPerUnit());

        return repository.save(existing);
    }

    @Override
    public void deactivate(Long id) {
        Ingredient ingredient = getById(id);
        ingredient.setActive(false);
        repository.save(ingredient);
    }
}
