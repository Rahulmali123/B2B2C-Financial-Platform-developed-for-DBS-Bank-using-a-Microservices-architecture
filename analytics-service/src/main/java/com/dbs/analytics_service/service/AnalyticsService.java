package com.dbs.analytics_service.service;

import com.dbs.analytics_service.dto.PortfolioPerformanceResponse;
import com.dbs.analytics_service.dto.RiskResponse;
import com.dbs.analytics_service.dto.WealthInsightResponse;

public interface AnalyticsService {

    PortfolioPerformanceResponse getPortfolioPerformance(String customerId);

    RiskResponse getRisk(String customerId);

    WealthInsightResponse getWealthInsight(String customerId);

}