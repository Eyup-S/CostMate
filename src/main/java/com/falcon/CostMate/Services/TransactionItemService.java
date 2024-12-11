package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Entity.Category;
import com.falcon.CostMate.Entity.TransactionItem;
import com.falcon.CostMate.Repositories.CategoryRepository;
import com.falcon.CostMate.Repositories.TransactionItemRepository;
import com.falcon.CostMate.Repositories.AppUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TransactionItemService {


    private final TransactionItemRepository itemRepository;
    private final AppUserRepository userRepository;
	private final CategoryRepository categoryRepository;

    public List<TransactionItem> getItemsByCategory(String categoryName){
		Category category = categoryRepository.findByName(categoryName)
				.orElseThrow(() -> new RuntimeException("Category not found"));

		List<TransactionItem> items = itemRepository.findByCategory(category).orElseThrow(() -> new RuntimeException("Items not found"));
		return items;
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

	public TransactionItem addItem(TransactionItem item) {
		if (item.getAddedBy() != null) {
			AppUser addedBy = userRepository.findById(item.getAddedBy().getUid())
					.orElseThrow(() -> new RuntimeException("User for 'addedBy' not found"));
			item.setAddedBy(addedBy);
		} else {
			throw new RuntimeException("'addedBy' user is required");
		}

		if (item.getPaidBy() != null && !item.getPaidBy().isEmpty()) {
			Set<AppUser> paidByUsers = new HashSet<>();
			for (AppUser paidByUser : item.getPaidBy()) {
				AppUser user = userRepository.findById(paidByUser.getUid())
						.orElseThrow(() -> new RuntimeException("User in 'paidBy' not found: ID " + paidByUser.getUid()));
				paidByUsers.add(user);
			}
			item.setPaidBy(paidByUsers);
		}

		if (item.getCategory() != null) {
			Category category = categoryRepository.findById(item.getCategory().getCid())
					.orElseThrow(() -> new RuntimeException("Category not found: ID " + item.getCategory().getCid()));
			item.setCategory(category);
		} else {
			throw new RuntimeException("Category is required");
		}

		if (item.getSharedWith() != null && !item.getSharedWith().isEmpty()) {
			List<AppUser> sharedWithUsers = new ArrayList<>();
			for (AppUser sharedUser : item.getSharedWith()) {
				AppUser user = userRepository.findById(sharedUser.getUid())
						.orElseThrow(() -> new RuntimeException("User in 'sharedWith' not found: ID " + sharedUser.getUid()));
				sharedWithUsers.add(user);
			}
			item.setSharedWith(sharedWithUsers);
		}

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
            	updatedItem.setCategory(item.getCategory());
            	updatedItem.setStatus(item.getStatus());
            	updatedItem.setAddedBy(item.getAddedBy());
            	updatedItem.setPaidBy(item.getPaidBy());
            	updatedItem.setAddedDate(item.getAddedDate());
            	updatedItem.setBoughtDate(item.getBoughtDate());
            	updatedItem.setSharedWith(item.getSharedWith());
            	updatedItem.setPrice(item.getPrice());
            	return itemRepository.save(updatedItem);
            }
            else {
            	throw new RuntimeException("Item could not be updated");
            }
    }
    
    public Map<String, List<TransactionItem>> getItemsGroupedByCategory(){
    	Map<String, List<TransactionItem>> byCategory = new HashMap<>();
    	List<TransactionItem> items = itemRepository.findAll();
    	List<String> categories = new ArrayList<>();
    	
    	
     	items.forEach((item_) -> {
     		if(byCategory.containsKey(item_.getCategory())) {
     			byCategory.get(item_.getCategory()).add(item_);
     		}
     		else {
     			byCategory.put(item_.getCategory().getName(), new ArrayList<>(Arrays.asList(item_)));
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
