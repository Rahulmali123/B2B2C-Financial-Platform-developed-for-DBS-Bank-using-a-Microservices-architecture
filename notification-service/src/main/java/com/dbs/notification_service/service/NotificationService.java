package com.dbs.notification_service.service;

import java.util.List;

import com.dbs.notification_service.dto.NotificationRequest;
import com.dbs.notification_service.dto.NotificationResponse;

public interface NotificationService {

    NotificationResponse sendNotification(NotificationRequest request);

    List<NotificationResponse> getAllNotifications();

    NotificationResponse getNotificationById(Long id);

}