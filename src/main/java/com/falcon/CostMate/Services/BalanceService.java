package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Entity.Balances;
import com.falcon.CostMate.Repositories.AppUserRepository;
import com.falcon.CostMate.Repositories.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.*;


@RequiredArgsConstructor
@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final AppUserRepository userRepository;

    public Balances getBalanceOfUser(Long userId) throws Exception{
        Optional<AppUser> user = userRepository.findById(userId);
        Optional<Balances> balance = Optional.empty();
        if(user.isPresent()){
            balance =  balanceRepository.findByUser(user.get());
            if(balance.isPresent()){
                return balance.get();
            }
            else{
                throw new Exception("no balance");
            }
        }
        return balance.get();
    }

    public List<Balances> getBalanceOfGroup(String groupId){
        List<Balances> balances = balanceRepository.findByGroup_Gid(Long.parseLong(groupId));

        return balances;
    }
}
