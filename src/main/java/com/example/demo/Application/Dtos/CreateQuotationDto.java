package com.example.demo.Application.Dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateQuotationDto {

    @NotNull(message = "Products are required")
    @Size(min = 1, message = "At least one product is required")
    private List<UUID> productIds;

    @NotBlank(message = "Customer name is required")
    String customerName;

    @NotBlank(message = "Country is required")
    String country;

    @NotBlank(message = "State is required")
    String state;

    public List<UUID> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<UUID> productIds) {
        this.productIds = productIds;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
    
    
}
