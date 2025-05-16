package com.example.demo.Infraestructure.Models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

    private Integer maxQuantity;
    private Integer minQuantity;
    private Integer currentQuantity;

    protected Stock() {
    }


    public Stock(Product product, Integer maxQuantity, Integer minQuantity, Integer currentQuantity) {
        this.product = product;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
    }


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


    public Integer getMaxQuantity() {
        return maxQuantity;
    }


    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }


    public Integer getMinQuantity() {
        return minQuantity;
    }


    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    
}
