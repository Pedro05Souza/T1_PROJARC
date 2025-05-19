package com.example.demo.Application.Services;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import com.example.demo.Application.Dtos.ApproveQuotationDto;
import com.example.demo.Application.Dtos.ApprovedQuotationDto;
import com.example.demo.Application.Usecases.ApproveQuotationUsecase;
import com.example.demo.Domain.Entities.QuotationEntity;
import com.example.demo.Domain.Enums.StateEnum;
import com.example.demo.Infraestructure.Repositories.QuotationRepository;

@Component
public class ApproveQuotationUsecaseHandler {

    private final ApproveQuotationUsecase approveQuotationUsecase;
    private final SPTaxService spTaxService;
    private final RSTaxService rsTaxService;
    private final PETaxService peTaxService;
    private final QuotationRepository quotationRepository;

    public ApproveQuotationUsecaseHandler(ApproveQuotationUsecase approveQuotationUsecase,
            SPTaxService spTaxService, RSTaxService rsTaxService, PETaxService peTaxService,
            QuotationRepository quotationRepository) {
        this.approveQuotationUsecase = approveQuotationUsecase;
        this.spTaxService = spTaxService;
        this.rsTaxService = rsTaxService;
        this.peTaxService = peTaxService;
        this.quotationRepository = quotationRepository;
    }

    /**
     * Handles the approval of a quotation based on its state.
     *
     * @param approveQuotationDto The DTO containing the quotation ID to be approved.
     * @return The approved quotation DTO.
     * @throws BadRequestException If the quotation is not found or if the state is invalid.
     */
    public ApprovedQuotationDto handler(ApproveQuotationDto approveQuotationDto) throws BadRequestException {
        

        QuotationEntity quotation = this.quotationRepository.getById(approveQuotationDto.getQuotationId());

        if (quotation.getState().equals(StateEnum.RIO_GRANDE_DO_SUL.getDisplayName())) {
            return this.approveQuotationUsecase.approveQuotation(quotation, rsTaxService);
        } else if (quotation.getState().equals(StateEnum.SAO_PAULO.getDisplayName())) {
            return this.approveQuotationUsecase.approveQuotation(quotation, spTaxService);
        } else if (quotation.getState().equals(StateEnum.PERNAMBUCO.getDisplayName())) {
            return this.approveQuotationUsecase.approveQuotation(quotation, peTaxService);
        } else {
            throw new IllegalArgumentException("Invalid state");
        }
    }
    
}
