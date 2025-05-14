package com.example.demo.Application.Dtos;

import jakarta.validation.constraints.*;

public class CreateProductDto {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @NotBlank(message = "SKU is required")
    @Size(min = 3, max = 20, message = "SKU must be between 3 and 20 characters")
    private String sku;

    @NotNull(message = "Max quantity is required")
    @Min(value = 0, message = "Max quantity must be >= 0")
    private Integer maxQuantity;

    @NotNull(message = "Min quantity is required")
    @Min(value = 0, message = "Min quantity must be >= 0")
    private Integer minQuantity;

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

    public String getSku() {
        return sku;
    }

    public void setSKU(String sku) {
        this.sku = sku;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }
}
