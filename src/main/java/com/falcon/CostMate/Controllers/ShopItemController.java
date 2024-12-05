package com.falcon.CostMate.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.falcon.CostMate.Entity.ShopItem;
import com.falcon.CostMate.Services.ShopItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/shopListApp/shopItems")
@RequiredArgsConstructor
public class ShopItemController {
	
	private final ShopItemService shopItemService;
	
	@GetMapping("/")
	public ResponseEntity<List<ShopItem>> getAllShopItems(){
		try {
			return ResponseEntity.ok(shopItemService.getAllItems());
		}
		catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
    @GetMapping("/list/{listName}")
    public ResponseEntity<List<ShopItem>> getShopItemsByListName(@PathVariable String listName) {
        try {
            return ResponseEntity.ok(shopItemService.getShopItemsByListName(listName));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<ShopItem> createShopItem(@Valid @RequestBody ShopItem shopItem) {
        return ResponseEntity.ok(shopItemService.createShopItem(shopItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopItem> updateShopItem(@PathVariable Long id, @Valid @RequestBody ShopItem shopItem) {
        try {
            return ResponseEntity.ok(shopItemService.updateShopItem(id, shopItem));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/toggleStatus")
    public ResponseEntity<ShopItem> toggleStatus(@PathVariable Long id, @RequestParam Long boughtById) {
        try {
            return ResponseEntity.ok(shopItemService.toggleStatus(id, boughtById));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteShopItem(@PathVariable Long id) {
        if (shopItemService.deleteShopItem(id)) {
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
}
