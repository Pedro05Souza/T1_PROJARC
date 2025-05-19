package com.example.demo.Application.Services;

import org.springframework.stereotype.Component;

import com.example.demo.Domain.Entities.QuotationEntity;


@Component
public class SPTaxService implements TaxStrategyService {
    private static final double TAX = 0.12;

    @Override
    public QuotationPriceInfo calculateTax(QuotationEntity quotationDto, double totalPrice) {
        QuotationPriceInfo quotationPriceInfo = new QuotationPriceInfo();
        quotationPriceInfo.setTax(TAX);
        quotationPriceInfo.setFinalPrice(totalPrice - (totalPrice * TAX));
        return quotationPriceInfo;

    }

}
