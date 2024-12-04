package com.falcon.CostMate.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.falcon.CostMate.Entity.Group;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
	
}
