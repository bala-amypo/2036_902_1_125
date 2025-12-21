package com.example.demo.service;

import com.example.demo.entity.MenuItem;
import java.util.List;

public interface MenuItemService {

    MenuItem create(MenuItem item);

    MenuItem update(Long id, MenuItem item);

    MenuItem getById(Long id);

    List<MenuItem> getAll();

    void deactivate(Long id);
}
