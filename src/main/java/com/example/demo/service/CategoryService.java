package com.example.demo.service;

import com.example.demo.entity.Category;
import java.util.List;

public interface CategoryService {

    Category create(Category category);

    Category update(Long id, Category category);

    Category getById(Long id);

    List<Category> getAll();

    void deactivate(Long id);
}
