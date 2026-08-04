package com.example.rewards.service;

import com.example.rewards.dto.RewardSummaryDTO;
import com.example.rewards.model.Transaction;
import com.example.rewards.repositroy.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {

    private final TransactionRepository transactionRepository;

    public RewardServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public RewardSummaryDTO getCustomerRewards(Long customerId,
                                               LocalDate startDate,
                                               LocalDate endDate) {

        List<Transaction> transactions = transactionRepository.findAllTransactions()
                .stream()
                .filter(transaction ->
                        transaction.getCustomerId().equals(customerId))
                .filter(transaction ->
                        !transaction.getTransactionDate().isBefore(startDate)
                                && !transaction.getTransactionDate().isAfter(endDate))
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            throw new RuntimeException("No transactions found for customer.");
        }

        return buildRewardSummary(transactions);
    }

    private RewardSummaryDTO buildRewardSummary(List<Transaction> transactions) {

        Transaction first = transactions.get(0);

        Map<String, Integer> monthlyRewards =
                transactions.stream()
                        .collect(Collectors.groupingBy(
                                transaction -> transaction.getTransactionDate()
                                        .getMonth()
                                        .name(),
                                LinkedHashMap::new,
                                Collectors.summingInt(transaction ->
                                        calculateRewardPoints(transaction.getAmount()))
                        ));

        int totalRewards =
                monthlyRewards.values()
                        .stream()
                        .mapToInt(Integer::intValue)
                        .sum();

        return RewardSummaryDTO.builder()
                .customerId(first.getCustomerId())
                .customerName(first.getCustomerName())
                .monthlyRewards(monthlyRewards)
                .totalRewards(totalRewards)
                .build();
    }

    private int calculateRewardPoints(Double amount) {

        if (amount <= 50) {
            return 0;
        }

        if (amount <= 100) {
            return (int) (amount - 50);
        }

        return (int) ((amount - 100) * 2 + 50);
    }
}