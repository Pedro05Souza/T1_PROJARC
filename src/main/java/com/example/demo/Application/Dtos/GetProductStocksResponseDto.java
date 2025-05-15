package com.example.demo.Application.Dtos;

import jakarta.validation.constraints.NotBlank;

public class GetProductStocksResponseDto {

    @NotBlank(message = "Id is required")
    private String id;

    @NotBlank(message = "currentQuantity is required")
    private Integer currentQuantity;

    @NotBlank(message = "maxQuantity is required")
    private Integer maxQuantity;

    @NotBlank(message = "minQuantity is required")
    private Integer minQuantity;

    public GetProductStocksResponseDto(String id, Integer currentQuantity, Integer maxQuantity, Integer minQuantity) {
        this.id = id;
        this.currentQuantity = currentQuantity;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }
    
    
}
