package com.charter.reward.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TransactionRewardDTO.
 */
class TransactionRewardDTOTest {

    /**
     * Tests the builder, getters, and setters.
     */
    @Test
    void testBuilderGetterSetter() {

        TransactionRewardDTO dto = TransactionRewardDTO.builder()
                .transactionDate(LocalDate.of(2025, 1, 10))
                .amount(BigDecimal.valueOf(120.00))
                .points(90)
                .build();

        assertEquals(LocalDate.of(2025, 1, 10), dto.getTransactionDate());
        assertEquals(BigDecimal.valueOf(120.00), dto.getAmount());
        assertEquals(90, dto.getPoints());

        dto.setTransactionDate(LocalDate.of(2025, 2, 15));
        dto.setAmount(BigDecimal.valueOf(150.00));
        dto.setPoints(150);

        assertEquals(LocalDate.of(2025, 2, 15), dto.getTransactionDate());
        assertEquals(BigDecimal.valueOf(150.00), dto.getAmount());
        assertEquals(150, dto.getPoints());
    }
}