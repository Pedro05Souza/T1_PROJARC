package com.example.demo.Infraestructure.Models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepositoryInterface extends JpaRepository<Product, Long> {
    
}
