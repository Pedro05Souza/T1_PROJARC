package com.example.demo.Infraestructure.Repositories;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.Domain.Entities.ProductEntity;
import com.example.demo.Infraestructure.Models.Product;
import com.example.demo.Infraestructure.Models.ProductRepositoryInterface;
import com.example.demo.Infraestructure.Repositories.Mappers.ProductEntityMapper;

@Repository
public class ProductRepository {
    private final ProductRepositoryInterface productRepositoryInterface;
    private final ProductEntityMapper productEntityMapper;

    public ProductRepository(ProductRepositoryInterface productRepositoryInterface,
            ProductEntityMapper productEntityMapper) {
        this.productRepositoryInterface = productRepositoryInterface;
        this.productEntityMapper = productEntityMapper;
    }

    public PersistedResult<Product, ProductEntity> createProduct(ProductEntity productEntity) {
        Product product = productEntityMapper.toModel(productEntity);
        product = this.productRepositoryInterface.save(product);
        return new PersistedResult<>(product, productEntityMapper.toEntity(product));
    }

    public List<ProductEntity> listProducts() {
        List<Product> productModels = this.productRepositoryInterface.findAll();
        return productModels.stream()
                .map(productEntityMapper::toEntity)
                .collect(Collectors.toList());
    }

    public ProductEntity listProductById(UUID id) {
        Product product = this.productRepositoryInterface.getReferenceById(id);
        return productEntityMapper.toEntity(product);
    }

    public List<ProductEntity> listProductsByIds(List<UUID> ids) {
        List<Product> products = this.productRepositoryInterface.findAllById(ids);
        return products.stream()
                .map(productEntityMapper::toEntity)
                .collect(Collectors.toList());

    }
}