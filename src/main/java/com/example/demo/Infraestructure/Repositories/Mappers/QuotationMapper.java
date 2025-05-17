package com.example.demo.Infraestructure.Repositories.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.Domain.Entities.QuotationEntity;
import com.example.demo.Domain.Entities.ProductEntity;
import com.example.demo.Infraestructure.Models.Quotation;
import com.example.demo.Infraestructure.Models.Product;

@Component
public class QuotationMapper {

    private final ProductEntityMapper productEntityMapper;

    public QuotationMapper(ProductEntityMapper productEntityMapper) {
        this.productEntityMapper = productEntityMapper;
    }

    public QuotationEntity toEntity(Quotation quotationModel) {
        List<ProductEntity> products = quotationModel.getProducts().stream()
                .map(productEntityMapper::toEntity)
                .collect(Collectors.toList());

        return new QuotationEntity(
                quotationModel.getId(),
                quotationModel.getCode(),
                quotationModel.getCustomerName(),
                products,
                quotationModel.getCreatedAt(),
                quotationModel.getCountry(),
                quotationModel.getState(),
                quotationModel.isApproved());
    }

    public Quotation toModel(QuotationEntity quotationEntity) {
        List<Product> products = quotationEntity.getProducts().stream()
                .map(productEntityMapper::toModel)
                .collect(Collectors.toList());

        return new Quotation(
                quotationEntity.getCode(),
                quotationEntity.getCustomerName(),
                products,
                quotationEntity.getCreatedAt(),
                quotationEntity.getCountry(),
                quotationEntity.getState(),
                quotationEntity.isApproved());
    }

}
