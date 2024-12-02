package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.TransactionItem;
import com.falcon.CostMate.Repositories.TransactionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionItemService {


    private final TransactionItemRepository itemRepository;

    public List<TransactionItem> getItemsByCategory(String category){
        List<TransactionItem> items = itemRepository.findByCategory(category).get();
    	if(!items.isEmpty()) {
    		return items;    		
    	}
    	else {
    		throw new RuntimeException("Items not found");
    	}
    }
    
    public List<TransactionItem> getAllItems(){
    	List<TransactionItem> items = itemRepository.findAll();
    	if(!items.isEmpty()) {
    		return items;
    	}
    	throw new RuntimeException("Items not found");
    }
    
    public List<TransactionItem> getItemsByYearAndMonth(int month, int year){
    	List<TransactionItem> items = itemRepository.findByMonthAndYear(month, year);
    	if(!items.isEmpty()) {
    		return items;
    	}
    	throw new RuntimeException("Items not found");
    }
    
    
    
    public String addItem(TransactionItem item){
        try {
            itemRepository.save(item);
        } catch (Exception e){
            return "Error adding item: " + e.getMessage();
        }
        return "Item added: " + item;
    }

    public String deleteItem(TransactionItem item){
        return "Item deleted: " + item;
    }

    public String updateItem(TransactionItem item){
        try {
            return "updated";
        }
        catch (Exception e){
            return "Error updating item: " + e.getMessage();
        }
    }



}
