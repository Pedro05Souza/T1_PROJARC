package com.example.demo.Application.Services;

import org.springframework.stereotype.Component;

import com.example.demo.Domain.Entities.QuotationEntity;


@Component
public class RSTaxService implements TaxStrategyService {
    private static final double TAX = 0.10;

    @Override
    public QuotationPriceInfo calculateTax(QuotationEntity quotationDto, double totalPrice) {
        QuotationPriceInfo quotationPriceInfo = new QuotationPriceInfo();

        if (totalPrice < 100.0) {
            quotationPriceInfo.setTax(0.0);
            quotationPriceInfo.setFinalPrice(totalPrice);
        } else {
            quotationPriceInfo.setTax(totalPrice * TAX);
            quotationPriceInfo.setFinalPrice(totalPrice  - (totalPrice * TAX));
        }

        return quotationPriceInfo;

    }

}
