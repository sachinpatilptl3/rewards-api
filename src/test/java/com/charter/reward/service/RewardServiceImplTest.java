package com.charter.reward.service;

import com.charter.reward.dto.RewardSummaryDTO;
import com.charter.reward.exception.CustomerNotFoundException;
import com.charter.reward.model.Transaction;
import com.charter.reward.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for RewardServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
class RewardServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardServiceImpl rewardService;

    /**
     * Tests successful reward calculation.
     */
    @Test
    void shouldReturnRewardSummary() {

        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setCustomerId(101L);
        transaction1.setCustomerName("John");
        transaction1.setAmount(BigDecimal.valueOf(120));
        transaction1.setTransactionDate(LocalDate.of(2025, 1, 10));

        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setCustomerId(101L);
        transaction2.setCustomerName("John");
        transaction2.setAmount(BigDecimal.valueOf(80));
        transaction2.setTransactionDate(LocalDate.of(2025, 2, 10));

        when(transactionRepository.findAll())
                .thenReturn(List.of(transaction1, transaction2));

        RewardSummaryDTO result = rewardService.getCustomerRewards(
                101L,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 3, 31));

        assertNotNull(result);
        assertEquals(101L, result.getCustomerId());
        assertEquals("John", result.getCustomerName());
        assertEquals(120, result.getTotalRewards());
        assertEquals(2, result.getTransactions().size());
        assertEquals(2, result.getMonthlyRewards().size());
    }

    /**
     * Tests customer not found.
     */
    @Test
    void shouldThrowCustomerNotFoundException() {

        when(transactionRepository.findAll())
                .thenReturn(List.of());

        assertThrows(
                CustomerNotFoundException.class,
                () -> rewardService.getCustomerRewards(
                        999L,
                        LocalDate.of(2025, 1, 1),
                        LocalDate.of(2025, 3, 31)
                )
        );
    }
}