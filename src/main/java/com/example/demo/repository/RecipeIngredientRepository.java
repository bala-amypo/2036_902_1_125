package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    // Used by profit calculation
    List<RecipeIngredient> findByMenuItemId(Long menuItemId);

    // ✅ REQUIRED for GET /ingredient/{id}/total-quantity
    @Query("""
        SELECT COALESCE(SUM(ri.quantityRequired), 0)
        FROM RecipeIngredient ri
        WHERE ri.ingredient.id = :ingredientId
    """)
    double sumQuantityByIngredientId(@Param("ingredientId") Long ingredientId);
}
