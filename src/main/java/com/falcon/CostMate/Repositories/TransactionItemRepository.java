package com.falcon.CostMate.Repositories;

import java.util.List;

import com.falcon.CostMate.Entity.Category;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.falcon.CostMate.Entity.TransactionItem;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {
	Optional<List<TransactionItem>> findByCategory(Category category);
	
	@Query("SELECT t FROM TransactionItem t WHERE FUNCTION('MONTH', t.addedDate) = :month AND FUNCTION('YEAR', t.addedDate) = :year")
    List<TransactionItem> findByMonthAndYear(int month, int year);

	List<TransactionItem> findByGroup_Gid(Long groupId);

	@Query("SELECT DISTINCT t FROM TransactionItem t LEFT JOIN FETCH t.shares WHERE t.iid = :id")
	Optional<TransactionItem> findByIdWithShares(@Param("id") Long id);


}
