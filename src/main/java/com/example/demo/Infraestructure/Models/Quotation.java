package com.example.demo.Infraestructure.Models;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "quotations")
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String code;
    private String customerName;

    @OneToMany(mappedBy = "quotation", orphanRemoval = true)
    private List<QuotedProduct> products;

    private Instant createdAt;

    private String country;

    private String state;

    private boolean isApproved;

    protected Quotation() {
    }

    public Quotation(String code, String customerName, List<QuotedProduct> products, Instant createdAt, String country,
            String state, boolean isApproved) {
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

    public List<QuotedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<QuotedProduct> products) {
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
