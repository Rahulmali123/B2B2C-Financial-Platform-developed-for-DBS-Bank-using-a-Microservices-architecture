package com.dbs.analytics_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.analytics_service.dto.PortfolioPerformanceResponse;
import com.dbs.analytics_service.dto.RiskResponse;
import com.dbs.analytics_service.dto.WealthInsightResponse;
import com.dbs.analytics_service.service.AnalyticsService;

@RestController
@RequestMapping("/analytics")
@CrossOrigin("*")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    // Portfolio Performance
    @GetMapping("/performance/{customerId}")
    public ResponseEntity<PortfolioPerformanceResponse> getPortfolioPerformance(
            @PathVariable String customerId) {

        PortfolioPerformanceResponse response =
                analyticsService.getPortfolioPerformance(customerId);

        return ResponseEntity.ok(response);
    }

    // Risk Calculation
    @GetMapping("/risk/{customerId}")
    public ResponseEntity<RiskResponse> getRisk(
            @PathVariable String customerId) {

        RiskResponse response =
                analyticsService.getRisk(customerId);

        return ResponseEntity.ok(response);
    }

    // Wealth Insights
    @GetMapping("/wealth/{customerId}")
    public ResponseEntity<WealthInsightResponse> getWealthInsight(
            @PathVariable String customerId) {

        WealthInsightResponse response =
                analyticsService.getWealthInsight(customerId);

        return ResponseEntity.ok(response);
    }

}
