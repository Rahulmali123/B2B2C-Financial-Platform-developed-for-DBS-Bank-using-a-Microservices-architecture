package com.dbs.investment_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.investment_service.entity.InvestmentOrder;

public interface InvestmentOrderRepository extends JpaRepository<InvestmentOrder, Long> {

	List<InvestmentOrder> findByCustomerId(Long customerId);

}