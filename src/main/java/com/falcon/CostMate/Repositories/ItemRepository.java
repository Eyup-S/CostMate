package com.falcon.CostMate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.falcon.CostMate.Entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
