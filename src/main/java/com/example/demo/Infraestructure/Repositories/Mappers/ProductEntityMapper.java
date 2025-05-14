package com.example.demo.Infraestructure.Repositories.Mappers;

import org.springframework.stereotype.Component;

import com.example.demo.Domain.Entities.ProductEntity;
import com.example.demo.Infraestructure.Models.Product;

@Component
public class ProductEntityMapper {
    public ProductEntity toEntity(Product productDomainObj){
        return new ProductEntity(
            productDomainObj.getId(),
            productDomainObj.getDescription(),
            productDomainObj.getPrice(),
            productDomainObj.getSKU(),
            productDomainObj.getCreatedAt(),
            productDomainObj.isActive()
        );
    }

    public Product toModel(ProductEntity productEntity) {
        return new Product(
            productEntity.getId(),
            productEntity.getDescription(),
            productEntity.getPrice(),
            productEntity.getSKU(),
            productEntity.getCreatedAt(),
            productEntity.isActive()
        );
    }
}
