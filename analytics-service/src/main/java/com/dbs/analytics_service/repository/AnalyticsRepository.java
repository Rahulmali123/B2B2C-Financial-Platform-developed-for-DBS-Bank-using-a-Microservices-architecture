package com.dbs.analytics_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.analytics_service.entity.Analytics;

@Repository
public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {

    Optional<Analytics> findByCustomerId(String customerId);

}