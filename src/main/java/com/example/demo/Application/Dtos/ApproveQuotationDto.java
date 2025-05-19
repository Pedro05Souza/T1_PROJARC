package com.example.demo.Application.Dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class ApproveQuotationDto {

    @NotNull(message = "Quotation ID cannot be null")
    private UUID quotationId;
}
