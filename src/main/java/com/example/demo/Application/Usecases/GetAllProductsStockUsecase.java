package com.example.demo.Application.Usecases;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Application.Dtos.GetProductStocksResponseDto;
import com.example.demo.Infraestructure.Repositories.ProductStock;
import com.example.demo.Infraestructure.Repositories.StockRepository;

@Service
public class GetAllProductsStockUsecase {
    private final StockRepository stockRepository;

    public GetAllProductsStockUsecase(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    private List<GetProductStocksResponseDto> mapProductStockToDto(List<ProductStock> products) {
        return products.stream()
                .map(product -> new GetProductStocksResponseDto(product.getProductId().toString(), product.getCurrentQuantity(),
                        product.getMaxQuantity(), product.getMinQuantity()))
                .collect(Collectors.toList());
    }

    public List<GetProductStocksResponseDto> getAllProductsStocks(List<UUID> ids) {
        if (ids != null && !ids.isEmpty()) {
            List<ProductStock> products = this.stockRepository.getAllProductsStockByIds(ids);
            return mapProductStockToDto(products);
        }

        List<ProductStock> products = this.stockRepository.getAllProductsStock();
        return mapProductStockToDto(products);
    }

}
