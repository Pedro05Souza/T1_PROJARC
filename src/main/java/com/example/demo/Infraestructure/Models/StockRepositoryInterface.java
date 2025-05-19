package com.example.demo.Infraestructure.Models;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepositoryInterface extends JpaRepository<Stock, UUID> {
    List<Stock> findByProductIdIn(List<UUID> productIds);
    Optional<Stock> findByProductId(UUID productId);
}
