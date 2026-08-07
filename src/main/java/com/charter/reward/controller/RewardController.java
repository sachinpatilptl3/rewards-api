package com.charter.reward.controller;

import com.charter.reward.dto.RewardSummaryDTO;
import com.charter.reward.exception.InvalidRequestException;
import com.charter.reward.service.RewardService;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/rewards")
@Validated
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/customers/{customerId}/rewards")
    public ResponseEntity<RewardSummaryDTO> getCustomerRewards(

            @PathVariable
            @Positive(message = "Customer Id must be greater than zero")
            Long customerId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate) {

        if (startDate == null || endDate == null) {
            throw new InvalidRequestException(
                    "Start date and End date are mandatory.");
        }

        if (startDate.isAfter(endDate)) {
            throw new InvalidRequestException(
                    "Start date cannot be after End date.");
        }

        return ResponseEntity.ok(
                rewardService.getCustomerRewards(
                        customerId,
                        startDate,
                        endDate));
    }
}