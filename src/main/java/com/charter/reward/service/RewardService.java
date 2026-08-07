package com.charter.reward.service;

import com.charter.reward.dto.RewardSummaryDTO;

import java.time.LocalDate;

public interface RewardService {

    RewardSummaryDTO getCustomerRewards(
            Long customerId,
            LocalDate startDate,
            LocalDate endDate);

}