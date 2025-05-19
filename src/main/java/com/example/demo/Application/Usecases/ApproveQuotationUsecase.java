package com.example.demo.Application.Usecases;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.example.demo.Application.Dtos.ApprovedQuotationDto;
import com.example.demo.Application.Dtos.QuotationDto;
import com.example.demo.Application.Dtos.Assemblers.QuotationAssembler;
import com.example.demo.Application.Services.QuotationDiscountService;
import com.example.demo.Application.Services.QuotationPriceInfo;
import com.example.demo.Application.Services.TaxStrategyService;
import com.example.demo.Domain.Entities.QuotationEntity;
import com.example.demo.Domain.Entities.QuotedProductEntity;
import com.example.demo.Infraestructure.Repositories.ProductStock;
import com.example.demo.Infraestructure.Repositories.QuotationRepository;
import com.example.demo.Infraestructure.Repositories.StockRepository;

import jakarta.transaction.Transactional;

@Service
public class ApproveQuotationUsecase {
    private static final Double FEDERAL_TAX = 0.15;
    private final QuotationRepository quotationRepository;
    private final StockRepository stocksRepository;
    private final QuotationAssembler quotationAssembler;
    private final QuotationDiscountService quotationDiscountService;

    public ApproveQuotationUsecase(QuotationRepository quotationRepository,
            QuotationAssembler quotationAssembler,
            StockRepository stocksRepository,
            QuotationDiscountService quotationDiscountService) {
        this.quotationRepository = quotationRepository;
        this.quotationAssembler = quotationAssembler;
        this.stocksRepository = stocksRepository;
        this.quotationDiscountService = quotationDiscountService;
    }

    @Transactional
    public ApprovedQuotationDto approveQuotation(QuotationEntity quotation, TaxStrategyService taxStrategyService)
            throws BadRequestException {

        validateQuotation(quotation);

        List<UUID> productIds = quotation.getProducts().stream()
                .map(product -> product.getProduct().getId())
                .toList();

        List<ProductStock> productsInStock = this.stocksRepository.getAllProductsStockByIds(productIds);

        validateProductsInStock(productsInStock, productIds);

        updateStockQuantities(productsInStock, quotation.getProducts());

        approveQuotationAndPersistChanges(quotation, productsInStock);

        Double totalPrice = calculateTotalPrice(quotation);
        QuotationPriceInfo quotationPriceInfo = calculateQuotationPriceInfo(quotation, taxStrategyService, totalPrice);
        Double discount = calculateDiscount(quotation);
        Double finalPrice = calculateFinalPrice(quotationPriceInfo, discount);

        return buildApprovedQuotationDto(quotation, totalPrice, quotationPriceInfo, discount, finalPrice);
    }

    private void validateQuotation(QuotationEntity quotation) throws BadRequestException {
        if (Instant.now().isAfter(quotation.getCreatedAt().plus(21, ChronoUnit.DAYS))) {
            throw new BadRequestException("Quotation is expired");
        }
        if (quotation.isApproved()) {
            throw new BadRequestException("Quotation is already approved");
        }
    }

    private void validateProductsInStock(List<ProductStock> productsInStock, List<UUID> productIds)
            throws BadRequestException {
        if (productsInStock.isEmpty()) {
            throw new BadRequestException("Quotation has no products");
        }
        if (productsInStock.size() != productIds.size()) {
            throw new BadRequestException("Some products are not in stock");
        }
        if (!isProductStockValid(productsInStock)) {
            throw new BadRequestException("Insufficient stock for some products");
        }
    }

    private void updateStockQuantities(List<ProductStock> productsInStock, List<QuotedProductEntity> quotedProduct)
            throws BadRequestException {
        Map<UUID, Integer> quantitiesToUpdate = quotedProduct.stream()
                .collect(Collectors.toMap(
                        product -> product.getProduct().getId(),
                        QuotedProductEntity::getAmount));

        for (ProductStock productStock : productsInStock) {
            Integer quantityToUpdate = quantitiesToUpdate.get(productStock.getProductId());
            if (quantityToUpdate != null) {
                if (productStock.getCurrentQuantity() - quantityToUpdate < productStock.getMinQuantity()) {
                    System.out.println(productStock.getCurrentQuantity() - quantityToUpdate);
                    throw new BadRequestException("Insufficient stock for product: " + productStock.getProductId());
                }
    
                productStock.setCurrentQuantity(productStock.getCurrentQuantity() - quantityToUpdate);

            } else {
                throw new BadRequestException("Product not found in stock");
            }
        }   
    }

    private void approveQuotationAndPersistChanges(QuotationEntity quotation, List<ProductStock> productsInStock) {
        quotation.setApproved(true);
        this.quotationRepository.update(quotation);
        this.stocksRepository.bulkUpdate(productsInStock);
    }

    private Double calculateTotalPrice(QuotationEntity quotation) {
        return quotation.getProducts().stream()
                .mapToDouble(product -> product.getProduct().getPrice() * product.getAmount())
                .sum();
    }

    private QuotationPriceInfo calculateQuotationPriceInfo(QuotationEntity quotation,
            TaxStrategyService taxStrategyService,
            Double totalPrice) {
        return taxStrategyService.calculateTax(quotation, totalPrice);
    }

    private Double calculateDiscount(QuotationEntity quotation) {
        return this.quotationDiscountService.calculateDiscount(quotation);
    }

    private Double calculateFinalPrice(QuotationPriceInfo quotationPriceInfo, Double discount) {
        Double finalPrice = quotationPriceInfo.getFinalPrice() - discount;
        finalPrice -= finalPrice * FEDERAL_TAX;
        quotationPriceInfo.setFinalPrice(finalPrice);
        quotationPriceInfo.setDiscount(discount);
        return finalPrice;
    }

    private ApprovedQuotationDto buildApprovedQuotationDto(QuotationEntity quotation, Double totalPrice,
            QuotationPriceInfo quotationPriceInfo, Double discount, Double finalPrice) {
        QuotationDto quotationDto = this.quotationAssembler.toDto(quotation);

        return new ApprovedQuotationDto(
                quotationDto.getId(),
                quotationDto.getCode(),
                quotationDto.getCustomerName(),
                quotationDto.getProducts(),
                quotationDto.getCreatedAtIso(),
                quotationDto.getCountry(),
                quotationDto.getState(),
                true,
                totalPrice,
                quotationPriceInfo.getTax(),
                discount,
                finalPrice);
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
