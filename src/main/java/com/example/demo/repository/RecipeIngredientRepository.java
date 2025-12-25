package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeIngredientRepository
        extends JpaRepository<RecipeIngredient, Long> {

    List<RecipeIngredient> findByMenuItemId(Long menuItemId);

    List<RecipeIngredient> findByIngredientId(Long ingredientId);

    boolean existsByMenuItemId(Long menuItemId);

    // ✅ REQUIRED BY TEST
    @Query("""
        SELECT COALESCE(SUM(r.quantityRequired), 0)
        FROM RecipeIngredient r
        WHERE r.ingredient.id = :ingredientId
    """)
    Double getTotalQuantityByIngredientId(Long ingredientId);
}
