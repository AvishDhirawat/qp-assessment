package com.example.grocery_booking_api.controller;

import com.example.grocery_booking_api.model.GroceryItem;
import com.example.grocery_booking_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/available-items")
    public List<GroceryItem> viewAvailableItems() {
        return userService.getAvailableItems();
    }

    @PostMapping("/book-items")
    public ResponseEntity<Void> bookItems(@RequestBody List<Long> itemIds) {
        userService.bookItems(itemIds);
        return ResponseEntity.ok().build();
    }
}

