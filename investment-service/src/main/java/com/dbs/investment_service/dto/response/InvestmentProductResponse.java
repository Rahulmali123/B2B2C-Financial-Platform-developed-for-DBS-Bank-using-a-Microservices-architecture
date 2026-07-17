package com.dbs.investment_service.dto.response;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentProductResponse {


    private Long id;


    private String productName;


    private String productType;


    private String category;


    private String riskLevel;


    private BigDecimal price;


    private String status;


    private LocalDateTime createdDate;


}