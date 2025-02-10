package com.falcon.CostMate.Repositories;

import com.falcon.CostMate.Entity.Shares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharesRepository extends JpaRepository<Shares, Long> {


}
