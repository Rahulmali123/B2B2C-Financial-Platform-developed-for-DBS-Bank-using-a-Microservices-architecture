package com.dbs.notification_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.dbs.notification_service.dto.NotificationRequest;
import com.dbs.notification_service.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvestmentEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "investment-created",
            groupId = "notification-group")
    public void consumeInvestmentEvent(InvestmentEvent event) {

        log.info("Received Kafka Event : {}", event);

        NotificationRequest request = NotificationRequest.builder()
                .recipient(event.getEmail())
                .mobileNumber(event.getMobileNumber())
                .message("Dear " + event.getCustomerName()
                        + ", your investment of ₹"
                        + event.getAmount()
                        + " has been created successfully.")
                .type("EMAIL")
                .build();

        notificationService.sendNotification(request);

        log.info("Notification Sent Successfully.");
    }

}
