package com.example.grocery_booking_api.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "grocery_item_id")
    @NotEmpty(message = "Order must contain at least one item")
    private List<GroceryItem> groceryItemIds;

    @ElementCollection
    @CollectionTable(name = "order_quantities", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "quantity")
    @NotEmpty(message = "Quantities must be provided for each item in the order")
    private List<@Min(value = 1, message = "Quantity must be at least 1") Integer> quantities;

    @NotNull(message = "Total price is mandatory")
    @Min(value = 0, message = "Total price cannot be negative")
    private Double totalPrice;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GroceryItem> getGroceryItemIds() {
        return groceryItemIds;
    }

    public void setGroceryItemIds(List<GroceryItem> groceryItemIds) {
        this.groceryItemIds = groceryItemIds;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
