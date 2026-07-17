package com.dbs.portfolio_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.portfolio_service.entity.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> 
{

    Optional<Portfolio> findByCustomerId(Long customerId);

    List<Portfolio> findAllByCustomerId(Long customerId);

    boolean existsByPortfolioName(String portfolioName);

}
