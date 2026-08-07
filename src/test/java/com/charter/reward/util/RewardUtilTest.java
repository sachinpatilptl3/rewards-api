package com.charter.reward.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RewardUtilTest {

    @Test
    void shouldReturnZeroForNegativeAmount() {
        assertEquals(0,
                RewardUtil.calculateRewardPoints(BigDecimal.valueOf(-10)));
    }

    @Test
    void shouldReturnZeroForZeroAmount() {
        assertEquals(0,
                RewardUtil.calculateRewardPoints(BigDecimal.ZERO));
    }

    @Test
    void shouldReturnZeroForFifty() {
        assertEquals(0,
                RewardUtil.calculateRewardPoints(BigDecimal.valueOf(50)));
    }

    @Test
    void shouldReturnFiftyForHundred() {
        assertEquals(50,
                RewardUtil.calculateRewardPoints(BigDecimal.valueOf(100)));
    }

    @Test
    void shouldReturnOneHundredFiftyForOneFifty() {
        assertEquals(150,
                RewardUtil.calculateRewardPoints(BigDecimal.valueOf(150)));
    }
}