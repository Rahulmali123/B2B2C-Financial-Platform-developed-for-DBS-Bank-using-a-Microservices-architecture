package com.dbs.portfolio_service.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;


class GlobalExceptionHandlerTest {


    private GlobalExceptionHandler exceptionHandler;


    private HttpServletRequest request;


    @BeforeEach
    void setUp() {

        exceptionHandler = new GlobalExceptionHandler();

        request = mock(HttpServletRequest.class);

        when(request.getRequestURI())
                .thenReturn("/api/v1/portfolios/1");
    }



    @Test
    void handlePortfolioNotFound_success() {


        PortfolioNotFoundException exception =
                new PortfolioNotFoundException(
                        "Portfolio not found with id : 1"
                );


        ResponseEntity<ErrorResponse> response =
                exceptionHandler.handlePortfolioNotFound(
                        exception,
                        request
                );


        assertEquals(
                HttpStatus.NOT_FOUND,
                response.getStatusCode()
        );


        assertNotNull(response.getBody());


        assertEquals(
                404,
                response.getBody().getStatus()
        );


        assertEquals(
                "NOT_FOUND",
                response.getBody().getError()
        );


        assertEquals(
                "Portfolio not found with id : 1",
                response.getBody().getMessage()
        );


        assertEquals(
                "/api/v1/portfolios/1",
                response.getBody().getPath()
        );
    }



    @Test
    void handleDuplicatePortfolio_success() {


        DuplicatePortfolioException exception =
                new DuplicatePortfolioException(
                        "Portfolio already exists"
                );


        ResponseEntity<ErrorResponse> response =
                exceptionHandler.handleDuplicatePortfolio(
                        exception,
                        request
                );


        assertEquals(
                HttpStatus.CONFLICT,
                response.getStatusCode()
        );


        assertNotNull(response.getBody());


        assertEquals(
                409,
                response.getBody().getStatus()
        );


        assertEquals(
                "CONFLICT",
                response.getBody().getError()
        );


        assertEquals(
                "Portfolio already exists",
                response.getBody().getMessage()
        );
    }



    @Test
    void handleException_success() {


        Exception exception =
                new Exception(
                        "Something went wrong"
                );


        ResponseEntity<ErrorResponse> response =
                exceptionHandler.handleException(
                        exception,
                        request
                );


        assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                response.getStatusCode()
        );


        assertNotNull(response.getBody());


        assertEquals(
                500,
                response.getBody().getStatus()
        );


        assertEquals(
                "INTERNAL_SERVER_ERROR",
                response.getBody().getError()
        );


        assertEquals(
                "Something went wrong",
                response.getBody().getMessage()
        );
    }

}
