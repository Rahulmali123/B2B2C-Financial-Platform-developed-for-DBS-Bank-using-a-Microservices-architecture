package com.dbs.investment_service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dbs.investment_service.dto.request.InvestmentOrderRequest;
import com.dbs.investment_service.dto.response.InvestmentOrderResponse;
import com.dbs.investment_service.entity.OrderType;
import com.dbs.investment_service.service.InvestmentOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(InvestmentOrderController.class)
class InvestmentOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvestmentOrderService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testBuyInvestment() throws Exception {

        InvestmentOrderRequest request = InvestmentOrderRequest.builder()
                .customerId(101L)
                .productId(1L)
                .quantity(10)
                .amount(BigDecimal.valueOf(50000))
                .build();

        InvestmentOrderResponse response = InvestmentOrderResponse.builder()
                .id(1L)
                .customerId(101L)
                .productId(1L)
                .orderType(OrderType.BUY)
                .quantity(10)
                .amount(BigDecimal.valueOf(50000))
                .orderStatus("PENDING")
                .orderDate(LocalDateTime.now())
                .build();

        when(service.buyInvestment(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/investments/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").value(101))
                .andExpect(jsonPath("$.orderType").value("BUY"));
    }

    @Test
    void testSellInvestment() throws Exception {

        InvestmentOrderRequest request = InvestmentOrderRequest.builder()
                .customerId(101L)
                .productId(1L)
                .quantity(5)
                .amount(BigDecimal.valueOf(25000))
                .build();

        InvestmentOrderResponse response = InvestmentOrderResponse.builder()
                .id(2L)
                .customerId(101L)
                .productId(1L)
                .orderType(OrderType.SELL)
                .quantity(5)
                .amount(BigDecimal.valueOf(25000))
                .orderStatus("PENDING")
                .orderDate(LocalDateTime.now())
                .build();

        when(service.sellInvestment(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/investments/sell")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderType").value("SELL"));
    }

    @Test
    void testGetOrdersByCustomer() throws Exception {

        InvestmentOrderResponse response = InvestmentOrderResponse.builder()
                .id(1L)
                .customerId(101L)
                .productId(1L)
                .orderType(OrderType.BUY)
                .quantity(10)
                .amount(BigDecimal.valueOf(50000))
                .orderStatus("PENDING")
                .orderDate(LocalDateTime.now())
                .build();

        when(service.getOrdersByCustomer(101L))
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/investments/orders/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerId").value(101))
                .andExpect(jsonPath("$[0].orderType").value("BUY"));
    }

    @Test
    void testCompleteInvestment() throws Exception {

        InvestmentOrderResponse response = InvestmentOrderResponse.builder()
                .id(1L)
                .customerId(101L)
                .productId(1L)
                .orderType(OrderType.BUY)
                .quantity(10)
                .amount(BigDecimal.valueOf(50000))
                .orderStatus("COMPLETED")
                .orderDate(LocalDateTime.now())
                .build();

        when(service.completeInvestment(1L))
                .thenReturn(response);

        mockMvc.perform(put("/api/v1/investments/1/complete"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderStatus").value("COMPLETED"));
    }
}
