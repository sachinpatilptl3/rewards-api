package com.charter.reward.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MonthlyRewardDTO.
 */
class MonthlyRewardDTOTest {

    @Test
    void testGetterSetterAndBuilder() {

        MonthlyRewardDTO dto = MonthlyRewardDTO.builder()
                .year(2025)
                .month("January")
                .points(120)
                .build();

        assertEquals(2025, dto.getYear());
        assertEquals("January", dto.getMonth());
        assertEquals(120, dto.getPoints());

        dto.setYear(2026);
        dto.setMonth("February");
        dto.setPoints(200);

        assertEquals(2026, dto.getYear());
        assertEquals("February", dto.getMonth());
        assertEquals(200, dto.getPoints());
    }
}