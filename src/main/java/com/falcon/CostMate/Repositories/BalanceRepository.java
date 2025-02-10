package com.falcon.CostMate.Repositories;

import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Entity.Balances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balances, Long> {

    Optional<Balances> findByUser(AppUser user);

    List<Balances> findByGroup_Gid(Long groupId);
}
