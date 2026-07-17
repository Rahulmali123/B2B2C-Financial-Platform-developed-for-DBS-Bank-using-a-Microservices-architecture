package com.dbs.notification_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {

    @Email(message = "Invalid email address")
    private String recipient;

    private String mobileNumber;

    @NotBlank(message = "Message cannot be empty")
    private String message;

    @NotBlank(message = "Notification type is required")
    private String type; // EMAIL or SMS
}