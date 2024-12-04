package com.falcon.CostMate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.falcon.CostMate.Entity.ShopItem;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopItemRepository extends JpaRepository<ShopItem,Long>{

}
