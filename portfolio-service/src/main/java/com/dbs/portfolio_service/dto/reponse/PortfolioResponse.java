package com.dbs.portfolio_service.dto.reponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioResponse {

    private Long id;

    private Long customerId;

    private String portfolioName;

    private String portfolioType;

    private BigDecimal investedAmount;

    private BigDecimal currentValue;

    private BigDecimal profitLoss;

    private String riskLevel;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}