package com.example.grocery_booking_api.service;


import com.example.grocery_booking_api.model.GroceryItem;
import com.example.grocery_booking_api.model.Order;
import com.example.grocery_booking_api.repository.GroceryItemRepository;
import com.example.grocery_booking_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Get all available grocery items with quantity greater than zero.
     */
    public List<GroceryItem> getAvailableItems() {
        return groceryItemRepository.findAll()
                .stream()
                .filter(item -> item.getInventoryQuantity() > 0)
                .collect(Collectors.toList());
    }

    /**
     * Book items in a new order.
     */
    @Transactional
    public Order bookItems(List<Long> itemIds) {
        Order order = new Order();
        List<GroceryItem> orderedItems = itemIds.stream()
                .map(itemId -> groceryItemRepository.findById(itemId)
                        .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId)))
                .collect(Collectors.toList());

        // Decrease the quantity of each ordered item and check availability
        for (GroceryItem item : orderedItems) {
            if (item.getInventoryQuantity() <= 0) {
                throw new RuntimeException("Item out of stock: " + item.getName());
            }
            item.setInventoryQuantity(item.getInventoryQuantity() - 1);
            groceryItemRepository.save(item);
        }

        order.setGroceryItemIds(orderedItems);
        return orderRepository.save(order);
    }
}

