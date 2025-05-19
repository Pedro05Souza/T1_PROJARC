package com.example.demo.Infraestructure.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.Infraestructure.Models.Product;
import com.example.demo.Infraestructure.Models.Stock;
import com.example.demo.Infraestructure.Models.StockRepositoryInterface;
import com.example.demo.Infraestructure.Repositories.Mappers.ProductEntityMapper;

@Repository
public class StockRepository {
        private final StockRepositoryInterface stockRepositoryInterface;

        public StockRepository(StockRepositoryInterface stockRepositoryInterface,
                        ProductEntityMapper productEntityMapper) {
                this.stockRepositoryInterface = stockRepositoryInterface;
        }

        public void createStock(Product product, int minQuantity, int maxQuantity, int currentQuantity) {
                Stock stock = new Stock(product, maxQuantity, minQuantity, currentQuantity);
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

        public void bulkUpdate(List<ProductStock> stocks) {
                List<UUID> productIds = stocks.stream()
                                .map(ProductStock::getProductId)
                                .toList();

                List<Stock> stockList = this.stockRepositoryInterface.findByProductIdIn(productIds);

                for (ProductStock productStock : stocks) {
                        for (Stock stock : stockList) {
                                if (stock.getProduct().getId().equals(productStock.getProductId())) {
                                        stock.setCurrentQuantity(productStock.getCurrentQuantity());
                                        stock.setMinQuantity(productStock.getMinQuantity());
                                        stock.setMaxQuantity(productStock.getMaxQuantity());
                                }
                        }
                }
                this.stockRepositoryInterface.saveAll(stockList);
        }

}
