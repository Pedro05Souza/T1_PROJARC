package com.example.demo.Infraestructure.Repositories;

import java.util.UUID;

public class ProductStock {
    private UUID productId;
    private Integer currentQuantity;
    private Integer minQuantity;
    private Integer maxQuantity;

    public ProductStock(UUID productId, Integer currentQuantity, Integer minQuantity, Integer maxQuantity) {
        this.productId = productId;
        this.currentQuantity = currentQuantity;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
