package com.dbs.analytics_service.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.analytics_service.dto.PortfolioPerformanceResponse;
import com.dbs.analytics_service.dto.RiskResponse;
import com.dbs.analytics_service.dto.WealthInsightResponse;
import com.dbs.analytics_service.entity.Analytics;
import com.dbs.analytics_service.repository.AnalyticsRepository;
import com.dbs.analytics_service.service.AnalyticsService;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    @Override
    public PortfolioPerformanceResponse getPortfolioPerformance(String customerId) {

        Optional<Analytics> analytics = analyticsRepository.findByCustomerId(customerId);

        if (analytics.isPresent()) {

            Analytics data = analytics.get();

            return new PortfolioPerformanceResponse(
                    data.getCustomerId(),
                    data.getInvestedAmount(),
                    data.getCurrentValue(),
                    data.getProfit(),
                    data.getReturnPercentage());

        }

        return null;
    }

    @Override
    public RiskResponse getRisk(String customerId) {

        Optional<Analytics> analytics = analyticsRepository.findByCustomerId(customerId);

        if (analytics.isPresent()) {

            Analytics data = analytics.get();

            return new RiskResponse(
                    data.getCustomerId(),
                    data.getRiskLevel(),
                    data.getRiskScore());

        }

        return null;
    }

    @Override
    public WealthInsightResponse getWealthInsight(String customerId) {

        Optional<Analytics> analytics = analyticsRepository.findByCustomerId(customerId);

        if (analytics.isPresent()) {

            Analytics data = analytics.get();

            return new WealthInsightResponse(
                    data.getCustomerId(),
                    data.getWealthInsight());

        }

        return null;
    }

}