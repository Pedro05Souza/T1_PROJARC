package com.example.demo.Presentation.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Application.Dtos.CreateProductDto;
import com.example.demo.Application.Dtos.ProductDto;
import com.example.demo.Application.Usecases.CreateProductUsecase;
import com.example.demo.Application.Usecases.GetAllProductsUsecase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUsecase createProductUsecase;
    private final GetAllProductsUsecase getAllProductsUsecase;

    public ProductController(CreateProductUsecase createProductUsecase,
            GetAllProductsUsecase getAllProductsUsecase) {
        this.createProductUsecase = createProductUsecase;
        this.getAllProductsUsecase = getAllProductsUsecase;
    }

    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody CreateProductDto createProductDto) {
        return createProductUsecase.createProduct(createProductDto);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return getAllProductsUsecase.getAllProducts();
    }

}
