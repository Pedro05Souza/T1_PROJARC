package com.example.demo.Application.Usecases;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Application.Dtos.QuotationDto;
import com.example.demo.Application.Dtos.Assemblers.QuotationAssembler;
import com.example.demo.Domain.Entities.QuotationEntity;
import com.example.demo.Infraestructure.Repositories.QuotationRepository;

@Service
public class GetAllQuotationsByDate {
    private final QuotationRepository quotationRepository;
    private final QuotationAssembler quotationAssembler;

    public GetAllQuotationsByDate(QuotationRepository quotationRepository,
            QuotationAssembler quotationAssembler) {
        this.quotationRepository = quotationRepository;
        this.quotationAssembler = quotationAssembler;
    }

    public List<QuotationDto> getAllQuotationsByDate(String initialDate, String finalDate) {
        LocalDate startDateLocal = LocalDate.parse(initialDate);
        LocalDate endDateLocal = LocalDate.parse(finalDate);
        Instant startDate = startDateLocal.atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant endDate = endDateLocal.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();

        List<QuotationEntity> quotations = this.quotationRepository.quotationsByDate(startDate, endDate);
        return quotations.stream().map(quotationAssembler::toDto).toList();
    }

}
