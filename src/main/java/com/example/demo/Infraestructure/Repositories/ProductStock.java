package com.example.demo.Infraestructure.Repositories;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductStock {
    private UUID productId;
    private Integer currentQuantity;
    private Integer minQuantity;
    private Integer maxQuantity;
}
