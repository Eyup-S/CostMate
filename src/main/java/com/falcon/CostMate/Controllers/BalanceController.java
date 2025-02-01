package com.falcon.CostMate.Controllers;


import com.falcon.CostMate.Entity.Balances;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class BalanceController {
    //private final BalanceService balanceService;

    @GetMapping("/balances/{groupID}/")
    public ResponseEntity<Balances> getBalanceofGroup(@PathVariable("groupID") String groupID){
        Balances balance = new Balances();
        return ResponseEntity.ok(balance);
    }



}
