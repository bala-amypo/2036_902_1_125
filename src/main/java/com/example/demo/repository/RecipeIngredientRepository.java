package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    boolean existsByMenuItemId(Long menuItemId);

    @Query("SELECT SUM(r.quantityRequired) FROM RecipeIngredient r WHERE r.ingredient.id = :ingredientId")
    Double getTotalQuantityByIngredientId(@Param("ingredientId") Long ingredientId);
}
