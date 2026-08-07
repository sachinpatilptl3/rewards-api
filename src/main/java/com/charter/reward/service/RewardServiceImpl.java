package com.charter.reward.service;

import com.charter.reward.dto.RewardSummaryDTO;
import com.charter.reward.exception.CustomerNotFoundException;
import com.charter.reward.model.Transaction;
import com.charter.reward.repository.TransactionRepository;
import com.charter.reward.util.RewardUtil;
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

        List<Transaction> transactions = transactionRepository.findAll()
                .stream()
                .filter(transaction ->
                        transaction.getCustomerId().equals(customerId))
                .filter(transaction ->
                        !transaction.getTransactionDate().isBefore(startDate)
                                && !transaction.getTransactionDate().isAfter(endDate))
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            throw new CustomerNotFoundException(
                    "No transactions found for customer " + customerId);
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
                                        RewardUtil.calculateRewardPoints(transaction.getAmount()))
                        ));

        int totalRewards = monthlyRewards.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        return new RewardSummaryDTO(
                first.getCustomerId(),
                first.getCustomerName(),
                monthlyRewards,
                totalRewards
        );
    }
}