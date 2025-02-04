package com.falcon.CostMate.Repositories;

import java.util.List;

import com.falcon.CostMate.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import com.falcon.CostMate.Entity.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g JOIN g.groupMembers u WHERE u.uid = :userId")
    List<Group> findGroupsByUserId(@Param("userId") Long userId);


}
