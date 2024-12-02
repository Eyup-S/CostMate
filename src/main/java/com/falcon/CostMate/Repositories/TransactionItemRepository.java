package com.falcon.CostMate.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.falcon.CostMate.Entity.TransactionItem;


@Repository
public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {
	Optional<List<TransactionItem>> findByCategory(String category);
	
	@Query("SELECT t FROM TransactionItem t WHERE FUNCTION('MONTH', t.addedDate) = :month AND FUNCTION('YEAR', t.addedDate) = :year")
    List<TransactionItem> findByMonthAndYear(int month, int year);

}
