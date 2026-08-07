package com.charter.reward.util;

import java.math.BigDecimal;

public final class RewardUtil {

    private static final BigDecimal FIFTY = BigDecimal.valueOf(50);
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    private RewardUtil() {
    }

    /**
     * Calculates reward points for a transaction amount.
     *
     * @param amount transaction amount
     * @return reward points
     */
    public static int calculateRewardPoints(BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return 0;
        }

        if (amount.compareTo(FIFTY) <= 0) {
            return 0;
        }

        if (amount.compareTo(HUNDRED) <= 0) {
            return amount.subtract(FIFTY).intValue();
        }

        return 50 + amount.subtract(HUNDRED).multiply(BigDecimal.valueOf(2)).intValue();
    }
}