package com.example.demo.repository;

import com.example.demo.entity.MenuItem;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findByNameIgnoreCase(String name);

    @Query("""
        SELECT DISTINCT m FROM MenuItem m
        LEFT JOIN FETCH m.categories
        WHERE m.active = true
    """)
    List<MenuItem> findAllActiveWithCategories();

    @Query("""
        SELECT m FROM MenuItem m
        LEFT JOIN FETCH m.categories
        WHERE m.id = :id
    """)
    Optional<MenuItem> findByIdWithCategories(Long id);
}
