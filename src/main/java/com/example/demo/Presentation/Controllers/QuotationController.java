package com.example.demo.Presentation.Controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Application.Dtos.ApproveQuotationDto;
import com.example.demo.Application.Dtos.CreateQuotationDto;
import com.example.demo.Application.Dtos.QuotationDto;
import com.example.demo.Application.Usecases.ApproveQuotationUsecase;
import com.example.demo.Application.Usecases.CreateQuotationUsecase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/quotations")
public class QuotationController {

    private final CreateQuotationUsecase createQuotationUsecase;
    private final ApproveQuotationUsecase approveQuotationUsecase;

    public QuotationController(CreateQuotationUsecase createQuotationUsecase,
            ApproveQuotationUsecase approveQuotationUsecase) {
        this.createQuotationUsecase = createQuotationUsecase;
        this.approveQuotationUsecase = approveQuotationUsecase;
    }
    
    @PostMapping
    public QuotationDto createQuotation(@Valid @RequestBody CreateQuotationDto createQuotationDto) throws BadRequestException {
        return createQuotationUsecase.createQuotation(createQuotationDto);
    }

    @PostMapping("/approve")
    public QuotationDto approveQuotation(@Valid @RequestBody ApproveQuotationDto approveQuotationDto) throws BadRequestException {
        return approveQuotationUsecase.approveQuotation(approveQuotationDto);
    }


}
