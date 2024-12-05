package com.falcon.CostMate.Services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.falcon.CostMate.Entity.ShopItem;
import com.falcon.CostMate.Repositories.ShopItemRepository;
import com.falcon.CostMate.Repositories.UserRepository;

@Service
public class ShopItemService {
	
	private final ShopItemRepository shopItemRepository;
	private final UserRepository userRepository;
	
	public List<ShopItem> getAllItems(){
		return shopItemRepository.findAll();
	}
	
    public List<ShopItem> getShopItemsByListName(String listName) {
        return shopItemRepository.findByListName_Name(listName);
    }

    public ShopItem createShopItem(ShopItem shopItem) {
        shopItem.setCreatedTime(new Date());
        return shopItemRepository.save(shopItem);
    }

    public ShopItem updateShopItem(Long id, ShopItem shopItemDetails) {
        Optional<ShopItem> shopItemOptional = shopItemRepository.findById(id);

        if (shopItemOptional.isPresent()) {
            ShopItem shopItem = shopItemOptional.get();
            shopItem.setName(shopItemDetails.getName());
            shopItem.setAmount(shopItemDetails.getAmount());
            shopItem.setListName(shopItemDetails.getListName());
            shopItem.setStatus(shopItemDetails.getStatus());
            return shopItemRepository.save(shopItem);
        } else {
            throw new RuntimeException("ShopItem not found");
        }
    }

    public ShopItem toggleStatus(Long id, Long boughtById) {
        Optional<ShopItem> shopItemOptional = shopItemRepository.findById(id);
        if (shopItemOptional.isPresent()) {
            ShopItem shopItem = shopItemOptional.get();
            shopItem.setBoughtTime(new Date());
            shopItem.setStatus(!shopItem.getStatus()); 
            shopItem.setBoughtBy(userRepository.findById(boughtById).orElseThrow(() -> new RuntimeException("User not found")));
            return shopItemRepository.save(shopItem);
        } else {
            throw new RuntimeException("ShopItem not found");
        }
    }

    public boolean deleteShopItem(Long id) {
        Optional<ShopItem> shopItemOptional = shopItemRepository.findById(id);
        if (shopItemOptional.isPresent()) {
            shopItemRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
