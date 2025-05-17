package com.example.demo.Application.Dtos.Assemblers;

import org.springframework.stereotype.Component;

import com.example.demo.Application.Dtos.QuotationDto;
import com.example.demo.Domain.Entities.QuotationEntity;

@Component
public class QuotationAssembler {

    public QuotationDto toDto(QuotationEntity quotation) {
        QuotationDto dto = new QuotationDto(quotation.getId(), quotation.getCode(), quotation.getCustomerName(),
                quotation.getProducts().stream().map(product -> product.getId()).toList(),
                quotation.getCreatedAt().toString(), quotation.getCountry(), quotation.getState(),
                quotation.isApproved());
        return dto;
    }
    
}
