package com.charter.reward.service;

import com.charter.reward.dto.MonthlyRewardDTO;
import com.charter.reward.dto.RewardSummaryDTO;
import com.charter.reward.dto.TransactionRewardDTO;
import com.charter.reward.exception.CustomerNotFoundException;
import com.charter.reward.model.Transaction;
import com.charter.reward.repository.TransactionRepository;
import com.charter.reward.util.RewardUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of RewardService.
 */
@Service
public class RewardServiceImpl implements RewardService {

    private final TransactionRepository transactionRepository;

    /**
     * Constructor.
     *
     * @param transactionRepository transaction repository
     */
    public RewardServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Returns customer rewards.
     *
     * @param customerId customer id
     * @param startDate start date
     * @param endDate end date
     * @return reward summary
     */
    @Override
    public RewardSummaryDTO getCustomerRewards(Long customerId,
                                               LocalDate startDate,
                                               LocalDate endDate) {

        List<Transaction> transactions = transactionRepository.findAll()
                .stream()
                .filter(t -> t.getCustomerId().equals(customerId))
                .filter(t -> !t.getTransactionDate().isBefore(startDate)
                        && !t.getTransactionDate().isAfter(endDate))
                .sorted(Comparator.comparing(Transaction::getTransactionDate))
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            throw new CustomerNotFoundException(
                    "No transactions found for customer " + customerId);
        }

        return buildRewardSummary(transactions);
    }

    /**
     * Builds reward summary.
     *
     * @param transactions transaction list
     * @return reward summary
     */
    private RewardSummaryDTO buildRewardSummary(List<Transaction> transactions) {

        Transaction first = transactions.get(0);

        // Transaction Details
        List<TransactionRewardDTO> transactionRewards = transactions.stream()
                .map(transaction -> TransactionRewardDTO.builder()
                        .transactionDate(transaction.getTransactionDate())
                        .amount(transaction.getAmount())
                        .points(RewardUtil.calculateRewardPoints(transaction.getAmount()))
                        .build())
                .collect(Collectors.toList());

        // Monthly Rewards
        Map<String, Integer> rewardMap = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getTransactionDate().getYear()
                                + "-" +
                                transaction.getTransactionDate().getMonthValue(),
                        Collectors.summingInt(
                                transaction ->
                                        RewardUtil.calculateRewardPoints(
                                                transaction.getAmount())
                        )
                ));

        List<MonthlyRewardDTO> monthlyRewards = rewardMap.entrySet()
                .stream()
                .map(entry -> {

                    String[] values = entry.getKey().split("-");

                    int year = Integer.parseInt(values[0]);
                    int monthNumber = Integer.parseInt(values[1]);

                    String monthName = Month.of(monthNumber)
                            .name()
                            .substring(0, 1)
                            + Month.of(monthNumber)
                            .name()
                            .substring(1)
                            .toLowerCase();

                    return MonthlyRewardDTO.builder()
                            .year(year)
                            .month(monthName)
                            .points(entry.getValue())
                            .build();
                })
                .sorted(
                        Comparator.comparing(MonthlyRewardDTO::getYear)
                                .thenComparing(dto ->
                                        Month.valueOf(dto.getMonth().toUpperCase()).getValue())
                )
                .collect(Collectors.toList());

        int totalRewards = transactionRewards.stream()
                .mapToInt(TransactionRewardDTO::getPoints)
                .sum();

        return RewardSummaryDTO.builder()
                .customerId(first.getCustomerId())
                .customerName(first.getCustomerName())
                .monthlyRewards(monthlyRewards)
                .transactions(transactionRewards)
                .totalRewards(totalRewards)
                .build();
    }
}