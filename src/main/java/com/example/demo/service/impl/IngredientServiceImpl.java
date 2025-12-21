package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repo;

    public IngredientServiceImpl(IngredientRepository repo){
        this.repo = repo;
    }

    @Override
    public Ingredient create(Ingredient ingredient){
        ingredient.setActive(true);
        return repo.save(ingredient);
    }

    @Override
    public Ingredient update(Long id, Ingredient ingredient){
        Ingredient ing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        ing.setName(ingredient.getName());
        ing.setUnit(ingredient.getUnit());
        ing.setCostPerUnit(ingredient.getCostPerUnit());

        return repo.save(ing);
    }

    @Override
    public Ingredient getById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }

    @Override
    public List<Ingredient> getAll(){
        return repo.findAll();
    }

    @Override
    public void deactivate(Long id){
        Ingredient ing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        ing.setActive(false);

        repo.save(ing);
    }
}
