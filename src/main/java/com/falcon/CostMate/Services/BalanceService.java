package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Entity.Balances;
import com.falcon.CostMate.Entity.Group;
import com.falcon.CostMate.Repositories.AppUserRepository;
import com.falcon.CostMate.Repositories.BalanceRepository;
import com.falcon.CostMate.Repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.*;


@RequiredArgsConstructor
@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final AppUserRepository userRepository;
    private final GroupRepository groupRepository;


    public List<Balances> getBalancesOfUser(Long userId) throws Exception{
        Optional<AppUser> user = userRepository.findById(userId);
        Optional<List<Balances>> balances = Optional.empty();
        if(user.isPresent()){
            balances =  balanceRepository.findAllByUser(user.get());
            if(balances.isPresent()){
                return balances.get();
            }
            else{
                throw new Exception("no balance");
            }
        }
        return balances.get();
    }
    public Balances getBalanceOfUser(Long userId, Long groupId) throws Exception{
        Optional<AppUser> user = userRepository.findById(userId);
        Optional<Group> group = groupRepository.findById(groupId);
        Balances balances = new Balances();
        if(user.isPresent() && group.isPresent()){
            balances =  balanceRepository.findByUser_UidAndGroup_Gid(user.get().getUid(), group.get().getGid());
            return balances;
        }
        return balances;
    }

    public List<Balances> getBalanceOfGroup(String groupId){
        List<Balances> balances = balanceRepository.findByGroup_Gid(Long.parseLong(groupId));

        return balances;
    }
}
