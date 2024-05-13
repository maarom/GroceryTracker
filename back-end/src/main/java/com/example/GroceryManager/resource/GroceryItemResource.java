package com.example.GroceryManager.resource;



import com.example.GroceryManager.model.GroceryItem;
import com.example.GroceryManager.service.GroceryItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grocery")
public class GroceryItemResource {

    private final GroceryItemService groceryItemService;

    public GroceryItemResource(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<GroceryItem>> getAllItems() {
        List<GroceryItem> items = groceryItemService.findAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<GroceryItem> getItemById(@PathVariable Long id) {
        GroceryItem item = groceryItemService.findItemById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/items")
    public ResponseEntity<GroceryItem> addItem(@RequestBody GroceryItem item) {
        GroceryItem newItem = groceryItemService.addItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<GroceryItem> updateItem(@RequestBody GroceryItem item, @PathVariable Long id) {
        item.setId(id);
        GroceryItem updatedItem = groceryItemService.updateItem(item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        groceryItemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}
