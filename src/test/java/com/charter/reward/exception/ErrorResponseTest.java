package com.charter.reward.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ErrorResponse.
 */
class ErrorResponseTest {

    /**
     * Tests the constructor, getters, and setters.
     */
    @Test
    void testGetterSetterAndConstructor() {

        LocalDateTime now = LocalDateTime.now();

        ErrorResponse response = new ErrorResponse(
                now,
                400,
                "Bad Request",
                "Invalid customer id");

        assertEquals(now, response.getTimestamp());
        assertEquals(400, response.getStatus());
        assertEquals("Bad Request", response.getError());
        assertEquals("Invalid customer id", response.getMessage());

        LocalDateTime later = LocalDateTime.now();

        response.setTimestamp(later);
        response.setStatus(404);
        response.setError("Not Found");
        response.setMessage("Customer not found");

        assertEquals(later, response.getTimestamp());
        assertEquals(404, response.getStatus());
        assertEquals("Not Found", response.getError());
        assertEquals("Customer not found", response.getMessage());
    }
}