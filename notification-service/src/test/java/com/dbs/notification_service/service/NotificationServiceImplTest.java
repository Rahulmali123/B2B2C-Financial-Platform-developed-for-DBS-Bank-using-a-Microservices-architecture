package com.dbs.notification_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dbs.notification_service.dto.NotificationRequest;
import com.dbs.notification_service.dto.NotificationResponse;
import com.dbs.notification_service.entity.Notification;
import com.dbs.notification_service.repo.NotificationRepository;
import com.dbs.notification_service.service.impl.NotificationServiceImpl;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private NotificationRepository repository;

    @InjectMocks
    private NotificationServiceImpl service;

    private Notification notification;

    @BeforeEach
    void setup() {

        notification = Notification.builder()
                .id(1L)
                .recipient("rahul@gmail.com")
                .mobileNumber("9876543210")
                .message("Investment Successful")
                .type("EMAIL")
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .build();
    }

    @Test
    void testSendNotification() {

        NotificationRequest request = NotificationRequest.builder()
                .recipient("rahul@gmail.com")
                .mobileNumber("9876543210")
                .message("Investment Successful")
                .type("EMAIL")
                .build();

        when(repository.save(any(Notification.class))).thenReturn(notification);

        NotificationResponse response = service.sendNotification(request);

        assertNotNull(response);
        assertEquals("SENT", response.getStatus());
    }

    @Test
    void testGetNotificationById() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(notification));

        NotificationResponse response =
                service.getNotificationById(1L);

        assertEquals(1L, response.getId());
    }

}
