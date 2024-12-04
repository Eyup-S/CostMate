package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Entity.TransactionItem;
import com.falcon.CostMate.Repositories.TransactionItemRepository;
import com.falcon.CostMate.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionItemService {


    private final TransactionItemRepository itemRepository;
    private final UserRepository userRepository;

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
    
    public TransactionItem addItem(TransactionItem item){
        return itemRepository.save(item);
        
    }
    
    public boolean deleteItem(Long id){
    	try {
    		itemRepository.deleteById(id);
    		return true;
    	}
    	catch(IllegalArgumentException e) {
    		throw new RuntimeException("Id is not valid.");
    	}
    	
    	
    }
    
    public TransactionItem updateItem(Long id,TransactionItem item){
            Optional<TransactionItem> dbItem = itemRepository.findById(id);
            if(dbItem.isPresent()) {
            	TransactionItem updatedItem = dbItem.get();
            	updatedItem.setName(item.getName());
            	updatedItem.setCategory(item.getCategory);
            	updatedItem.setStatus(item.getStatus);
            	updatedItem.setAddedBy(item.getAddedBy);
            	updatedItem.setPaidBy(item.getPaidBy);
            	updatedItem.setaddedDate(item.getAddedDate);
            	updatedItem.setboughtDate(item.getBoughtDate);
            	updatedItem.setsharedWith(item.getSharedWith);
            	updatedItem.setPrice(item.getPrice);
            	return itemRepository.save(updatedItem);
            }
            else {
            	throw new RuntimeException("Item could not be updated");
            }
    }
    
    public Map<String, List<TransactionItem>> getItemsGroupedByCategory(){
    	Map<String, List<TransactionItem>> byCategory;
    	List<TransactionItem> items = itemRepository.findAll();
    	List<String> categories = new ArrayList<>();
    	
    	
     	items.forEach((item_) -> {
     		if(byCategory.containsKey(item_.getCategory())) {
     			byCategory.get(item_.getCategory()).add(item_);
     		}
     		else {
     			byCategory.put(item_.getCategory(), new ArrayList<>(Arrays.asList(item_)));     		     			
     		}
     	});
     	
     	return byCategory;    	
    }
    
    //It seems unnecessary, may be removed
    public List<TransactionItem> getItemsSharedWithUser(Long userId){
    	AppUser user = userRepository.findById(userId).get();
    	return user.getPaidItems();
    }
    
    public List<TransactionItem> getItemsPaidByUser(Long userId){
    	AppUser user = userRepository.findById(userId).get();
    	return user.getPaidItems();
    }
    
    public TransactionItem toggleStatus(Long id) {
        TransactionItem dbItem = itemRepository.findById(id).get();
        dbItem.setStatus(!dbItem.getStatus());
        return itemRepository.save(dbItem);
        
    }
    
    




}
