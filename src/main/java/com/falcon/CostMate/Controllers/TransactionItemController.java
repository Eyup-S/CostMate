package com.falcon.CostMate.Controllers;


import com.falcon.CostMate.Entity.TransactionItem;
import com.falcon.CostMate.Services.TransactionItemService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<TransactionItem>> getItemsByCategory(@PathVariable String category){
    	try {
    		return ResponseEntity.ok(itemService.getItemsByCategory(category));
    	}
    	catch(Exception e) {
    		return ResponseEntity.noContent().build();
    	}
        
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
    public String addItem(@RequestBody TransactionItem item){
        return itemService.addItem(item);
    }


}
