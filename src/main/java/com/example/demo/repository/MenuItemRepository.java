package com.example.demo.repository;

import com.example.demo.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findByNameIgnoreCase(String name);

    // 🔥 REQUIRED FOR UPDATE TESTS
    @Query("""
        SELECT m FROM MenuItem m
        LEFT JOIN FETCH m.categories
        WHERE m.id = :id
    """)
    Optional<MenuItem> findByIdWithCategories(@Param("id") Long id);

    // 🔥 REQUIRED FOR getAllMenuItems test
    @Override
    List<MenuItem> findAll();

    // 🔥 REQUIRED BY HQL TEST
    @Query("""
        SELECT DISTINCT m FROM MenuItem m
        LEFT JOIN FETCH m.categories
        WHERE m.active = true
    """)
    List<MenuItem> findAllActiveWithCategories();
}
