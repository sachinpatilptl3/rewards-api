package com.charter.reward.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InvalidRequestException.
 */
class InvalidRequestExceptionTest {

    /**
     * Tests that the exception stores the provided message.
     */
    @Test
    void shouldCreateExceptionWithMessage() {

        String message = "Invalid request";

        InvalidRequestException exception =
                new InvalidRequestException(message);

        assertEquals(message, exception.getMessage());
    }
}