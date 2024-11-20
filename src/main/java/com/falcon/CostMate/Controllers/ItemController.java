package com.falcon.CostMate.Controllers;


import com.falcon.CostMate.Entity.Item;
import com.falcon.CostMate.Services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/{category}")
    public List<Item> getItems(@PathVariable String category){
        return itemService.getItems(category);
    }

    @PostMapping("/items")
    public String addItem(@RequestBody Item item){
        return itemService.addItem(item);
    }


}
