package com.example.demo.Infraestructure.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.Domain.Entities.ProductEntity;
import com.example.demo.Infraestructure.Models.Product;
import com.example.demo.Infraestructure.Models.Stock;
import com.example.demo.Infraestructure.Models.StockRepositoryInterface;
import com.example.demo.Infraestructure.Repositories.Mappers.ProductEntityMapper;

@Repository
public class StockRepository {
    private final StockRepositoryInterface stockRepositoryInterface;
    private final ProductEntityMapper productEntityMapper;

    public StockRepository(StockRepositoryInterface stockRepositoryInterface,
            ProductEntityMapper productEntityMapper) {
        this.stockRepositoryInterface = stockRepositoryInterface;
        this.productEntityMapper = productEntityMapper;
    }

    public void createStock(Product product, int minQuantity, int maxQuantity) {
        Stock stock = new Stock(product, minQuantity, maxQuantity, 1);
        this.stockRepositoryInterface.save(stock);
    }

    public List<ProductStock> getAllProductsStock() {
        List<Stock> stocks = this.stockRepositoryInterface.findAll();
        return stocks.stream()
                .map(stock -> new ProductStock(stock.getProduct().getId(), stock.getCurrentQuantity(),
                        stock.getMinQuantity(), stock.getMaxQuantity()))
                .toList();
    }

    public List<ProductStock> getAllProductsStockByIds(List<UUID> productIds) {
        List<Stock> stocks = stockRepositoryInterface.findByProductIdIn(productIds);

        return stocks.stream()
                .map(stock -> new ProductStock(
                        stock.getProduct().getId(),
                        stock.getCurrentQuantity(),
                        stock.getMinQuantity(),
                        stock.getMaxQuantity()))
                .toList();
    }

    public void bulkUpdate(List<ProductEntity> products, List<ProductStock> stocks) {
        List<Stock> stockList = stocks.stream()
                .map(stock -> {
                    Product product = productEntityMapper.toModel(products.stream()
                            .filter(p -> p.getId().toString().equals(stock.getProductId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "Product not found for ID: " + stock.getProductId())));
                    return new Stock(product, stock.getMaxQuantity(), stock.getMinQuantity(),
                            stock.getCurrentQuantity());
                })
                .toList();

        this.stockRepositoryInterface.saveAll(stockList);
    }

}
