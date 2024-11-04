package com.example.grocery_booking_api.dto;

import com.example.grocery_booking_api.model.GroceryItem;

public class InventoryUpdateResponse {
    private String message;
    private GroceryItem item;

    public InventoryUpdateResponse(String message, GroceryItem item) {
        this.message = message;
        this.item = item;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GroceryItem getItem() {
        return item;
    }

    public void setItem(GroceryItem item) {
        this.item = item;
    }
}
