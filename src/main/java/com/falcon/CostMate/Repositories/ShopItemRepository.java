package com.falcon.CostMate.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.falcon.CostMate.Entity.ShopItem;

@Repository
public interface ShopItemRepository extends JpaRepository<ShopItem,Long>{

	List<ShopItem> findByListName_Name(String listName);

}
