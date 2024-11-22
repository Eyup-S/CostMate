package com.falcon.CostMate.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.falcon.CostMate.Entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
	
}
