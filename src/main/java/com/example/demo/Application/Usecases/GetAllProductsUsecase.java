package com.example.demo.Application.Usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Application.Dtos.ProductDto;
import com.example.demo.Application.Dtos.Assemblers.ProductAssembler;
import com.example.demo.Domain.Entities.ProductEntity;
import com.example.demo.Infraestructure.Repositories.ProductRepository;

@Service
public class GetAllProductsUsecase {
    private final ProductRepository productRepository;
    private final ProductAssembler productAssembler;

    public GetAllProductsUsecase(ProductRepository productRepository, ProductAssembler productAssembler) {
        this.productRepository = productRepository;
        this.productAssembler = productAssembler;
    }

    public List<ProductDto> getAllProducts() {
        List<ProductEntity> products = this.productRepository.listProducts();
        return products.stream()
                .map(productAssembler::toDto)
                .collect(Collectors.toList());
    }

    
}
