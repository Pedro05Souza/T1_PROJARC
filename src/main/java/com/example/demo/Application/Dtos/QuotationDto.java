package com.example.demo.Application.Dtos;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuotationDto {
    private UUID id;
    private String code;
    private String customerName;
    private List<UUID> products;
    private String createdAtIso;
    private String country;
    private String state;
    private boolean isApproved;
}