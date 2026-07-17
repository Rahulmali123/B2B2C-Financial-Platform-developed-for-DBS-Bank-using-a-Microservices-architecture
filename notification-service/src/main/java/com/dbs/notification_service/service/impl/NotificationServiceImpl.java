package com.dbs.notification_service.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dbs.notification_service.dto.NotificationRequest;
import com.dbs.notification_service.dto.NotificationResponse;
import com.dbs.notification_service.entity.Notification;
import com.dbs.notification_service.exception.NotificationNotFoundException;
import com.dbs.notification_service.repo.NotificationRepository;
import com.dbs.notification_service.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;

    @Override
    public NotificationResponse sendNotification(NotificationRequest request) {

        // Mock Email/SMS Sending
        log.info("======================================");
        log.info("Sending {} Notification", request.getType());
        log.info("Recipient : {}", request.getRecipient());
        log.info("Mobile    : {}", request.getMobileNumber());
        log.info("Message   : {}", request.getMessage());
        log.info("======================================");

        Notification notification = Notification.builder()
                .recipient(request.getRecipient())
                .mobileNumber(request.getMobileNumber())
                .message(request.getMessage())
                .type(request.getType())
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .build();

        Notification saved = repository.save(notification);

        return mapToResponse(saved);
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponse getNotificationById(Long id) {

        Notification notification = repository.findById(id)
                .orElseThrow(() ->
                        new NotificationNotFoundException(
                                "Notification not found with id : " + id));

        return mapToResponse(notification);
    }

    private NotificationResponse mapToResponse(Notification notification) {

        return NotificationResponse.builder()
                .id(notification.getId())
                .recipient(notification.getRecipient())
                .mobileNumber(notification.getMobileNumber())
                .message(notification.getMessage())
                .type(notification.getType())
                .status(notification.getStatus())
                .sentAt(notification.getSentAt())
                .build();
    }

}