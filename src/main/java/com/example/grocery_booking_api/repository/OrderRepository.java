package com.example.grocery_booking_api.repository;


import com.example.grocery_booking_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
