package com.example.grocery_booking_api.service;

import com.example.grocery_booking_api.model.GroceryItem;
import com.example.grocery_booking_api.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    /**
     * Add a new grocery item to the inventory.
     */
    public GroceryItem addGroceryItem(GroceryItem item) {
        return groceryItemRepository.save(item);
    }

    /**
     * Get a list of all grocery items.
     */
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    /**
     * Update details of an existing grocery item.
     */
    public GroceryItem updateGroceryItem(Long itemId, GroceryItem updatedItem) {
        GroceryItem item = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));

        item.setName(updatedItem.getName());
        item.setPrice(updatedItem.getPrice());
        item.setInventoryQuantity(updatedItem.getInventoryQuantity());
        return groceryItemRepository.save(item);
    }

    /**
     * Remove a grocery item by ID.
     *
     * @return
     */
    public String deleteGroceryItem(Long itemId) {
        GroceryItem item = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));
        groceryItemRepository.deleteById(itemId);
        return item.getName() + "was deleted";
    }

    /**
     * Update the inventory level for a grocery item.
     */
    public GroceryItem updateInventory(Long itemId, int newQuantity) {
        GroceryItem item = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));
        item.setInventoryQuantity(newQuantity);
        return groceryItemRepository.save(item);
    }
}
