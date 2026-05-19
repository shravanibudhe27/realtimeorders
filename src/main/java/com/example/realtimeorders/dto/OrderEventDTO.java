package com.example.realtimeorders.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEventDTO {

    private String eventType;
    private LocalDateTime timestamp;
    private OrderResponseDTO data;
}
