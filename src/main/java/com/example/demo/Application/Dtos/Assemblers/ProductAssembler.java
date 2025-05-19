package com.example.demo.Application.Dtos.Assemblers;

import org.springframework.stereotype.Component;

import com.example.demo.Application.Dtos.ProductDto;
import com.example.demo.Domain.Entities.ProductEntity;

@Component
public class ProductAssembler {
    
    public ProductDto toDto(ProductEntity product) {
        return new ProductDto(
                product.getId(),
                product.getDescription(),
                product.getSKU(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getIsActive()
        );
    }
    
}
