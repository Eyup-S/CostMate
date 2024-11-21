package com.falcon.CostMate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.falcon.CostMate.Entity.TransactionItem;

public interface ItemRepository extends JpaRepository<TransactionItem, Long> {

}
