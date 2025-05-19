package com.example.demo.Application.Services;

import org.springframework.stereotype.Component;

import com.example.demo.Domain.Entities.QuotationEntity;
import com.example.demo.Domain.Entities.QuotedProductEntity;


@Component
public class QuotationDiscountService {
    private static final double ITEM_DISCOUNT_RATE = 0.05;
    private static final double BULK_DISCOUNT_RATE = 0.10;
    private static final int BULK_DISCOUNT_THRESHOLD = 10;
    private static final int ITEM_DISCOUNT_THRESHOLD = 3;

    public double calculateDiscount(QuotationEntity quotation) {
        double discount = 0.0;
        int totalQuantity = 0;

        for (QuotedProductEntity quotedProduct : quotation.getProducts()) {
            int quantity = quotedProduct.getAmount();
            double price = quotedProduct.getProduct().getPrice();
            totalQuantity += quantity;

            if (quantity > ITEM_DISCOUNT_THRESHOLD) {
                discount += price * quantity * ITEM_DISCOUNT_RATE;
            }
        }

        double totalBeforeDiscount = quotation.getProducts().stream()
                .mapToDouble(qp -> qp.getProduct().getPrice() * qp.getAmount())
                .sum();

        if (totalQuantity > BULK_DISCOUNT_THRESHOLD) {
            discount += totalBeforeDiscount * BULK_DISCOUNT_RATE;
        }

        return discount;
    }
}
