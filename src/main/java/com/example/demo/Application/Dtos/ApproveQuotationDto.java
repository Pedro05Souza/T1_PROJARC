package com.example.demo.Application.Dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class ApproveQuotationDto {

    @NotNull(message = "Quotation ID cannot be null")
    private UUID quotationId;

    public UUID getQuotationId() {
        return quotationId;
    }
    
}
