package com.example.demo.Application.Usecases;

import java.util.List;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.example.demo.Application.Dtos.ApproveQuotationDto;
import com.example.demo.Application.Dtos.QuotationDto;
import com.example.demo.Application.Dtos.Assemblers.QuotationAssembler;
import com.example.demo.Domain.Entities.ProductEntity;
import com.example.demo.Domain.Entities.QuotationEntity;
import com.example.demo.Infraestructure.Repositories.ProductStock;
import com.example.demo.Infraestructure.Repositories.QuotationRepository;
import com.example.demo.Infraestructure.Repositories.StockRepository;

import jakarta.transaction.Transactional;

@Service
public class ApproveQuotationUsecase {
    private final QuotationRepository quotationRepository;
    private final StockRepository stocksRepository;
    private final QuotationAssembler quotationAssembler;

    public ApproveQuotationUsecase(QuotationRepository quotationRepository,
            QuotationAssembler quotationAssembler,
            StockRepository stocksRepository) {
        this.quotationRepository = quotationRepository;
        this.quotationAssembler = quotationAssembler;
        this.stocksRepository = stocksRepository;
    }

    @Transactional
    public QuotationDto approveQuotation(ApproveQuotationDto approveQuotationDto) throws BadRequestException {
        QuotationEntity quotation = this.quotationRepository.getById(approveQuotationDto.getQuotationId());


        List<UUID> productIds = quotation.getProducts().stream()
                .map(ProductEntity::getId)
                .toList();
        
        List<ProductStock> productsInStock = this.stocksRepository.getAllProductsStockByIds(productIds);

        if (productsInStock.isEmpty()) {
            throw new BadRequestException("Quotation has no products");
        }

        if (productsInStock.size() != productIds.size()) {
            throw new BadRequestException("Some products are not in stock");
        }

        if (!isProductStockValid(productsInStock)) {
            throw new BadRequestException("Some products are not in stock");
        }

        if (quotation.isApproved()) {
            throw new BadRequestException("Quotation is already approved");
        }

        for (ProductStock product : productsInStock) {
            product.setCurrentQuantity(product.getCurrentQuantity() - 1);
        }

        quotation.setApproved(true);
        this.quotationRepository.update(quotation);
        this.stocksRepository.bulkUpdate(quotation.getProducts(), productsInStock);
        return this.quotationAssembler.toDto(quotation);

    }

    public boolean isProductStockValid(List<ProductStock> productsInStock) {

        for (ProductStock product : productsInStock) {
            if (product.getCurrentQuantity() == 0 || product.getCurrentQuantity() < product.getMinQuantity()) {
                return false;
            }
        }
        return true;
    }
}
