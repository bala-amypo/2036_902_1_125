package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientRepository
        extends JpaRepository<RecipeIngredient, Long> {

    // ✅ Used in ProfitCalculationServiceImpl
    List<RecipeIngredient> findByMenuItemId(Long menuItemId);

    // ✅ Used in RecipeIngredientServiceImpl.getTotalQuantityOfIngredient()
    List<RecipeIngredient> findByIngredientId(Long ingredientId);

    // ✅ Used in MenuItem activation validation
    boolean existsByMenuItemId(Long menuItemId);
}
