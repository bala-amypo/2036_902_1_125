package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    // Used in ProfitCalculationService
    List<RecipeIngredient> findByMenuItemId(Long menuItemId);

    // Used in MenuItemService (deactivate protection)
    boolean existsByMenuItemId(Long menuItemId);

    // Used in RecipeIngredientService (total quantity)
    @Query("""
        SELECT COALESCE(SUM(ri.quantityRequired), 0)
        FROM RecipeIngredient ri
        WHERE ri.ingredient.id = :ingredientId
    """)
    double sumQuantityByIngredientId(@Param("ingredientId") Long ingredientId);
}
