package com.example.demo.Domain.Entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class QuotationEntity {
    private UUID id;
    private String code;
    private String customerName;
    private List<QuotedProductEntity> products;
    private Instant createdAt;
    private String country;
    private String state;
    private boolean isApproved;

    public QuotationEntity(UUID id, String code, String customerName, List<QuotedProductEntity> products,
            Instant createdAt,
            String country, String state, boolean isApproved) {
        this.id = id;
        this.code = code;
        this.customerName = customerName;
        this.products = products;
        this.createdAt = createdAt;
        this.country = country;
        this.state = state;
        this.isApproved = isApproved;
    }

    public QuotationEntity(String code, String customerName, List<QuotedProductEntity> products, Instant createdAt,
            String country, String state, boolean isApproved) {
        this.code = code;
        this.customerName = customerName;
        this.products = products;
        this.createdAt = createdAt;
        this.country = country;
        this.state = state;
        this.isApproved = isApproved;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<QuotedProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<QuotedProductEntity> products) {
        this.products = products;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

}
