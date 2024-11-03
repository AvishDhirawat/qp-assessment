package com.example.grocery_booking_api.repository;

import com.example.grocery_booking_api.model.GroceryItem;


import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
}
