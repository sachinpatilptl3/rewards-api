package com.charter.reward.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Transaction entity.
 */
class TransactionTest {

    /**
     * Tests the getters and setters of Transaction.
     */
    @Test
    void testGetterSetter() {

        Transaction transaction = new Transaction();

        transaction.setId(1L);
        transaction.setCustomerId(101L);
        transaction.setCustomerName("John");
        transaction.setAmount(BigDecimal.valueOf(120));
        transaction.setTransactionDate(LocalDate.of(2025, 1, 10));

        assertEquals(1L, transaction.getId());
        assertEquals(101L, transaction.getCustomerId());
        assertEquals("John", transaction.getCustomerName());
        assertEquals(BigDecimal.valueOf(120), transaction.getAmount());
        assertEquals(LocalDate.of(2025, 1, 10), transaction.getTransactionDate());

        transaction.setId(2L);
        transaction.setCustomerId(102L);
        transaction.setCustomerName("Sachin");
        transaction.setAmount(BigDecimal.valueOf(150));
        transaction.setTransactionDate(LocalDate.of(2025, 2, 15));

        assertEquals(2L, transaction.getId());
        assertEquals(102L, transaction.getCustomerId());
        assertEquals("Sachin", transaction.getCustomerName());
        assertEquals(BigDecimal.valueOf(150), transaction.getAmount());
        assertEquals(LocalDate.of(2025, 2, 15), transaction.getTransactionDate());
    }
}