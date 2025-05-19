package com.example.demo.Application.Dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class QuotedProductDto {
    @NotNull(message = "Provide a product id")
    private UUID productId;

    @NotNull(message = "Amount is required")
    @Size(min = 1, message = "Amount must be greater than 0")
    private Integer amount;
}