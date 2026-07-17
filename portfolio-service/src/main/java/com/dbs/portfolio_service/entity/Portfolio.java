package com.dbs.portfolio_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "portfolio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false, length = 100)
    private String portfolioName;

    @Column(nullable = false, length = 50)
    private String portfolioType;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal investedAmount;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal currentValue;

    @Column(precision = 15, scale = 2)
    private BigDecimal profitLoss;

    @Column(length = 20)
    private String riskLevel;

    @Column(length = 20)
    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}