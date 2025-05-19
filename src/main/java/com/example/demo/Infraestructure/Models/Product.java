package com.example.demo.Infraestructure.Models;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;
    private Double price;
    private String SKU;


    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Stock stock;

    private Boolean isActive = true;

    @CreationTimestamp
    private Instant createdAt;

    protected Product() {
    }

    public Product(String description, Double price, String SKU) {
        this.description = description;
        this.price = price;
        this.SKU = SKU;
    }

    public Product(UUID id, String description, Double price, String SKU, Instant createdAt, Boolean isActive) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.SKU = SKU;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

}
