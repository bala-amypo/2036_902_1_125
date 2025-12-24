package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeIngredientRepository
        extends JpaRepository<RecipeIngredient, Long> {

    // ✅ REQUIRED by services & profit calculation
    List<RecipeIngredient> findByMenuItemId(Long menuItemId);

    // ✅ REQUIRED by MenuItemService
    boolean existsByMenuItemId(Long menuItemId);

    // ✅ REQUIRED by tests & service
    @Query("SELECT SUM(r.quantityRequired) FROM RecipeIngredient r WHERE r.ingredient.id = :ingredientId")
    Double getTotalQuantityByIngredientId(Long ingredientId);
}
