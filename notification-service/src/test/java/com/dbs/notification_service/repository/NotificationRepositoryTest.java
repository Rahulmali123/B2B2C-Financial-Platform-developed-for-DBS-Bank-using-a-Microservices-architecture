package com.dbs.notification_service.repository;


import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dbs.notification_service.entity.Notification;
import com.dbs.notification_service.repo.NotificationRepository;

@DataJpaTest
class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository repository;

    @Test
    void testSaveNotification() {

        Notification notification = Notification.builder()
                .recipient("rahul@gmail.com")
                .mobileNumber("9876543210")
                .message("Investment Successful")
                .type("EMAIL")
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .build();

        Notification saved = repository.save(notification);

        assertFalse(saved.getId() == null);

    }

}