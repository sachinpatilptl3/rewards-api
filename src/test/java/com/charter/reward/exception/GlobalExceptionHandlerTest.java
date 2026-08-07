package com.charter.reward.exception;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GlobalExceptionHandler.
 */
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler =
            new GlobalExceptionHandler();

    /**
     * Tests CustomerNotFoundException handling.
     */
    @Test
    void handleCustomerNotFound() {

        CustomerNotFoundException exception =
                new CustomerNotFoundException("Customer not found");

        ResponseEntity<ErrorResponse> response =
                exceptionHandler.handleCustomerNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Customer Not Found", response.getBody().getError());
        assertEquals("Customer not found", response.getBody().getMessage());
    }

    /**
     * Tests InvalidRequestException handling.
     */
    @Test
    void handleInvalidRequest() {

        InvalidRequestException exception =
                new InvalidRequestException("Invalid request");

        ResponseEntity<ErrorResponse> response =
                exceptionHandler.handleInvalidRequest(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Bad Request", response.getBody().getError());
        assertEquals("Invalid request", response.getBody().getMessage());
    }

    /**
     * Tests ConstraintViolationException handling.
     */
    @Test
    void handleConstraintViolation() {

        ConstraintViolationException exception =
                new ConstraintViolationException("Validation failed", null);

        ResponseEntity<ErrorResponse> response =
                exceptionHandler.handleConstraintViolation(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Validation Failed", response.getBody().getError());
    }

    /**
     * Tests generic exception handling.
     */
    @Test
    void handleException() {

        Exception exception =
                new Exception("Unexpected error");

        ResponseEntity<ErrorResponse> response =
                exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals("Internal Server Error",
                response.getBody().getError());

        assertEquals("Unexpected error",
                response.getBody().getMessage());
    }
}