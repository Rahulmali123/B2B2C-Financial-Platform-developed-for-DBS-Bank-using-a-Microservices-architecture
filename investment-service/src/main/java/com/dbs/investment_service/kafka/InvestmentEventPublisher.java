package com.dbs.investment_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String CREATED_TOPIC = "investment-created";
    private static final String COMPLETED_TOPIC = "investment-completed";

    public void publishInvestmentCreated(InvestmentEvent event) {
        kafkaTemplate.send(CREATED_TOPIC, event);
    }

    public void publishInvestmentCompleted(InvestmentEvent event) {
        kafkaTemplate.send(COMPLETED_TOPIC, event);
    }
}