package com.example.demo.Infraestructure.Repositories;

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
        Stock stock = new Stock(product, minQuantity, maxQuantity);
        this.stockRepositoryInterface.save(stock);
    }

}
