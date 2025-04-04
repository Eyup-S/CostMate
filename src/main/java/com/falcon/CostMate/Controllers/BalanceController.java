package com.falcon.CostMate.Controllers;


import com.falcon.CostMate.DTO.MoneyTransferModel;
import com.falcon.CostMate.Entity.Balances;
import com.falcon.CostMate.Services.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class BalanceController {
    private final BalanceService balanceService;

    @GetMapping("/balances/{groupID}")
    public ResponseEntity<List<Balances>> getBalanceOfGroup(@PathVariable("groupID") String groupID){
        try{
            List<Balances> balance = balanceService.getBalanceOfGroup(groupID);
            return ResponseEntity.ok(balance);
        }
        catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/balances/users/{userId}")
    public ResponseEntity<List<Balances>> getBalancesOfUser(@PathVariable("userId") Long userId){
        try{
            return ResponseEntity.ok(balanceService.getBalancesOfUser(userId));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/balances/users/{userId}/groups/{groupId}")
    public ResponseEntity<Balances> getBalanceOfUser(@PathVariable("userId") Long userId, @PathVariable("groupId") Long groupId){
        try{
            return ResponseEntity.ok(balanceService.getBalanceOfUser(userId, groupId));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}
