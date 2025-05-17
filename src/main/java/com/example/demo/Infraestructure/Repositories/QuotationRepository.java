package com.example.demo.Infraestructure.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.Domain.Entities.QuotationEntity;
import com.example.demo.Infraestructure.Models.Product;
import com.example.demo.Infraestructure.Models.Quotation;
import com.example.demo.Infraestructure.Models.QuotationRepositoryInterface;
import com.example.demo.Infraestructure.Repositories.Mappers.ProductEntityMapper;
import com.example.demo.Infraestructure.Repositories.Mappers.QuotationMapper;

@Repository
public class QuotationRepository {
    private final QuotationRepositoryInterface quotationRepositoryInterface;
    private final QuotationMapper quotationMapper;
    private final ProductEntityMapper productEntityMapper;

    public QuotationRepository(QuotationRepositoryInterface quotationRepositoryInterface,
            QuotationMapper quotationMapper,
            ProductEntityMapper productEntityMapper) {
        this.quotationRepositoryInterface = quotationRepositoryInterface;
        this.quotationMapper = quotationMapper;
        this.productEntityMapper = productEntityMapper;
    }

    public QuotationEntity getById(UUID id) {
        Optional<Quotation> quotation = quotationRepositoryInterface.findById(id);

        if (quotation.isPresent()) {
            return quotationMapper.toEntity(quotation.get());
        } else {
            return null;
        }
    }

    public QuotationEntity create(QuotationEntity quotation) {
        Quotation quotationModel = quotationMapper.toModel(quotation);
        Quotation savedquotation = quotationRepositoryInterface.save(quotationModel);
        return quotationMapper.toEntity(savedquotation);
    }

    public QuotationEntity update(QuotationEntity quotation) {
        Quotation quotationModel = quotationMapper.toModel(quotation);
        Quotation savedquotation = quotationRepositoryInterface.save(quotationModel);
        return quotationMapper.toEntity(savedquotation);
    }

}
