package com.example.demo.Infraestructure.Repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.Domain.Entities.QuotationEntity;
import com.example.demo.Infraestructure.Models.Quotation;
import com.example.demo.Infraestructure.Models.QuotationRepositoryInterface;
import com.example.demo.Infraestructure.Repositories.Mappers.QuotationMapper;

@Repository
public class QuotationRepository {
    private final QuotationRepositoryInterface quotationRepositoryInterface;
    private final QuotationMapper quotationMapper;

    public QuotationRepository(QuotationRepositoryInterface quotationRepositoryInterface,
            QuotationMapper quotationMapper) {
        this.quotationRepositoryInterface = quotationRepositoryInterface;
        this.quotationMapper = quotationMapper;
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
        Optional<Quotation> quotationOptional = quotationRepositoryInterface.findById(quotation.getId());

        if (quotationOptional.isEmpty()) {
            throw new RuntimeException("Quotation not found");
        }

        Quotation quotationModel = quotationOptional.get();
        quotationModel.setCode(quotation.getCode());
        quotationModel.setCustomerName(quotation.getCustomerName());
        quotationModel.setCountry(quotation.getCountry());
        quotationModel.setState(quotation.getState());
        quotationModel.setApproved(quotation.isApproved());

        Quotation savedquotation = quotationRepositoryInterface.save(quotationModel);
        return quotationMapper.toEntity(savedquotation);
    }

    public List<QuotationEntity> quotationsByDate(Instant startDate, Instant endDate) {
        List<Quotation> quotations = quotationRepositoryInterface
                .findByCreatedAtBetween(startDate, endDate);

        return quotations.stream().map(quotationMapper::toEntity).collect(Collectors.toList());
    }

}
