package com.example.realtimeorders.event;

import com.example.realtimeorders.dto.OrderEventDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public OrderEventPublisher(
            SimpMessagingTemplate messagingTemplate) {

        this.messagingTemplate = messagingTemplate;
    }

    public void publish(OrderEventDTO eventDTO) {

        messagingTemplate.convertAndSend(
                "/topic/orders",
                eventDTO
        );
    }
}