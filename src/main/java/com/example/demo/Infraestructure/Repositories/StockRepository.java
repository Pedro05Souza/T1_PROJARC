package com.example.demo.Infraestructure.Repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.Infraestructure.Models.Product;
import com.example.demo.Infraestructure.Models.Stock;
import com.example.demo.Infraestructure.Models.StockRepositoryInterface;

@Repository
public class StockRepository {
    private final StockRepositoryInterface stockRepositoryInterface;

    public StockRepository(StockRepositoryInterface stockRepositoryInterface) {
        this.stockRepositoryInterface = stockRepositoryInterface;
    }

    public void createStock(Product product, int minQuantity, int maxQuantity) {
        Stock stock = new Stock(product, minQuantity, maxQuantity, 1);
        this.stockRepositoryInterface.save(stock);
    }

    public List<ProductStock> getAllProductsStock() {
        List<Stock> stocks = this.stockRepositoryInterface.findAll();
        return stocks.stream()
                .map(stock -> new ProductStock(stock.getProduct().getId().toString(), stock.getCurrentQuantity(),
                        stock.getMinQuantity(), stock.getMaxQuantity()))
                .toList();
    }

    public List<ProductStock> getAllProductsStockByIds(List<Long> ids) {
        List<Stock> stocks = this.stockRepositoryInterface.findAllById(ids);
        return stocks.stream()
                .map(stock -> new ProductStock(stock.getProduct().getId().toString(), stock.getCurrentQuantity(),
                        stock.getMinQuantity(), stock.getMaxQuantity()))
                .toList();
    }

}
