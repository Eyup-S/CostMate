package com.falcon.CostMate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.falcon.CostMate.Entity.TransactionItem;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {

}
