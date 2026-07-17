package com.dbs.notification_service.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GlobalExceptionHandlerTest {

    @Test
    void testNotificationException() {

        NotificationNotFoundException ex =
                new NotificationNotFoundException("Not Found");

        assertEquals("Not Found", ex.getMessage());

    }

}