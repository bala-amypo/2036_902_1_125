package com.example.demo.repository;

import com.example.demo.entity.MenuItem;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findByNameIgnoreCase(String name);

    @Query("SELECT DISTINCT m FROM MenuItem m JOIN FETCH m.categories WHERE m.active = true")
    List<MenuItem> findAllActiveWithCategories();
}
