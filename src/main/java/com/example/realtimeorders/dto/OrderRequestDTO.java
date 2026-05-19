package com.example.realtimeorders.dto;

import lombok.Data;

@Data
public class OrderRequestDTO {

    private String customerName;
    private String productName;
    private String status;
}
