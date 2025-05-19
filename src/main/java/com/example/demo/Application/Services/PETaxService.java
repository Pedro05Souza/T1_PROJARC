package com.example.demo.Application.Services;

import org.springframework.stereotype.Component;

import com.example.demo.Domain.Entities.ProductEntity;
import com.example.demo.Domain.Entities.QuotationEntity;
import com.example.demo.Domain.Entities.QuotedProductEntity;


@Component
public class PETaxService implements TaxStrategyService {
    private static final double REGULAR_TAX = 0.15;
    private static final double ESSENTIAL_TAX = 0.05;

    @Override
    public QuotationPriceInfo calculateTax(QuotationEntity quotationEntity, double totalPrice) {
        double totalTax = 0.0;

        for (QuotedProductEntity quotedProduct : quotationEntity.getProducts()) {
            ProductEntity product = quotedProduct.getProduct();
            String description = product.getDescription();
            double price = product.getPrice();
            int amount = quotedProduct.getAmount();

            boolean isEssential = description != null && description.trim().endsWith("*");
            double taxRate = isEssential ? ESSENTIAL_TAX : REGULAR_TAX;

            totalTax += price * amount * taxRate;
        }

        QuotationPriceInfo quotationPriceInfo = new QuotationPriceInfo();
        quotationPriceInfo.setTax(totalTax);
        quotationPriceInfo.setFinalPrice(totalPrice + totalTax);

        return quotationPriceInfo;
    }
}
