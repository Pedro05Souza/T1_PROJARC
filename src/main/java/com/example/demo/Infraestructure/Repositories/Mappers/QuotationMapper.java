package com.example.demo.Infraestructure.Repositories.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.Domain.Entities.QuotedProductEntity;
import com.example.demo.Domain.Entities.QuotationEntity;
import com.example.demo.Infraestructure.Models.Quotation;
import com.example.demo.Infraestructure.Models.QuotedProduct;

@Component
public class QuotationMapper {
    private final ProductEntityMapper productEntityMapper;

    public QuotationMapper(ProductEntityMapper productEntityMapper) {
        this.productEntityMapper = productEntityMapper;
    }

    public QuotationEntity toEntity(Quotation quotationModel) {
        List<QuotedProductEntity> products = quotationModel.getProducts().stream()
                .map(quotedProduct -> toQuotedProductEntity(quotedProduct))
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
        List<QuotedProduct> products = quotationEntity.getProducts().stream()
                .map(quotedProductEntity -> toPartialQuotedProductModel(quotationEntity, quotedProductEntity))
                .collect(Collectors.toList());

        Quotation quotation = new Quotation(
                quotationEntity.getCode(),
                quotationEntity.getCustomerName(),
                products,
                quotationEntity.getCreatedAt(),
                quotationEntity.getCountry(),
                quotationEntity.getState(),
                quotationEntity.isApproved());

        for (QuotedProduct p : products) {
            p.setQuotation(quotation);
        }
        return quotation;
    }

    public QuotedProductEntity toQuotedProductEntity(QuotedProduct quotedProduct) {
        return new QuotedProductEntity(
                quotedProduct.getId(),
                productEntityMapper.toEntity(quotedProduct.getProduct()),
                quotedProduct.getAmount());
    }

    /**
     * This method is used to stop infinite recursion when converting
     * a QuotationEntity to a QuotedProduct. The QuotedProduct
     *
     * 
     * @param <QuotationEntity>     The QuotationEntity to convert
     * @param <QuotedProductEntity> The QuotedProductEntity to convert
     * 
     */
    private QuotedProduct toPartialQuotedProductModel(QuotationEntity quotation,
            QuotedProductEntity quotedProductEntity) {
        return new QuotedProduct(
                productEntityMapper.toModel(quotedProductEntity.getProduct()),
                quotedProductEntity.getAmount(),
                null);
    }

    public QuotedProduct toQuotedProductModel(QuotationEntity quotation, QuotedProductEntity quotedProductEntity) {
        return new QuotedProduct(
                productEntityMapper.toModel(quotedProductEntity.getProduct()),
                quotedProductEntity.getAmount(),
                toModel(quotation));
    }
}
