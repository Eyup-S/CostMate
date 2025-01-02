package com.falcon.CostMate.Controllers;


import com.falcon.CostMate.Entity.Category;
import com.falcon.CostMate.Entity.TransactionItem;
import com.falcon.CostMate.Repositories.CategoryRepository;
import com.falcon.CostMate.Repositories.TransactionItemRepository;
import com.falcon.CostMate.Services.TransactionItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/splitApp")
@RequiredArgsConstructor
public class TransactionItemController {

    private final TransactionItemService itemService;

    
    @GetMapping("/items")
    public ResponseEntity<List<TransactionItem>> getAllItems(){
    	try {
    		return ResponseEntity.ok(itemService.getAllItems());
    	}
    	catch(Exception e) {
    		return ResponseEntity.noContent().build();
    	}

    }

    @GetMapping("/items/{category}")
    public ResponseEntity<List<TransactionItem>> getItemsByCategory(@PathVariable String categoryName){
        List<TransactionItem> items = itemService.getItemsByCategory(categoryName);
        if(!items.isEmpty()){
            return ResponseEntity.ok(items);
        }
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/items/years/{year}/months/{month}")
    public ResponseEntity<List<TransactionItem>> getItemsByMonthAndYear(@PathVariable("year") int year, @PathVariable("month") int month){
    	try {
    		return ResponseEntity.ok(itemService.getItemsByYearAndMonth(month, year));
    	}
    	catch(Exception e) {
    		return ResponseEntity.noContent().build();
    	}
    }

    @PostMapping("/items")
    public ResponseEntity<TransactionItem> addItem(@Valid @RequestBody TransactionItem item){
        System.out.println("📝 Incoming TransactionItem: " + item);
        return ResponseEntity.ok(itemService.addItem(item));
    }
    
    @PutMapping("/items/{id}")
    public ResponseEntity<TransactionItem> updateTransactionItem(@PathVariable("id") Long id, @RequestBody TransactionItem transactionItem) {
        try {
        	return ResponseEntity.ok(itemService.updateItem(id, transactionItem));
        }
        catch(Exception e) {
        	return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Long> deleteTransactionItem(@PathVariable Long id) {
        if(itemService.deleteItem(id)) {
        	return ResponseEntity.ok(id);
        }
        else {
        	return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/items/grouped-by-category")
    public ResponseEntity<Map<String, List<TransactionItem>>> getItemsGroupedByCategory() {
        try {
            return ResponseEntity.ok(itemService.getItemsGroupedByCategory());
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
    
    @GetMapping("/items/shared-with/{userId}")
    public ResponseEntity<List<TransactionItem>> getItemsSharedWithUser(@PathVariable("userId") Long userId) {
        try {
            return ResponseEntity.ok(itemService.getItemsSharedWithUser(userId));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
    
    @GetMapping("/items/paid-by/{userId}")
    public ResponseEntity<List<TransactionItem>> getItemsPaidByUser(@PathVariable("userId") Long userId) {
        try {
            return ResponseEntity.ok(itemService.getItemsPaidByUser(userId));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
    
    @PatchMapping("/items/{id}/toggle-status")
    public ResponseEntity<TransactionItem> toggleStatus(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(itemService.toggleStatus(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    

}
