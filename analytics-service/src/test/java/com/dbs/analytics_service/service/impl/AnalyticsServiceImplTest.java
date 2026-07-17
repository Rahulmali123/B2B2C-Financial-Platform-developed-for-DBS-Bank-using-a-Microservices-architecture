package com.dbs.analytics_service.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dbs.analytics_service.dto.PortfolioPerformanceResponse;
import com.dbs.analytics_service.dto.RiskResponse;
import com.dbs.analytics_service.dto.WealthInsightResponse;
import com.dbs.analytics_service.entity.Analytics;
import com.dbs.analytics_service.repository.AnalyticsRepository;

class AnalyticsServiceImplTest {

    @Mock
    private AnalyticsRepository analyticsRepository;

    @InjectMocks
    private AnalyticsServiceImpl analyticsService;

    private Analytics analytics;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        analytics = new Analytics();
        analytics.setId(1L);
        analytics.setCustomerId("CUST101");
        analytics.setInvestedAmount(100000.0);
        analytics.setCurrentValue(120000.0);
        analytics.setProfit(20000.0);
        analytics.setReturnPercentage(20.0);
        analytics.setRiskLevel("MEDIUM");
        analytics.setRiskScore(70);
        analytics.setWealthInsight("Portfolio is performing well.");
    }

    @Test
    void testGetPortfolioPerformance() {

        when(analyticsRepository.findByCustomerId("CUST101"))
                .thenReturn(Optional.of(analytics));

        PortfolioPerformanceResponse response =
                analyticsService.getPortfolioPerformance("CUST101");

        assertNotNull(response);
        assertEquals("CUST101", response.getCustomerId());
        assertEquals(100000.0, response.getInvestedAmount());
        assertEquals(120000.0, response.getCurrentValue());
    }

    @Test
    void testGetRisk() {

        when(analyticsRepository.findByCustomerId("CUST101"))
                .thenReturn(Optional.of(analytics));

        RiskResponse response =
                analyticsService.getRisk("CUST101");

        assertNotNull(response);
        assertEquals("MEDIUM", response.getRiskLevel());
        assertEquals(70, response.getScore());
    }

    @Test
    void testGetWealthInsight() {

        when(analyticsRepository.findByCustomerId("CUST101"))
                .thenReturn(Optional.of(analytics));

        WealthInsightResponse response =
                analyticsService.getWealthInsight("CUST101");

        assertNotNull(response);
        assertEquals("Portfolio is performing well.",
                response.getMessage());
    }

}