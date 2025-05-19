package com.example.demo.Domain.Entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuotedProductEntity {
    private UUID id;
    private ProductEntity product;
    private Integer amount;

    public QuotedProductEntity(ProductEntity product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }
}
