package com.example.realtimeorders.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

    @Entity
    @Table(name = "orders")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "customer_name")
        private String customerName;

        @Column(name = "product_name")
        private String productName;

        private String status;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @PrePersist
        @PreUpdate
        public void updateTimestamp() {

            this.updatedAt = LocalDateTime.now();
        }
    }

