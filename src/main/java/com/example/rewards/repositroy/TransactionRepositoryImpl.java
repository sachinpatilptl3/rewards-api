package com.example.rewards.repositroy;



import com.example.rewards.model.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @Override
    public List<Transaction> findAllTransactions() {

        return List.of(

                new Transaction(
                        1L,
                        101L,
                        "John",
                        120.00,
                        LocalDate.of(2025, 1, 10)
                ),

                new Transaction(
                        2L,
                        101L,
                        "John",
                        75.00,
                        LocalDate.of(2025, 1, 15)
                ),

                new Transaction(
                        3L,
                        101L,
                        "John",
                        200.00,
                        LocalDate.of(2025, 2, 5)
                ),

                new Transaction(
                        4L,
                        102L,
                        "Alice",
                        90.00,
                        LocalDate.of(2025, 1, 20)
                ),

                new Transaction(
                        5L,
                        102L,
                        "Alice",
                        130.00,
                        LocalDate.of(2025, 3, 10)
                ),

                new Transaction(
                        6L,
                        102L,
                        "Alice",
                        55.00,
                        LocalDate.of(2025, 3, 18)
                )

        );
    }

}
