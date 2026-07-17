package com.dbs.analytics_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WealthInsightResponse {

    private String customerId;

    private String message;

}
