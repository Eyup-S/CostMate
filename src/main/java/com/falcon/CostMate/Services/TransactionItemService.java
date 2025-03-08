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
		if (item.getAddedBy() != null) {
			Optional<AppUser> addedBy = userRepository.findById(item.getAddedBy().getUid());
			if(addedBy.isPresent())
				item.setAddedBy(addedBy.get());
			else {
				throw new Exception("addedby not found in db");
			}
		} else {
			throw new RuntimeException("'addedBy' user is required");
		}
		try {
			for (Shares share : item.getShares()) {
				Balances balance;
				Optional<AppUser> user = userRepository.findById(share.getUser().getUid());
				if (user.isPresent()) {
					try{
						balance = balanceRepository.findByUser_UidAndGroup_Gid(user.get().getUid(),item.getGroup().getGid());
					} catch (Exception e) {
						balance = new Balances();
						balance.setUser(user.get());
					}
					balance.setPaidAmount(balance.getPaidAmount() + share.getPaidAmount());
					balance.setOwedAmount(balance.getOwedAmount() + share.getOwedAmount());
					share.setTransaction(item);

					if (item.getGroup() == null) {
						throw new Exception("Group is null");
					}
					balance.setGroup(item.getGroup());
					balanceRepository.save(balance);
				} else {
					throw new Exception("User Not found");
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return itemRepository.save(item);
	}


	public boolean deleteItem(Long itemId, Long groupId) throws Exception{
    	try {
			System.out.println("item id: " + itemId);
			Optional<TransactionItem> item = itemRepository.findByIdWithShares(itemId);
			if(item.isPresent()){
				List<Shares> shares = item.get().getShares();
				for(Shares share: shares){
					Balances balance = balanceRepository.findByUser_UidAndGroup_Gid(share.getUser().getUid(), groupId);

					balance.setPaidAmount(balance.getPaidAmount() - share.getPaidAmount());
					balance.setOwedAmount(balance.getOwedAmount() - share.getOwedAmount());
					balanceRepository.save(balance);
					sharesRepository.delete(share);
				}
				itemRepository.delete(item.get());
				System.out.println("item deleted: " + itemId);
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
