package com.dbs.investment_service.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long customerId;


    private Long productId;


    @Enumerated(EnumType.STRING)
    private OrderType orderType;


    private Integer quantity;


    private BigDecimal amount;


    private String orderStatus;


    private LocalDateTime orderDate;


}