package com.example.demo.repository;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientRepository
        extends JpaRepository<RecipeIngredient, Long> {

    List<RecipeIngredient> findByMenuItem(MenuItem menuItem);

    // ✅ REQUIRED BY RecipeIngredientService
    @Query("""
        SELECT COALESCE(SUM(r.quantityRequired), 0)
        FROM RecipeIngredient r
        WHERE r.ingredient.id = :ingredientId
    """)
    Double getTotalQuantityByIngredientId(@Param("ingredientId") Long ingredientId);
}
