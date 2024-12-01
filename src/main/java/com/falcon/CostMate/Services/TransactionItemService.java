package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.TransactionItem;
import com.falcon.CostMate.Repositories.TransactionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionItemService {


    private final TransactionItemRepository itemRepository;

    public List<TransactionItem> getItems(String category){
        return itemRepository.findAll();
    }

    public String addItem(TransactionItem item){
        try {
            itemRepository.save(item);
        } catch (Exception e){
            return "Error adding item: " + e.getMessage();
        }
        return "Item added: " + item;
    }

    public String deleteItem(TransactionItem item){
        return "Item deleted: " + item;
    }

    public String updateItem(TransactionItem item){
        try {
            return "updated";
        }
        catch (Exception e){
            return "Error updating item: " + e.getMessage();
        }
    }



}
