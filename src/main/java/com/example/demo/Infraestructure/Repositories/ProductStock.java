package com.example.demo.Infraestructure.Repositories;

public class ProductStock {
    private String productId;
    private Integer currentQuantity;
    private Integer minQuantity;
    private Integer maxQuantity;

    public ProductStock(String productId, Integer currentQuantity, Integer minQuantity, Integer maxQuantity) {
        this.productId = productId;
        this.currentQuantity = currentQuantity;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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
