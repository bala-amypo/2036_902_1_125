package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo){
        this.repo = repo;
    }

    @Override
    public Category create(Category category){
        category.setActive(true);
        return repo.save(category);
    }

    @Override
    public Category update(Long id, Category updated){

        Category cat = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        cat.setName(updated.getName());
        cat.setDescription(updated.getDescription());

        return repo.save(cat);
    }

    @Override
    public Category getById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAll(){
        return repo.findAll();
    }

    @Override
    public void deactivate(Long id){
        Category c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        c.setActive(false);

        repo.save(c);
    }
}
