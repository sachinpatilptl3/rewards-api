package com.charter.reward.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CustomerNotFoundException.
 */
class CustomerNotFoundExceptionTest {

    /**
     * Tests the exception message.
     */
    @Test
    void shouldCreateExceptionWithMessage() {

        String message = "Customer not found";

        CustomerNotFoundException exception =
                new CustomerNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }
}