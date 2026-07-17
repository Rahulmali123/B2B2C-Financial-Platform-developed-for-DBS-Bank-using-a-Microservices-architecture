package com.dbs.notification_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {

    private Long id;

    private String recipient;

    private String mobileNumber;

    private String message;

    private String type;

    private String status;

    private LocalDateTime sentAt;
}