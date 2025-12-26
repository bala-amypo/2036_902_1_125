package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    List<RecipeIngredient> findByMenuItemId(Long menuItemId);

    boolean existsByMenuItemId(Long menuItemId);

    // REQUIRED BY TESTS
    @Query("""
        SELECT COALESCE(SUM(ri.quantityRequired), 0)
        FROM RecipeIngredient ri
        WHERE ri.ingredient.id = :ingredientId
    """)
    double getTotalQuantityByIngredientId(long ingredientId);
}
