package com.charter.reward.service;

import com.charter.reward.dto.RewardSummaryDTO;

import java.time.LocalDate;

/**
 * Service interface for calculating customer reward points.
 */
public interface RewardService {

    /**
     * Calculates the reward points earned by a customer for the
     * specified date range.
     *
     * @param customerId the customer identifier
     * @param startDate the start date of the reward period
     * @param endDate the end date of the reward period
     * @return RewardSummaryDTO containing customer reward details
     */
    RewardSummaryDTO getCustomerRewards(
            Long customerId,
            LocalDate startDate,
            LocalDate endDate);

}