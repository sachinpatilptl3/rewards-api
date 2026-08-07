package com.charter.reward.dto;

import java.util.Map;

public class RewardSummaryDTO {

    private Long customerId;
    private String customerName;
    private Map<String, Integer> monthlyRewards;
    private Integer totalRewards;

    public RewardSummaryDTO() {
    }

    public RewardSummaryDTO(Long customerId,
                            String customerName,
                            Map<String, Integer> monthlyRewards,
                            Integer totalRewards) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Map<String, Integer> getMonthlyRewards() {
        return monthlyRewards;
    }

    public void setMonthlyRewards(Map<String, Integer> monthlyRewards) {
        this.monthlyRewards = monthlyRewards;
    }

    public Integer getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(Integer totalRewards) {
        this.totalRewards = totalRewards;
    }
}