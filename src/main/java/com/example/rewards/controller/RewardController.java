package com.example.rewards.controller;

import com.example.rewards.dto.RewardSummaryDTO;
import com.example.rewards.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<RewardSummaryDTO> getCustomerRewards(
            @PathVariable Long customerId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return ResponseEntity.ok(
                rewardService.getCustomerRewards(customerId, startDate, endDate)
        );
    }
}