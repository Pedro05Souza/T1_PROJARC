package com.example.demo.Application.Dtos;

import jakarta.validation.constraints.NotBlank;

public class GetProductStocksDto {
    
    @NotBlank(message = "Id is required")
    private String id;
}
