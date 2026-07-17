package com.dbs.investment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.investment_service.entity.InvestmentProduct;

public interface InvestmentProductRepository extends JpaRepository<InvestmentProduct, Long> {

}
