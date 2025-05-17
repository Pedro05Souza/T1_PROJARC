package com.example.demo.Infraestructure.Models;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;
    private Double price;
    private String SKU;

    @ManyToOne
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    private Quotation quotation;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String sKU) {
        SKU = sKU;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

}
