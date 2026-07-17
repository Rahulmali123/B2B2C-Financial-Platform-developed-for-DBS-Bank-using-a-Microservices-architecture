package com.dbs.investment_service.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import com.dbs.investment_service.dto.request.InvestmentOrderRequest;
import com.dbs.investment_service.dto.response.InvestmentOrderResponse;
import com.dbs.investment_service.entity.InvestmentOrder;
import com.dbs.investment_service.entity.OrderType;
import com.dbs.investment_service.exception.ResourceNotFoundException;
import com.dbs.investment_service.kafka.InvestmentEventPublisher;
import com.dbs.investment_service.repository.InvestmentOrderRepository;

@ExtendWith(MockitoExtension.class)
class InvestmentOrderServiceImplTest {

    @Mock
    private InvestmentOrderRepository repository;

    @Mock
    private InvestmentEventPublisher publisher;

    @InjectMocks
    private InvestmentOrderServiceImpl service;

    private InvestmentOrder order;
    private InvestmentOrderRequest request;

    @BeforeEach
    void setUp() {

        request = InvestmentOrderRequest.builder()
                .customerId(101L)
                .productId(1L)
                .quantity(10)
                .amount(BigDecimal.valueOf(50000))
                .build();

        order = InvestmentOrder.builder()
                .id(1L)
                .customerId(101L)
                .productId(1L)
                .orderType(OrderType.BUY)
                .quantity(10)
                .amount(BigDecimal.valueOf(50000))
                .orderStatus("PENDING")
                .orderDate(LocalDateTime.now())
                .build();
    }

    @Test
    void testBuyInvestment() {

        when(repository.save(any(InvestmentOrder.class)))
                .thenReturn(order);

        InvestmentOrderResponse response =
                service.buyInvestment(request);

        assertNotNull(response);
        assertEquals(101L, response.getCustomerId());
        assertEquals(OrderType.BUY, response.getOrderType());

        verify(repository).save(any(InvestmentOrder.class));
        verify(publisher).publishInvestmentCreated(any());
    }

    @Test
    void testSellInvestment() {

        order.setOrderType(OrderType.SELL);

        when(repository.save(any(InvestmentOrder.class)))
                .thenReturn(order);

        InvestmentOrderResponse response =
                service.sellInvestment(request);

        assertNotNull(response);
        assertEquals(OrderType.SELL, response.getOrderType());

        verify(repository).save(any(InvestmentOrder.class));
        verify(publisher).publishInvestmentCreated(any());
    }

    @Test
    void testGetOrdersByCustomer() {

        when(repository.findByCustomerId(101L))
                .thenReturn(List.of(order));

        List<InvestmentOrderResponse> response =
                service.getOrdersByCustomer(101L);

        assertEquals(1, response.size());
        assertEquals(101L, response.get(0).getCustomerId());

        verify(repository).findByCustomerId(101L);
    }

    @Test
    void testCompleteInvestment() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(order));

        order.setOrderStatus("COMPLETED");

        when(repository.save(any(InvestmentOrder.class)))
                .thenReturn(order);

        InvestmentOrderResponse response =
                service.completeInvestment(1L);

        assertEquals("COMPLETED",
                response.getOrderStatus());

        verify(repository).save(any());
        verify(publisher).publishInvestmentCompleted(any());
    }

    @Test
    void testCompleteInvestment_NotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.completeInvestment(1L));
    }

}