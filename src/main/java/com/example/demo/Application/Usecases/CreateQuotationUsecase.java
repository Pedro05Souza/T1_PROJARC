package com.example.demo.Application.Usecases;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.security.SecureRandom;
import java.time.Instant;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.example.demo.Application.Dtos.CreateQuotationDto;
import com.example.demo.Application.Dtos.QuotationDto;
import com.example.demo.Application.Dtos.Assemblers.QuotationAssembler;
import com.example.demo.Domain.Entities.ProductEntity;
import com.example.demo.Domain.Entities.QuotationEntity;
import com.example.demo.Domain.Entities.QuotedProductEntity;
import com.example.demo.Infraestructure.Repositories.ProductRepository;
import com.example.demo.Infraestructure.Repositories.QuotationRepository;
import com.example.demo.Domain.Enums.StateEnum;

@Service
public class CreateQuotationUsecase {

    private final QuotationRepository quotationRepository;
    private final ProductRepository productRepository;
    private final QuotationAssembler quotationAssembler;
    private final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final SecureRandom random = new SecureRandom();

    public CreateQuotationUsecase(QuotationRepository quotationRepository,
            ProductRepository productRepository,
            QuotationAssembler quotationAssembler) {
        this.quotationRepository = quotationRepository;
        this.productRepository = productRepository;
        this.quotationAssembler = quotationAssembler;
    }

    public QuotationDto createQuotation(CreateQuotationDto createQuotationDto) throws BadRequestException {
        List<UUID> productIds = createQuotationDto.getQuotedProducts().stream()
                .map(product -> product.getProductId())
                .toList();

        List<ProductEntity> products = this.productRepository.listProductsByIds(productIds);

        Map<UUID, ProductEntity> productsMap = new HashMap<>();

        for (ProductEntity p : products) {
            productsMap.put(p.getId(), p);
        }

        if (products.isEmpty()) {
            throw new BadRequestException("No products found");
        }

        if (products.size() != productIds.size()) {
            throw new BadRequestException("Some products not found");
        }

        if (!(createQuotationDto.getCountry().equals("Brazil"))) {
            throw new BadRequestException("Country not supported");
        }

        if (!isValidState(createQuotationDto.getState())) {
            throw new BadRequestException("State not supported");
        }

        String quotationCode = generateQuotationCode();

        Instant createdAt = Instant.now();

        List<QuotedProductEntity> quotedProducts = createQuotationDto.getQuotedProducts().stream()
                .map(q -> new QuotedProductEntity(
                        productsMap.get(q.getProductId()),
                        q.getAmount()))
                .toList();

        QuotationEntity quotationEntity = new QuotationEntity(quotationCode, createQuotationDto.getCustomerName(),
                quotedProducts,
                createdAt, createQuotationDto.getCountry(), createQuotationDto.getState(),
                false);

        QuotationEntity savedQuotation = this.quotationRepository.create(quotationEntity);

        return this.quotationAssembler.toDto(savedQuotation);
    }

    private String generateQuotationCode() {
        String prefix = "#";

        String letters = randomString(4);
        String numbers = String.format("%04d", random.nextInt(10000));

        return String.format("%s%s%s", prefix, letters, numbers);
    }

    private String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(LETTERS.length());
            sb.append(LETTERS.charAt(idx));
        }
        return sb.toString();
    }

    private boolean isValidState(String state) {
        for (StateEnum stateEnum : StateEnum.values()) {
            if (stateEnum.getDisplayName().equals(state)) {
                return true;
            }
        }
        return false;
    }

}
