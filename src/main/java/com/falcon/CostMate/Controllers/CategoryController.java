package com.falcon.CostMate.Controllers;


import com.falcon.CostMate.Entity.Category;
import com.falcon.CostMate.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Get all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Add a new category
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Map<String, Object> requestBody) {
        // Parse the category name from the request body
        String categoryName = (String) requestBody.get("name");
        // Validate the category name
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Category name cannot be empty");
        }
        // Save the category using the service
        Category category = categoryService.addCategory(categoryName);
        return ResponseEntity.ok(category);
    }
    // Get a category by ID
    @GetMapping("/{cid}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long cid) {
        Category category = categoryService.getCategoryById(cid);
        return ResponseEntity.ok(category);
    }
    // Update a category
    @PutMapping("/{cid}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long cid,
            @RequestBody Category updatedCategory
    ) {
        Category category = categoryService.updateCategory(cid, updatedCategory);
        return ResponseEntity.ok(category);
    }
    // Delete a category
    @DeleteMapping("/{cid}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long cid) {
        categoryService.deleteCategory(cid);
        return ResponseEntity.ok("Category deleted successfully");
    }

}