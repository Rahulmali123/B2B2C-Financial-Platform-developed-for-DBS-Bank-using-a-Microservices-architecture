package com.dbs.investment_service.kafka;

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
public class InvestmentEvent {

    private Long orderId;

    private Long customerId;

    private Long productId;

    private String orderType;

    private Integer quantity;

    private BigDecimal amount;

    private String status;

    private LocalDateTime eventTime;

}