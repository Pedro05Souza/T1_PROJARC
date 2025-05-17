package com.example.demo.Application.Dtos;

import java.util.List;
import java.util.UUID;

public class QuotationDto {
    private UUID id;
    private String code;
    private String customerName;
    private List<UUID> products;
    private String createdAtIso;
    private String country;
    private String state;
    private boolean isApproved;


    public QuotationDto(UUID id, String code, String customerName, List<UUID> products, String createdAtIso, String country,
            String state, boolean isApproved) {
        this.id = id;
        this.code = code;
        this.customerName = customerName;
        this.products = products;
        this.createdAtIso = createdAtIso;
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


    public List<UUID> getProducts() {
        return products;
    }


    public void setProducts(List<UUID> products) {
        this.products = products;
    }


    public String getCreatedAtIso() {
        return createdAtIso;
    }


    public void setCreatedAtIso(String createdAtIso) {
        this.createdAtIso = createdAtIso;
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
