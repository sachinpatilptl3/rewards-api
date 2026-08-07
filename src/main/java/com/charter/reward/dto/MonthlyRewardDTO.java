package com.charter.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the reward points earned by a customer
 * for a specific month and year.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyRewardDTO {

    /**
     * Year for which the reward points were calculated.
     */
    private Integer year;

    /**
     * Month name (for example: January, February).
     */
    private String month;

    /**
     * Total reward points earned during the month.
     */
    private Integer points;
}