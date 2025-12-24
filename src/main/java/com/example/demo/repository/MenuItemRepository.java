package com.example.demo.repository;

import com.example.demo.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    // REQUIRED BY TESTS
    Optional<MenuItem> findByNameIgnoreCase(String name);
}
