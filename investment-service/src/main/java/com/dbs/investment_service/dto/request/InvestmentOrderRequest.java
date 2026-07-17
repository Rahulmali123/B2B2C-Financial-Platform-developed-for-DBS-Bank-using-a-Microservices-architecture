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
public class InvestmentOrderRequest {


    private Long customerId;


    private Long productId;


    private Integer quantity;


    private BigDecimal amount;


}