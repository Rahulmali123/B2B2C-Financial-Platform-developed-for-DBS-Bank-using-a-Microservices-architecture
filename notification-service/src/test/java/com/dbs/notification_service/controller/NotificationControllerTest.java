package com.dbs.notification_service.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.dbs.notification_service.dto.NotificationResponse;
import com.dbs.notification_service.service.NotificationService;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService service;

    @Test
    void testGetNotificationById() throws Exception {

        NotificationResponse response = NotificationResponse.builder()
                .id(1L)
                .recipient("rahul@gmail.com")
                .mobileNumber("9876543210")
                .message("Investment Successful")
                .type("EMAIL")
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .build();

        when(service.getNotificationById(1L))
                .thenReturn(response);

        mockMvc.perform(get("/notifications/1"))
                .andExpect(status().isOk());

    }

}
