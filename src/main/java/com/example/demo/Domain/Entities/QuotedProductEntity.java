package com.example.demo.Domain.Entities;

import java.util.UUID;

public class QuotedProductEntity {
    private UUID id;
    private ProductEntity product;
    private Integer amount;

    public QuotedProductEntity(UUID id, ProductEntity product, Integer amount) {
        this.id = id;
        this.product = product;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
