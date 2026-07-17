package com.dbs.portfolio_service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dbs.portfolio_service.dto.reponse.PortfolioResponse;
import com.dbs.portfolio_service.dto.request.PortfolioRequest;
import com.dbs.portfolio_service.service.PortfolioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PortfolioController.class)
class PortfolioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PortfolioService portfolioService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void createPortfolio_success() throws Exception {

        PortfolioRequest request = PortfolioRequest.builder()
                .customerId(1L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("500000"))
                .currentValue(new BigDecimal("520000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        PortfolioResponse response = PortfolioResponse.builder()
                .id(1L)
                .customerId(1L)
                .portfolioName("Retirement Fund")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("500000"))
                .currentValue(new BigDecimal("520000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        when(portfolioService.createPortfolio(any(PortfolioRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/portfolios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Portfolio created successfully."))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.portfolioName").value("Retirement Fund"));
    }
   
    
    @Test
    void getPortfolioById_success() throws Exception {

        PortfolioResponse response =
                PortfolioResponse.builder()
                        .id(1L)
                        .customerId(101L)
                        .portfolioName("Retirement Fund")
                        .portfolioType("MUTUAL_FUND")
                        .build();


        when(portfolioService.getPortfolioById(1L))
                .thenReturn(response);


        mockMvc.perform(get("/api/v1/portfolios/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.portfolioName")
                        .value("Retirement Fund"));
    }
    
    @Test
    void getAllPortfolios_success() throws Exception {

        PortfolioResponse response1 = PortfolioResponse.builder()
                .id(1L)
                .portfolioName("Retirement Fund")
                .build();

        PortfolioResponse response2 = PortfolioResponse.builder()
                .id(2L)
                .portfolioName("Gold Portfolio")
                .build();

        when(portfolioService.getAllPortfolios())
                .thenReturn(List.of(response1, response2));

        mockMvc.perform(get("/api/v1/portfolios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].portfolioName").value("Retirement Fund"))
                .andExpect(jsonPath("$.data[1].portfolioName").value("Gold Portfolio"));
    }
    
    @Test
    void updatePortfolio_success() throws Exception {

        PortfolioRequest request = PortfolioRequest.builder()
                .customerId(1L)
                .portfolioName("Updated Portfolio")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("100000"))
                .currentValue(new BigDecimal("120000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        PortfolioResponse response = PortfolioResponse.builder()
                .id(1L)
                .customerId(1L)
                .portfolioName("Updated Portfolio")
                .portfolioType("MUTUAL_FUND")
                .investedAmount(new BigDecimal("100000"))
                .currentValue(new BigDecimal("120000"))
                .profitLoss(new BigDecimal("20000"))
                .riskLevel("MEDIUM")
                .status("ACTIVE")
                .build();

        when(portfolioService.updatePortfolio(any(Long.class), any(PortfolioRequest.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/v1/portfolios/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Portfolio updated successfully."))
                .andExpect(jsonPath("$.data.portfolioName").value("Updated Portfolio"));
    }
    
    @Test
    void deletePortfolio_success() throws Exception {

        doNothing().when(portfolioService).deletePortfolio(1L);

        mockMvc.perform(delete("/api/v1/portfolios/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Portfolio deleted successfully."))
                .andExpect(jsonPath("$.data").value("Portfolio deleted successfully."));
    }

}
