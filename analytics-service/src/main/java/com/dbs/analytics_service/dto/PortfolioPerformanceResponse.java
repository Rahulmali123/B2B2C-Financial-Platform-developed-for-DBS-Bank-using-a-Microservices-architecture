package com.dbs.analytics_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioPerformanceResponse {

    private String customerId;

    private Double investedAmount;

    private Double currentValue;

    private Double profit;

    private Double returnPercentage;

}
