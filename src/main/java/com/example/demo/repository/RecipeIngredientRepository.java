package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientRepository
        extends JpaRepository<RecipeIngredient, Long> {

    // REQUIRED
    List<RecipeIngredient> findByMenuItemId(Long menuItemId);

    // REQUIRED
    boolean existsByMenuItemId(Long menuItemId);

    // REQUIRED BY TESTS (VERY IMPORTANT)
    @Query("""
        SELECT COALESCE(SUM(ri.quantityRequired), 0)
        FROM RecipeIngredient ri
        WHERE ri.ingredient.id = :ingredientId
    """)
    Double getTotalQuantityByIngredientId(
            @Param("ingredientId") Long ingredientId
    );
}
