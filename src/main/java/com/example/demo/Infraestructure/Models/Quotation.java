package com.example.demo.Infraestructure.Models;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "quotations")
@Getter
@Setter
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    private String code;
    private String customerName;

    @OneToMany(mappedBy = "quotation", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<QuotedProduct> products;

    private Instant createdAt;

    private String country;

    private String state;

    private boolean isApproved;

    protected Quotation() {
    }

    public Quotation(String code, String customerName, List<QuotedProduct> products, Instant createdAt, String country,
            String state, boolean isApproved) {
        this.code = code;
        this.customerName = customerName;
        this.products = products;
        this.createdAt = createdAt;
        this.country = country;
        this.state = state;
        this.isApproved = isApproved;
    }

}
