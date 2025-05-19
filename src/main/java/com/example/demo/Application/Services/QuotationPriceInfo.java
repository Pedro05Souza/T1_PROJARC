package com.example.demo.Application.Services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class QuotationPriceInfo {
    
    private Double tax;
    private Double finalPrice;
    private Double discount;
    
}
