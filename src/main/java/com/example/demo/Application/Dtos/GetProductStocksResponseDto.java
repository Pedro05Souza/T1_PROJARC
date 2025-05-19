package com.example.demo.Application.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetProductStocksResponseDto {

    @NotBlank(message = "Id is required")
    private String id;

    @NotBlank(message = "currentQuantity is required")
    private Integer currentQuantity;

    @NotBlank(message = "maxQuantity is required")
    private Integer maxQuantity;

    @NotBlank(message = "minQuantity is required")
    private Integer minQuantity;
}
