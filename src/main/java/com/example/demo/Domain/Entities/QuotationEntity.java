package com.example.demo.Domain.Entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationEntity {
    private UUID id;
    private String code;
    private String customerName;
    private List<QuotedProductEntity> products;
    private Instant createdAt;
    private String country;
    private String state;
    private boolean isApproved;

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
}
