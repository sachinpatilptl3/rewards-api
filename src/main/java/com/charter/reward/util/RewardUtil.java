package com.charter.reward.util;

import java.math.BigDecimal;

/**
 * Utility class for reward point calculations.
 */
public final class RewardUtil {

    private static final BigDecimal FIFTY = BigDecimal.valueOf(50);
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    /**
     * Private constructor to prevent instantiation.
     */
    private RewardUtil() {
    }

    /**
     * Calculates reward points for the given transaction amount.
     *
     * Business Rules:
     * <ul>
     *     <li>No points for amounts up to $50.</li>
     *     <li>1 point for every dollar spent over $50 up to $100.</li>
     *     <li>2 points for every dollar spent over $100, plus 50 points.</li>
     * </ul>
     *
     * @param amount transaction amount
     * @return reward points earned for the transaction
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

        return 50 + amount.subtract(HUNDRED)
                .multiply(BigDecimal.valueOf(2))
                .intValue();
    }
}