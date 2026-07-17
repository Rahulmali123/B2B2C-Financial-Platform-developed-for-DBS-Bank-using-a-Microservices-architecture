package com.dbs.investment_service.dto.request;


import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentProductRequest {


    private String productName;


    private String productType;


    private String category;


    private String riskLevel;


    private BigDecimal price;


    private String status;


}