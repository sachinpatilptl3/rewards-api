package com.example.rewards.service;

import com.example.rewards.dto.RewardSummaryDTO;

import java.util.List;

public interface RewardService {

    List<RewardSummaryDTO> getCustomerRewards();

}