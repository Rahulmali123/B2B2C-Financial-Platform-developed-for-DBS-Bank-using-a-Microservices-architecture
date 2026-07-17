package com.dbs.investment_service.dto.response;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.dbs.investment_service.entity.OrderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentOrderResponse {


    private Long id;


    private Long customerId;


    private Long productId;


    private OrderType orderType;


    private Integer quantity;


    private BigDecimal amount;


    private String orderStatus;


    private LocalDateTime orderDate;


}
