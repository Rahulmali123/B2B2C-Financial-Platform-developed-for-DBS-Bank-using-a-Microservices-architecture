package com.dbs.investment_service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dbs.investment_service.dto.request.InvestmentOrderRequest;
import com.dbs.investment_service.dto.response.InvestmentOrderResponse;
import com.dbs.investment_service.entity.InvestmentOrder;
import com.dbs.investment_service.entity.OrderType;
import com.dbs.investment_service.exception.ResourceNotFoundException;
import com.dbs.investment_service.kafka.InvestmentEvent;
import com.dbs.investment_service.kafka.InvestmentEventPublisher;
import com.dbs.investment_service.repository.InvestmentOrderRepository;
import com.dbs.investment_service.service.InvestmentOrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentOrderServiceImpl implements InvestmentOrderService {

    private final InvestmentOrderRepository repository;
    private final InvestmentEventPublisher publisher;

    @Override
    public InvestmentOrderResponse buyInvestment(InvestmentOrderRequest request) {

        InvestmentOrder order = InvestmentOrder.builder()
                .customerId(request.getCustomerId())
                .productId(request.getProductId())
                .orderType(OrderType.BUY)
                .quantity(request.getQuantity())
                .amount(request.getAmount())
                .orderStatus("PENDING")
                .orderDate(LocalDateTime.now())
                .build();

        InvestmentOrder savedOrder = repository.save(order);

        InvestmentEvent event = InvestmentEvent.builder()
                .orderId(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .productId(savedOrder.getProductId())
                .orderType(savedOrder.getOrderType().name())
                .quantity(savedOrder.getQuantity())
                .amount(savedOrder.getAmount())
                .status(savedOrder.getOrderStatus())
                .eventTime(LocalDateTime.now())
                .build();

        // Publish only Created event
        publisher.publishInvestmentCreated(event);

        return mapToResponse(savedOrder);
    }

    @Override
    public InvestmentOrderResponse sellInvestment(InvestmentOrderRequest request) {

        InvestmentOrder order = InvestmentOrder.builder()
                .customerId(request.getCustomerId())
                .productId(request.getProductId())
                .orderType(OrderType.SELL)
                .quantity(request.getQuantity())
                .amount(request.getAmount())
                .orderStatus("PENDING")
                .orderDate(LocalDateTime.now())
                .build();

        InvestmentOrder savedOrder = repository.save(order);

        InvestmentEvent event = InvestmentEvent.builder()
                .orderId(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .productId(savedOrder.getProductId())
                .orderType(savedOrder.getOrderType().name())
                .quantity(savedOrder.getQuantity())
                .amount(savedOrder.getAmount())
                .status(savedOrder.getOrderStatus())
                .eventTime(LocalDateTime.now())
                .build();

        // Publish only Created event
        publisher.publishInvestmentCreated(event);

        return mapToResponse(savedOrder);
    }

    @Override
    public List<InvestmentOrderResponse> getOrdersByCustomer(Long customerId) {

        return repository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public InvestmentOrderResponse completeInvestment(Long orderId) {

        InvestmentOrder order = repository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Investment Order not found : " + orderId));

        order.setOrderStatus("COMPLETED");

        InvestmentOrder savedOrder = repository.save(order);

        InvestmentEvent event = InvestmentEvent.builder()
                .orderId(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .productId(savedOrder.getProductId())
                .orderType(savedOrder.getOrderType().name())
                .quantity(savedOrder.getQuantity())
                .amount(savedOrder.getAmount())
                .status(savedOrder.getOrderStatus())
                .eventTime(LocalDateTime.now())
                .build();

        publisher.publishInvestmentCompleted(event);

        return mapToResponse(savedOrder);
    }

    private InvestmentOrderResponse mapToResponse(InvestmentOrder order) {

        return InvestmentOrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .productId(order.getProductId())
                .orderType(order.getOrderType())
                .quantity(order.getQuantity())
                .amount(order.getAmount())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getOrderDate())
                .build();
    }
}