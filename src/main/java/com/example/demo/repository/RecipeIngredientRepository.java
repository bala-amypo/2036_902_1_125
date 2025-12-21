package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    List<RecipeIngredient> findByMenuItem(MenuItem menuItem);
}
