package com.falcon.CostMate.Controllers;

import com.falcon.CostMate.Entity.UserAddedCategory;
import com.falcon.CostMate.Services.UserAddedCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/my_categories")
public class UserAddedCategoryController {

    @Autowired
    private UserAddedCategoryService userAddedCategoryService;

    /**
     * GET all categories in a specific group
     * Example: GET /api/v1/my_categories/group/5
     */
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<UserAddedCategory>> getAllCategoriesByGroup(@PathVariable Long groupId) {
        List<UserAddedCategory> categories = userAddedCategoryService.getAllUserAddedCategories(groupId);
        return ResponseEntity.ok(categories);
    }

    /**
     * POST (Create) a new category in a specific group
     * Example: POST /api/v1/my_categories/group/5
     * Body: { "name": "New Category" }
     */
    @PostMapping("/group/{groupId}")
    public ResponseEntity<UserAddedCategory> createCategory(
            @PathVariable Long groupId,
            @RequestBody Map<String, String> requestBody) {

        String categoryName = requestBody.get("name");
        UserAddedCategory savedCategory = userAddedCategoryService.addUserAddedCategory(categoryName, groupId);
        return ResponseEntity.ok(savedCategory);
    }

    /**
     * GET a single category by its ID
     * Example: GET /api/v1/my_categories/10
     */
    @GetMapping("/{cid}")
    public ResponseEntity<UserAddedCategory> getCategoryById(@PathVariable Long cid) {
        UserAddedCategory category = userAddedCategoryService.getUserAddedCategoryById(cid);
        return ResponseEntity.ok(category);
    }

    /**
     * PUT (Update) a category by its ID
     * Example: PUT /api/v1/my_categories/10
     * Body: { "name": "Updated Category Name" }
     */
    @PutMapping("/{cid}")
    public ResponseEntity<UserAddedCategory> updateCategory(
            @PathVariable Long cid,
            @RequestBody UserAddedCategory updatedData) {

        UserAddedCategory updatedCategory = userAddedCategoryService.updateUserAddedCategory(cid, updatedData);
        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * DELETE a category by its ID
     * Example: DELETE /api/v1/my_categories/10
     */
    @DeleteMapping("/{cid}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long cid) {
        userAddedCategoryService.deleteUserAddedCategory(cid);
        return ResponseEntity.ok("Category (ID " + cid + ") deleted successfully.");
    }
}