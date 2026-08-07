package com.charter.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents the reward information for an individual transaction.
 * <p>
 * This DTO contains the transaction date, transaction amount,
 * and the reward points earned for that transaction.
 * </p>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRewardDTO {

    /**
     * Date on which the transaction occurred.
     */
    private LocalDate transactionDate;

    /**
     * Transaction amount.
     */
    private BigDecimal amount;

    /**
     * Reward points earned for the transaction.
     */
    private Integer points;
}