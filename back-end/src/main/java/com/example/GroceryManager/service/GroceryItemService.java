package com.example.GroceryManager.service;

import com.example.GroceryManager.model.GroceryItem;
import com.example.GroceryManager.repo.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;

    @Autowired
    public GroceryItemService(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    public List<GroceryItem> findAllItems() {
        return groceryItemRepository.findAll();
    }

    public GroceryItem findItemById(Long id) {
        return groceryItemRepository.findById(id).orElse(null);
    }

    public GroceryItem addItem(GroceryItem item) {
        return groceryItemRepository.save(item);
    }

    public GroceryItem updateItem(GroceryItem item) {
        return groceryItemRepository.save(item);
    }

    public void deleteItem(Long id) {
        groceryItemRepository.deleteById(id);
    }
}

// Pushed by Kriss