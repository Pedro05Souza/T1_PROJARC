package com.example.demo.Application.Usecases;

import org.springframework.stereotype.Service;

import com.example.demo.Application.Dtos.CreateProductDto;
import com.example.demo.Application.Dtos.ProductDto;
import com.example.demo.Application.Dtos.Assemblers.ProductAssembler;
import com.example.demo.Domain.Entities.ProductEntity;
import com.example.demo.Infraestructure.Models.Product;
import com.example.demo.Infraestructure.Repositories.PersistedResult;
import com.example.demo.Infraestructure.Repositories.ProductRepository;
import com.example.demo.Infraestructure.Repositories.StockRepository;

@Service
public class CreateProductUsecase {
    private final ProductRepository productRepository;
    private final StockRepository stocksRepository;
    private final ProductAssembler productAssembler;

    public CreateProductUsecase(ProductRepository productRepository, ProductAssembler productAssembler,
            StockRepository stocksRepository) {
        this.productRepository = productRepository;
        this.productAssembler = productAssembler;
        this.stocksRepository = stocksRepository;
    }

    public ProductDto createProduct(CreateProductDto createProductDto) {
        ProductEntity productEntity = new ProductEntity(createProductDto.getDescription(), createProductDto.getPrice(),
                createProductDto.getSku());
        PersistedResult<Product, ProductEntity> result = this.productRepository.createProduct(
                productEntity);

        ProductEntity newProductEntity = result.getEntity();
        Product rawProduct = result.getModel();

        this.stocksRepository.createStock(rawProduct, createProductDto.getMinQuantity(),
                createProductDto.getMaxQuantity());
        return this.productAssembler.toDto(newProductEntity);
    }

}
