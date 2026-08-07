package com.charter.reward.controller;

import com.charter.reward.dto.RewardSummaryDTO;
import com.charter.reward.service.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for RewardController.
 */
@WebMvcTest(RewardController.class)
class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    /**
     * Tests successful retrieval of customer rewards.
     */
    @Test
    void getCustomerRewards_Success() throws Exception {

        RewardSummaryDTO response = RewardSummaryDTO.builder()
                .customerId(101L)
                .customerName("John")
                .monthlyRewards(Collections.emptyList())
                .transactions(Collections.emptyList())
                .totalRewards(90)
                .build();

        when(rewardService.getCustomerRewards(
                eq(101L),
                any(LocalDate.class),
                any(LocalDate.class)))
                .thenReturn(response);

        mockMvc.perform(get("/api/rewards/customers/101/rewards")
                        .param("startDate", "2025-01-01")
                        .param("endDate", "2025-03-31")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(101))
                .andExpect(jsonPath("$.customerName").value("John"))
                .andExpect(jsonPath("$.totalRewards").value(90));
    }

    /**
     * Tests invalid request when startDate is after endDate.
     */
    @Test
    void getCustomerRewards_InvalidDateRange() throws Exception {

        mockMvc.perform(get("/api/rewards/customers/101/rewards")
                        .param("startDate", "2025-04-01")
                        .param("endDate", "2025-01-01"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Tests invalid customer id.
     */
    @Test
    void getCustomerRewards_InvalidCustomerId() throws Exception {

        mockMvc.perform(get("/api/rewards/customers/0/rewards")
                        .param("startDate", "2025-01-01")
                        .param("endDate", "2025-03-31"))
                .andExpect(status().isBadRequest());
    }
}