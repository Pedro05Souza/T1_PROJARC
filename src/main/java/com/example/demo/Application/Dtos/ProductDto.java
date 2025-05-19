package com.example.demo.Application.Dtos;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ProductDto {
    private UUID id;
    private String description;
    private String SKU;
    private Double price;
    private Instant createdAt;
    private Boolean isActive;

    public ProductDto(String description, Double price, String SKU){
        this.description = description;
        this.price = price;
        this.SKU = SKU;
    }

}