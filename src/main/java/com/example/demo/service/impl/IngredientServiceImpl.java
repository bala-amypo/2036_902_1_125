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

    private final IngredientRepository repository;

    public IngredientServiceImpl(IngredientRepository repository) {
        this.repository = repository;
    }

    // ================= CREATE =================
    @Override
    public Ingredient create(Ingredient ingredient) {

        repository.findByNameIgnoreCase(ingredient.getName())
                .ifPresent(i -> {
                    throw new BadRequestException("Ingredient already exists");
                });

        if (ingredient.getCostPerUnit() == null ||
                ingredient.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Invalid cost per unit");
        }

        return repository.save(ingredient);
    }

    // ================= UPDATE (SAFE PARTIAL) =================
    @Override
    public Ingredient update(Long id, Ingredient updated) {

        Ingredient existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

        if (updated.getName() != null && !updated.getName().isBlank()) {
            existing.setName(updated.getName());
        }

        if (updated.getUnit() != null) {
            existing.setUnit(updated.getUnit());
        }

        if (updated.getCostPerUnit() != null) {
            if (updated.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BadRequestException("Invalid cost per unit");
            }
            existing.setCostPerUnit(updated.getCostPerUnit());
        }

        if (updated.getActive() != null) {
            existing.setActive(updated.getActive());
        }

        return repository.save(existing);
    }

    // ================= READ =================
    @Override
    public Ingredient getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));
    }

    @Override
    public List<Ingredient> getAll() {
        return repository.findAll();
    }

    // ================= DEACTIVATE =================
    @Override
    public void deactivate(Long id) {
        Ingredient ingredient = getById(id);
        ingredient.setActive(false);
        repository.save(ingredient);
    }
}
