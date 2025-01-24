package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.Category;
import com.falcon.CostMate.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Fetch all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Add a new category
    public Category addCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        return categoryRepository.save(category);
    }

    // Get a category by ID
    public Category getCategoryById(Long cid) {
        return categoryRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Category not found: ID " + cid));
    }
    // Update a category
    public Category updateCategory(Long cid, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Category not found: ID " + cid));

        existingCategory.setName(updatedCategory.getName());
        return categoryRepository.save(existingCategory);
    }
    // Delete a category
    public void deleteCategory(Long cid) {
        Category category = categoryRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Category not found: ID " + cid));
        categoryRepository.delete(category);
    }

    // Additional business logic can be added here (e.g., filtering categories)
}