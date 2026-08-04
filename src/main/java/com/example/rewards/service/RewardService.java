package com.example.rewards.service;

import com.example.rewards.dto.RewardSummaryDTO;

import java.time.LocalDate;

public interface RewardService {

    RewardSummaryDTO getCustomerRewards(
            Long customerId,
            LocalDate startDate,
            LocalDate endDate);

}