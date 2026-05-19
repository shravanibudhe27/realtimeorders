package com.example.realtimeorders.controller;

import com.example.realtimeorders.dto.OrderRequestDTO;
import com.example.realtimeorders.dto.OrderResponseDTO;
import com.example.realtimeorders.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService) {

        this.orderService = orderService;
    }

    // CREATE ORDER
    @PostMapping
    public OrderResponseDTO createOrder(
            @RequestBody OrderRequestDTO dto) {

        return orderService.createOrder(dto);
    }

    // GET ALL ORDERS
    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {

        return orderService.getAllOrders();
    }

    // UPDATE ORDER
    @PutMapping("/{id}")
    public OrderResponseDTO updateOrder(
            @PathVariable Integer id,
            @RequestBody OrderRequestDTO dto) {

        return orderService.updateOrder(id, dto);
    }

    // DELETE ORDER
    @DeleteMapping("/{id}")
    public String deleteOrder(
            @PathVariable Integer id) {

        orderService.deleteOrder(id);

        return "Order deleted successfully";
    }
}
