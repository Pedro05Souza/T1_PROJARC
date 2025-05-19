package com.example.demo.Application.Dtos;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Data
@EqualsAndHashCode(callSuper = true)
public class ApprovedQuotationDto extends QuotationDto {
    private Double totalPrice;
    private Double appliedTax;
    private Double discount;
    private Double finalPrice;

    public ApprovedQuotationDto(UUID id, String code, String customerName, List<UUID> products,
            String createdAtIso, String country, String state, boolean isApproved,
            Double totalPrice, Double appliedTax, Double discount, Double finalPrice) {
        super(id, code, customerName, products, createdAtIso, country, state, isApproved);
        this.totalPrice = totalPrice;
        this.appliedTax = appliedTax;
        this.discount = discount;
        this.finalPrice = finalPrice;
    }

}
