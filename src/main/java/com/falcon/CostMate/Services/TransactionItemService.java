package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.*;
import com.falcon.CostMate.Repositories.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TransactionItemService {


    private final TransactionItemRepository itemRepository;
    private final AppUserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final BalanceRepository balanceRepository;
	private final SharesRepository sharesRepository;

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

	public TransactionItem addItem(TransactionItem item) throws Exception {
		List<Balances> balances = new ArrayList<>();
		if (item.getAddedBy() != null) {
			AppUser addedBy = userRepository.findByUsername(item.getAddedBy().getUsername())
					.orElseThrow(() -> new RuntimeException("User for 'addedBy' not found"));
			item.setAddedBy(addedBy);
		} else {
			throw new RuntimeException("'addedBy' user is required");
		}

		for(Shares share: item.getShares()){
			Balances balance;
			Optional<AppUser> user = userRepository.findById(share.getUser().getUid());
			if(user.isPresent()){
				if(balanceRepository.findByUser(user.get()).isPresent()){
					balance = balanceRepository.findByUser(user.get()).get();
				}
				else {
					balance = new Balances();
					balance.setUser(user.get());
				}
				balance.setPaidAmount(balance.getPaidAmount() + share.getPaidAmount());
				balance.setOwedAmount(balance.getOwedAmount() + share.getOwedAmount());
				share.setTransaction(item);

				if(item.getGroup() == null){
					throw new Exception("Group is null");
				}
				balance.setGroup(item.getGroup());
				balanceRepository.save(balance);
			}
			else {
				throw new Exception("User Not found");
			}
		}
		return itemRepository.save(item);
	}


	public boolean deleteItem(Long itemId, Long groupId) throws Exception{
    	try {
			System.out.println("item id: " + itemId);
			Optional<TransactionItem> item = itemRepository.findById(itemId);
			if(item.isPresent()){
				itemRepository.delete(item.get());
			}
			else{
				throw new Exception("item not found");
			}
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
            	updatedItem.setAddedBy(item.getAddedBy());
            	updatedItem.setAddedDate(item.getAddedDate());
            	updatedItem.setBoughtDate(item.getBoughtDate());
            	updatedItem.setPrice(item.getPrice());
				updatedItem.setShares(item.getShares());
            	return itemRepository.save(updatedItem);
            }
            else {
            	throw new RuntimeException("Item could not be updated");
            }
    }


    public List<TransactionItem> getItemsByGroup(Long groupId){
		return itemRepository.findByGroup_Gid(groupId);
	}


}
