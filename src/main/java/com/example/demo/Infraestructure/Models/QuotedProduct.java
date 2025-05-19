package com.example.demo.Infraestructure.Models;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "quoted_products")
public class QuotedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    private Quotation quotation;

    protected QuotedProduct() {
    }

    public QuotedProduct(Product product, Integer amount, Quotation quotation) {
        this.product = product;
        this.amount = amount;
        this.quotation = quotation;
    }

    public UUID getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getAmount() {
        return amount;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }
}
