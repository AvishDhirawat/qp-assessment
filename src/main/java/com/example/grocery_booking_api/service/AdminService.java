package com.example.grocery_booking_api.service;

import com.example.grocery_booking_api.dto.InventoryUpdateResponse;
import com.example.grocery_booking_api.model.GroceryItem;
import com.example.grocery_booking_api.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public List<GroceryItem> addGroceryItem(List<GroceryItem> items) {
        return groceryItemRepository.saveAll(items);
    }

    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public GroceryItem updateGroceryItem(Long itemId, GroceryItem updatedItem) {
        GroceryItem item = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));

        item.setName(updatedItem.getName());
        item.setPrice(updatedItem.getPrice());
        item.setInventoryQuantity(updatedItem.getInventoryQuantity());
        return groceryItemRepository.save(item);
    }

    public String deleteGroceryItem(Long itemId) {
        GroceryItem item = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));
        groceryItemRepository.deleteById(itemId);
        return "Item Id: " + item.getId() + " with Name: " + item.getName() + " was removed from the inventory";
    }

//    public Map<String, Object> updateInventory(Long itemId, int newQuantity) {
//        GroceryItem item = groceryItemRepository.findById(itemId)
//                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));
//        item.setInventoryQuantity(newQuantity);
//        GroceryItem savedItem = groceryItemRepository.save(item);
//
//        String message = "The number of items for, Item Id: " + savedItem.getId() +
//                " with Name: " + savedItem.getName() + " was updated in the inventory.";
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", message);
//        response.put("updatedItem", savedItem);
//        return response;
//    }


    public InventoryUpdateResponse updateInventory(Long itemId, int newQuantity) {
        GroceryItem item = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));
        item.setInventoryQuantity(newQuantity);
        GroceryItem savedItem = groceryItemRepository.save(item);

        String message = "The number of items for, Item Id: " + savedItem.getId() +
                " with Name: " + savedItem.getName() + " was updated in the inventory.";

        return new InventoryUpdateResponse(message, savedItem);
    }
}
