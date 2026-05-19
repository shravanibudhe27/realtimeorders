package com.example.realtimeorders.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Integer id;
    private String customerName;
    private String productName;
    private String status;
    private LocalDateTime updatedAt;
}