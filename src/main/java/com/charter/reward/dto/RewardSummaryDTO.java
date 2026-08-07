package com.charter.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents the reward summary for a customer.
 * <p>
 * Contains customer information, monthly reward points,
 * transaction-level reward details, and total reward points.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardSummaryDTO {

    /**
     * Customer identifier.
     */
    private Long customerId;

    /**
     * Customer name.
     */
    private String customerName;

    /**
     * Monthly reward summary.
     */
    private List<MonthlyRewardDTO> monthlyRewards;

    /**
     * Reward details for each transaction.
     */
    private List<TransactionRewardDTO> transactions;

    /**
     * Total reward points earned.
     */
    private Integer totalRewards;
}