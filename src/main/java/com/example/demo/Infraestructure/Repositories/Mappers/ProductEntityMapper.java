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
            productDomainObj.getSKU(),
            productDomainObj.getPrice(),
            productDomainObj.getCreatedAt(),
            productDomainObj.getIsActive()
        );
    }

    public Product toModel(ProductEntity productEntity) {
        return new Product(
            productEntity.getId(),
            productEntity.getDescription(),
            productEntity.getPrice(),
            productEntity.getSKU(),
            productEntity.getCreatedAt(),
            productEntity.getIsActive()
        );
    }
}
