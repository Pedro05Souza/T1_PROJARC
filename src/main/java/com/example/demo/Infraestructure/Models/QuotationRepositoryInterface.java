package com.example.demo.Infraestructure.Models;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuotationRepositoryInterface extends JpaRepository<Quotation, UUID> {
    List<Quotation> findByCreatedAtBetween(Instant startDate, Instant endDate);
}
