package com.charter.reward.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RewardSummaryDTO.
 */
class RewardSummaryDTOTest {

    /**
     * Tests the builder, getters, and setters.
     */
    @Test
    void testBuilderGetterSetter() {

        RewardSummaryDTO dto = RewardSummaryDTO.builder()
                .customerId(101L)
                .customerName("John")
                .monthlyRewards(new ArrayList<>())
                .transactions(new ArrayList<>())
                .totalRewards(250)
                .build();

        assertEquals(101L, dto.getCustomerId());
        assertEquals("John", dto.getCustomerName());
        assertEquals(250, dto.getTotalRewards());

        dto.setCustomerId(102L);
        dto.setCustomerName("Sachin");
        dto.setTotalRewards(500);

        assertEquals(102L, dto.getCustomerId());
        assertEquals("Sachin", dto.getCustomerName());
        assertEquals(500, dto.getTotalRewards());
    }
}