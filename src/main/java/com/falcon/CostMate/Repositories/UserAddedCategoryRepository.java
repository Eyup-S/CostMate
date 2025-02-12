package com.falcon.CostMate.Repositories;

import com.falcon.CostMate.Entity.Group;
import com.falcon.CostMate.Entity.UserAddedCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddedCategoryRepository extends JpaRepository<UserAddedCategory, Long> {
    Optional<UserAddedCategory> findByName(String name);

    List<UserAddedCategory> findByCategoryGroup(Group group);
}