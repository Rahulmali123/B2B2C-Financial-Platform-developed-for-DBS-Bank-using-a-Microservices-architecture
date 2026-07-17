package com.dbs.analytics_service.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.dbs.analytics_service.dto.PortfolioPerformanceResponse;
import com.dbs.analytics_service.dto.RiskResponse;
import com.dbs.analytics_service.dto.WealthInsightResponse;
import com.dbs.analytics_service.service.AnalyticsService;

@WebMvcTest(AnalyticsController.class)
class AnalyticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnalyticsService analyticsService;

    @Test
    void testGetPortfolioPerformance() throws Exception {

        PortfolioPerformanceResponse response =
                new PortfolioPerformanceResponse(
                        "CUST101",
                        100000.0,
                        120000.0,
                        20000.0,
                        20.0);

        when(analyticsService.getPortfolioPerformance("CUST101"))
                .thenReturn(response);

        mockMvc.perform(get("/analytics/performance/CUST101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value("CUST101"))
                .andExpect(jsonPath("$.investedAmount").value(100000.0))
                .andExpect(jsonPath("$.currentValue").value(120000.0));
    }

    @Test
    void testGetRisk() throws Exception {

        RiskResponse response =
                new RiskResponse(
                        "CUST101",
                        "MEDIUM",
                        70);

        when(analyticsService.getRisk("CUST101"))
                .thenReturn(response);

        mockMvc.perform(get("/analytics/risk/CUST101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.riskLevel").value("MEDIUM"))
                .andExpect(jsonPath("$.score").value(70));
    }

    @Test
    void testGetWealthInsight() throws Exception {

        WealthInsightResponse response =
                new WealthInsightResponse(
                        "CUST101",
                        "Portfolio is performing well.");

        when(analyticsService.getWealthInsight("CUST101"))
                .thenReturn(response);

        mockMvc.perform(get("/analytics/wealth/CUST101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value("CUST101"))
                .andExpect(jsonPath("$.message").value("Portfolio is performing well."));
    }
}