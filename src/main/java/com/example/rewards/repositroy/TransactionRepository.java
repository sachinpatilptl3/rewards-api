package com.example.rewards.repositroy;

import com.example.rewards.model.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAllTransactions();

}