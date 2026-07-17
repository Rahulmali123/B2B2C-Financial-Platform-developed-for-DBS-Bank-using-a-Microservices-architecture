package com.dbs.portfolio_service.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class PortfolioRequest {

    @NotNull(message = "Customer Id is required")
    private Long customerId;

    @NotBlank(message = "Portfolio Name is required")
    private String portfolioName;

    @NotBlank(message = "Portfolio Type is required")
    private String portfolioType;

    @NotNull(message = "Invested Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Invested Amount must be greater than zero")
    private BigDecimal investedAmount;

    @NotNull(message = "Current Value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Current Value must be greater than zero")
    private BigDecimal currentValue;

    private BigDecimal profitLoss;

    @NotBlank(message = "Risk Level is required")
    private String riskLevel;

    @NotBlank(message = "Status is required")
    private String status;

}