package com.dbs.analytics_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskResponse {

    private String customerId;

    private String riskLevel;

    private Integer score;

}