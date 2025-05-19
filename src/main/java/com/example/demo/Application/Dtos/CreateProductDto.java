package com.example.demo.Application.Dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;


@Getter
public class CreateProductDto {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @NotBlank(message = "SKU is required")
    @Size(min = 3, max = 20, message = "SKU must be between 3 and 20 characters")
    private String sku;

    @NotNull(message = "Max quantity is required")
    @Min(value = 0, message = "Max quantity must be >= 0")
    private Integer maxQuantity;

    @NotNull(message = "Min quantity is required")
    @Min(value = 0, message = "Min quantity must be >= 0")
    private Integer minQuantity;

    @NotNull(message = "Current Quantity is required")
    @Min(value = 0, message = "Current Quantity must be >= 0")
    private Integer currentQuantity;
}
