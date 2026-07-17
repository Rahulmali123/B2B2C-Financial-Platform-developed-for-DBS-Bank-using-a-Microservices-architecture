package com.dbs.investment_service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import com.dbs.investment_service.dto.request.InvestmentProductRequest;
import com.dbs.investment_service.dto.response.InvestmentProductResponse;
import com.dbs.investment_service.service.InvestmentProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(InvestmentProductController.class)
class InvestmentProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvestmentProductService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateProduct() throws Exception {

        InvestmentProductRequest request = InvestmentProductRequest.builder()
                .productName("HDFC Equity Fund")
                .productType("Mutual Fund")
                .category("Equity")
                .riskLevel("HIGH")
                .price(BigDecimal.valueOf(5000))
                .status("ACTIVE")
                .build();

        InvestmentProductResponse response = InvestmentProductResponse.builder()
                .id(1L)
                .productName("HDFC Equity Fund")
                .productType("Mutual Fund")
                .category("Equity")
                .riskLevel("HIGH")
                .price(BigDecimal.valueOf(5000))
                .status("ACTIVE")
                .createdDate(LocalDateTime.now())
                .build();

        when(service.createProduct(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productName").value("HDFC Equity Fund"));
    }

    @Test
    void testGetAllProducts() throws Exception {

        InvestmentProductResponse response = InvestmentProductResponse.builder()
                .id(1L)
                .productName("HDFC Equity Fund")
                .productType("Mutual Fund")
                .category("Equity")
                .riskLevel("HIGH")
                .price(BigDecimal.valueOf(5000))
                .status("ACTIVE")
                .createdDate(LocalDateTime.now())
                .build();

        when(service.getAllProducts()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName")
                        .value("HDFC Equity Fund"));
    }

    @Test
    void testGetProductById() throws Exception {

        InvestmentProductResponse response = InvestmentProductResponse.builder()
                .id(1L)
                .productName("HDFC Equity Fund")
                .productType("Mutual Fund")
                .category("Equity")
                .riskLevel("HIGH")
                .price(BigDecimal.valueOf(5000))
                .status("ACTIVE")
                .createdDate(LocalDateTime.now())
                .build();

        when(service.getProductById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateProduct() throws Exception {

        InvestmentProductRequest request = InvestmentProductRequest.builder()
                .productName("Updated Fund")
                .productType("Mutual Fund")
                .category("Hybrid")
                .riskLevel("MEDIUM")
                .price(BigDecimal.valueOf(6000))
                .status("ACTIVE")
                .build();

        InvestmentProductResponse response = InvestmentProductResponse.builder()
                .id(1L)
                .productName("Updated Fund")
                .productType("Mutual Fund")
                .category("Hybrid")
                .riskLevel("MEDIUM")
                .price(BigDecimal.valueOf(6000))
                .status("ACTIVE")
                .createdDate(LocalDateTime.now())
                .build();

        when(service.updateProduct(eq(1L), any())).thenReturn(response);

        mockMvc.perform(put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Updated Fund"));
    }

    @Test
    void testDeleteProduct() throws Exception {

        doNothing().when(service).deleteProduct(1L);

        mockMvc.perform(delete("/api/v1/products/1"))
                .andExpect(status().isOk());
    }
}