package com.example.demo.Domain.Entities;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    private UUID id;
    private String description;
    private String SKU;
    private Double price;
    private Instant createdAt;
    private Boolean isActive;

    public ProductEntity(String description, String SKU, Double price,
            Instant createdAt, Boolean isActive) {
        this.description = description;
        this.SKU = SKU;
        this.price = price;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }
}