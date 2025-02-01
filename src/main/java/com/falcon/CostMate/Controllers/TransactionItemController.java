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

    @GetMapping("/items/categories/{categoryName}")
    public ResponseEntity<List<TransactionItem>> getItemsByCategory(@PathVariable("categoryName") String categoryName){
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
        try {
            System.out.println("📝 Incoming TransactionItem: " + item);
            return ResponseEntity.ok(itemService.addItem(item));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
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
    


    @GetMapping("/items/groups/{groupId}")
    public ResponseEntity<List<TransactionItem>> getItemsByGroup(@PathVariable("groupId") Long groupId){
        try{
            return ResponseEntity.ok(itemService.getItemsByGroup(groupId));
        }
        catch (Exception e){
            return ResponseEntity.noContent().build();
        }

    }
    
    

}
