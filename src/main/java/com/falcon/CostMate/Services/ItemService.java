package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.Item;
import com.falcon.CostMate.Repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {


    private final ItemRepository itemRepository;

    public List<Item> getItems(String category){
        return itemRepository.findAll();
    }

    public String addItem(Item item){
        try {
            itemRepository.save(item);
        } catch (Exception e){
            return "Error adding item: " + e.getMessage();
        }
        return "Item added: " + item;
    }

    public String deleteItem(Item item){
        return "Item deleted: " + item;
    }

    public String updateItem(Item item){
        try {
            return "updated";
        }
        catch (Exception e){
            return "Error updating item: " + e.getMessage();
        }
    }



}
