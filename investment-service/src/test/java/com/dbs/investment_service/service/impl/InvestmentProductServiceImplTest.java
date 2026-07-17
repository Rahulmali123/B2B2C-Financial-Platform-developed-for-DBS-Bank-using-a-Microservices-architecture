package com.dbs.investment_service.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dbs.investment_service.dto.request.InvestmentProductRequest;
import com.dbs.investment_service.dto.response.InvestmentProductResponse;
import com.dbs.investment_service.entity.InvestmentProduct;
import com.dbs.investment_service.exception.ResourceNotFoundException;
import com.dbs.investment_service.repository.InvestmentProductRepository;

@ExtendWith(MockitoExtension.class)
class InvestmentProductServiceImplTest 
{

    @Mock
    private InvestmentProductRepository repository;

    @InjectMocks
    private InvestmentProductServiceImpl service;

    private InvestmentProduct product;
    private InvestmentProductRequest request;

    @BeforeEach
    void setUp() {

        request = InvestmentProductRequest.builder()
                .productName("HDFC Equity Fund")
                .productType("Mutual Fund")
                .category("Equity")
                .riskLevel("HIGH")
                .price(BigDecimal.valueOf(5000))
                .status("ACTIVE")
                .build();

        product = InvestmentProduct.builder()
                .id(1L)
                .productName("HDFC Equity Fund")
                .productType("Mutual Fund")
                .category("Equity")
                .riskLevel("HIGH")
                .price(BigDecimal.valueOf(5000))
                .status("ACTIVE")
                .createdDate(LocalDateTime.now())
                .build();
    }

    @Test
    void testCreateProduct() {

        when(repository.save(any(InvestmentProduct.class)))
                .thenReturn(product);

        InvestmentProductResponse response =
                service.createProduct(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("HDFC Equity Fund", response.getProductName());

        verify(repository, times(1)).save(any(InvestmentProduct.class));
    }

    @Test
    void testGetAllProducts() {

        when(repository.findAll())
                .thenReturn(List.of(product));

        List<InvestmentProductResponse> response =
                service.getAllProducts();

        assertEquals(1, response.size());
        assertEquals("HDFC Equity Fund",
                response.get(0).getProductName());

        verify(repository).findAll();
    }

    @Test
    void testGetProductById() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(product));

        InvestmentProductResponse response =
                service.getProductById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testGetProductById_NotFound() {

        when(repository.findById(100L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.getProductById(100L));
    }

    @Test
    void testUpdateProduct() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(product));

        when(repository.save(any(InvestmentProduct.class)))
                .thenReturn(product);

        InvestmentProductResponse response =
                service.updateProduct(1L, request);

        assertNotNull(response);
        assertEquals("HDFC Equity Fund",
                response.getProductName());

        verify(repository).save(any(InvestmentProduct.class));
    }

    @Test
    void testDeleteProduct() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(product));

        doNothing().when(repository).delete(product);

        service.deleteProduct(1L);

        verify(repository, times(1)).delete(product);
    }

    @Test
    void testDeleteProduct_NotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.deleteProduct(1L));
    }
}