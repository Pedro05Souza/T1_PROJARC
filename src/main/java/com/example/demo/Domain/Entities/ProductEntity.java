package com.example.demo.Domain.Entities;

import java.time.Instant;
import java.util.UUID;

public class ProductEntity {
    private UUID id;
    private String description;
    private String SKU;
    private Double price;
    private Instant createdAt;
    private Boolean isActive;

    public ProductEntity(String description, Double price, String SKU){
        this.description = description;
        this.price = price;
        this.SKU = SKU;
    }

    public ProductEntity(UUID id, String description, Double price, String SKU, Instant createdAt, Boolean isActive) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.SKU = SKU;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    public String getSKU() {
        return SKU;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Boolean isActive() {
        return isActive;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}