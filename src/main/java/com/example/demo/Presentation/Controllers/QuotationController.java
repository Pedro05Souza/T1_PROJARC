package com.example.demo.Presentation.Controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Application.Dtos.ApproveQuotationDto;
import com.example.demo.Application.Dtos.ApprovedQuotationDto;
import com.example.demo.Application.Dtos.CreateQuotationDto;
import com.example.demo.Application.Dtos.QuotationDto;
import com.example.demo.Application.Services.ApproveQuotationUsecaseHandler;
import com.example.demo.Application.Usecases.CreateQuotationUsecase;
import com.example.demo.Application.Usecases.GetAllQuotationsByDate;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/quotations")
public class QuotationController {

    private final CreateQuotationUsecase createQuotationUsecase;
    private final ApproveQuotationUsecaseHandler approveQuotationUsecaseHandler;
    private final GetAllQuotationsByDate getAllQuotationsByDate;

    public QuotationController(CreateQuotationUsecase createQuotationUsecase,
            ApproveQuotationUsecaseHandler approveQuotationUsecaseHandler,
            GetAllQuotationsByDate getAllQuotationsByDate) {
        this.createQuotationUsecase = createQuotationUsecase;
        this.approveQuotationUsecaseHandler = approveQuotationUsecaseHandler;
        this.getAllQuotationsByDate = getAllQuotationsByDate;
    }

    @PostMapping
    public QuotationDto createQuotation(@Valid @RequestBody CreateQuotationDto createQuotationDto)
            throws BadRequestException {
        return createQuotationUsecase.createQuotation(createQuotationDto);
    }

    @PostMapping("/approve")
    public ApprovedQuotationDto approveQuotation(@Valid @RequestBody ApproveQuotationDto approveQuotationDto)
            throws BadRequestException {
        return approveQuotationUsecaseHandler.handler(approveQuotationDto);
    }

    @GetMapping("/by-date")
    public List<QuotationDto> getAllQuotationsByDate(@Valid @RequestParam (name = "initialDate") String initialDate,
            @RequestParam(name = "finalDate") String finalDate) throws BadRequestException {
        return getAllQuotationsByDate.getAllQuotationsByDate(initialDate, finalDate);
    }

}
