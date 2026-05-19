package com.example.realtimeorders.service;

import com.example.realtimeorders.dto.*;
import com.example.realtimeorders.event.OrderEventPublisher;
import com.example.realtimeorders.model.Order;
import com.example.realtimeorders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderEventPublisher eventPublisher;

    public OrderService(
            OrderRepository orderRepository,
            OrderEventPublisher eventPublisher) {

        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    // CREATE ORDER
    public OrderResponseDTO createOrder(
            OrderRequestDTO dto) {

        Order order = Order.builder()
                .customerName(dto.getCustomerName())
                .productName(dto.getProductName())
                .status(dto.getStatus())
                .build();

        Order savedOrder =
                orderRepository.save(order);

        OrderResponseDTO response =
                mapToDTO(savedOrder);

        publishEvent(
                "ORDER_CREATED",
                response
        );

        return response;
    }

    // GET ALL ORDERS
    public List<OrderResponseDTO> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE ORDER
    public OrderResponseDTO updateOrder(
            Integer id,
            OrderRequestDTO dto) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found"));

        order.setCustomerName(
                dto.getCustomerName());

        order.setProductName(
                dto.getProductName());

        order.setStatus(
                dto.getStatus());

        Order updatedOrder =
                orderRepository.save(order);

        OrderResponseDTO response =
                mapToDTO(updatedOrder);

        publishEvent(
                "ORDER_UPDATED",
                response
        );

        return response;
    }

    // DELETE ORDER
    public void deleteOrder(Integer id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found"));

        OrderResponseDTO response =
                mapToDTO(order);

        orderRepository.delete(order);

        publishEvent(
                "ORDER_DELETED",
                response
        );
    }

    // ENTITY → DTO
    private OrderResponseDTO mapToDTO(
            Order order) {

        return OrderResponseDTO.builder()
                .id(order.getId())
                .customerName(
                        order.getCustomerName())
                .productName(
                        order.getProductName())
                .status(order.getStatus())
                .updatedAt(
                        order.getUpdatedAt())
                .build();
    }

    // SEND EVENT
    private void publishEvent(
            String eventType,
            OrderResponseDTO data) {

        OrderEventDTO event =
                OrderEventDTO.builder()
                        .eventType(eventType)
                        .timestamp(
                                LocalDateTime.now())
                        .data(data)
                        .build();

        eventPublisher.publish(event);
    }
}
