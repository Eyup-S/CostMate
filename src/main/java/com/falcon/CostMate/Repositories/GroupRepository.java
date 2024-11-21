package com.falcon.CostMate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.falcon.CostMate.Entity.Groups;

public interface GroupRepository extends JpaRepository<Groups, Long> {

}
