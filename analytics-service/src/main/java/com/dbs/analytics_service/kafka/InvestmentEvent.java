package com.dbs.analytics_service.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentEvent {

    private String customerId;

    private Double investedAmount;

    private Double currentValue;

}
