package com.dbs.notification_service.kafka;


import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestmentEvent {

    private Long orderId;

    private Long customerId;

    private String customerName;

    private String email;

    private String mobileNumber;

    private String investmentType;

    private BigDecimal amount;

    private String status;
}