package com.dbs.analytics_service.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.dbs.analytics_service.entity.Analytics;
import com.dbs.analytics_service.repository.AnalyticsRepository;

@Component
public class InvestmentEventConsumer {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    @KafkaListener(
            topics = "investment-completed",
            groupId = "analytics-group")
    public void consume(InvestmentEvent event) {

        System.out.println("Investment Event Received : " + event);

        Analytics analytics = new Analytics();

        analytics.setCustomerId(event.getCustomerId());

        analytics.setInvestedAmount(event.getInvestedAmount());

        analytics.setCurrentValue(event.getCurrentValue());

        double profit =
                event.getCurrentValue() - event.getInvestedAmount();

        analytics.setProfit(profit);

        double percentage =
                (profit / event.getInvestedAmount()) * 100;

        analytics.setReturnPercentage(percentage);

        // Simple Risk Logic
        if (percentage > 20) {
            analytics.setRiskLevel("HIGH");
            analytics.setRiskScore(90);
        } else if (percentage > 10) {
            analytics.setRiskLevel("MEDIUM");
            analytics.setRiskScore(70);
        } else {
            analytics.setRiskLevel("LOW");
            analytics.setRiskScore(40);
        }

        analytics.setWealthInsight(
                "Portfolio updated successfully.");

        analyticsRepository.save(analytics);

        System.out.println("Analytics Saved Successfully");

    }

}