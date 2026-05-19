package com.example.realtimeorders.repository;

import com.example.realtimeorders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<Order, Integer> {

}
