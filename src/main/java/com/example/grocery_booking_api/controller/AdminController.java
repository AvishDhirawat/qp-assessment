package com.example.grocery_booking_api.controller;

import com.example.grocery_booking_api.model.GroceryItem;
import com.example.grocery_booking_api.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-items")
    public ResponseEntity<List<GroceryItem>> addGroceryItem(@RequestBody List<GroceryItem> items) {
        List<GroceryItem> savedItems = adminService.addGroceryItem(items);
        return ResponseEntity.ok(savedItems);
    }

    @GetMapping("/items")
    public List<GroceryItem> getItems() {
        return adminService.getAllGroceryItems();
    }

    @DeleteMapping("/remove-item/{id}")
    public ResponseEntity<String> removeItem(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.deleteGroceryItem(id));
    }

    @PutMapping("/update-item/{id}")
    public ResponseEntity<GroceryItem> updateItem(@PathVariable Long id, @RequestBody GroceryItem item) {
        GroceryItem updatedItem = adminService.updateGroceryItem(id, item);
        return ResponseEntity.ok(updatedItem);
    }

    @PutMapping("/manage-inventory/{id}/{quantity}")
    public ResponseEntity<Void> manageInventory(@PathVariable Long id, @PathVariable int quantity) {
        adminService.updateInventory(id, quantity);
        return ResponseEntity.noContent().build();
    }
}


