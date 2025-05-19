package com.example.demo.Infraestructure.Models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stocks")
@Getter
@Setter
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
        this.currentQuantity = currentQuantity;
    }

}
