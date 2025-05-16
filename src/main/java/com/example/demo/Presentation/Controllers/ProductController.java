package com.example.demo.Presentation.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Application.Dtos.CreateProductDto;
import com.example.demo.Application.Dtos.GetProductStocksResponseDto;
import com.example.demo.Application.Dtos.ProductDto;
import com.example.demo.Application.Usecases.CreateProductUsecase;
import com.example.demo.Application.Usecases.GetAllProductsStockUsecase;
import com.example.demo.Application.Usecases.GetAllProductsUsecase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUsecase createProductUsecase;
    private final GetAllProductsUsecase getAllProductsUsecase;
    private final GetAllProductsStockUsecase getAllProductsStockUsecase;

    public ProductController(CreateProductUsecase createProductUsecase,
            GetAllProductsUsecase getAllProductsUsecase,
            GetAllProductsStockUsecase getAllProductsStockUsecase) {
        this.createProductUsecase = createProductUsecase;
        this.getAllProductsUsecase = getAllProductsUsecase;
        this.getAllProductsStockUsecase = getAllProductsStockUsecase;
    }

    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody CreateProductDto createProductDto) {
        return createProductUsecase.createProduct(createProductDto);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return getAllProductsUsecase.getAllProducts();
    }

    @GetMapping("/stocks")
    public List<GetProductStocksResponseDto> getAllProductsStock(
            @RequestParam(name = "productId", required = false) List<UUID> productIds) {
        return getAllProductsStockUsecase.getAllProductsStocks(productIds);
    }

}
