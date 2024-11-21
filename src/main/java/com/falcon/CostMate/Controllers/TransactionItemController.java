package com.falcon.CostMate.Controllers;


import com.falcon.CostMate.Entity.TransactionItem;
import com.falcon.CostMate.Services.TransactionItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TransactionItemController {

    private final TransactionItemService itemService;

    @GetMapping("/items/{category}")
    public List<TransactionItem> getItems(@PathVariable String category){
        return itemService.getItems(category);
    }

    @PostMapping("/items")
    public String addItem(@RequestBody TransactionItem item){
        return itemService.addItem(item);
    }


}
