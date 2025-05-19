package com.example.demo.Application.Services;

import com.example.demo.Domain.Entities.QuotationEntity;

public interface TaxStrategyService {

    /**
     * Calculate the tax amount based on the quotation entity and total price.
     *
     * @param quotationDto the quotation entity
     * @param totalPrice the total price of the quotation
     * @return QuotationPriceInfo containing the tax amount and total price
     */
    QuotationPriceInfo calculateTax(QuotationEntity quotationDto, double totalPrice);
     
}
