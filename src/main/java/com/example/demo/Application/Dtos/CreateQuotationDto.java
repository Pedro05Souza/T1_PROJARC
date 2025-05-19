package com.example.demo.Application.Dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateQuotationDto {

    @NotNull(message = "Products are required")
    @Size(min = 1, message = "At least one product is required")
    private List<QuotedProductDto> quotedProducts;

    @NotBlank(message = "Customer name is required")
    String customerName;

    @NotBlank(message = "Country is required")
    String country;

    @NotBlank(message = "State is required")
    String state;

}
