package com.vikram.macquarie.bankdemo.service;

import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import com.vikram.macquarie.bankdemo.repository.DatabaseException;
import com.vikram.macquarie.bankdemo.repository.TransactionRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing transactions.
 *
 * Provides business logic for:
 * - Retrieving transactions for a specific account
 *
 * Acts as an intermediary between the TransactionController and the TransactionRepository.
 */
@Service
public class TransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsByAccountNumber(@NonNull String accountNumber)
    throws DataAccessException {
        try {
            return transactionRepository.getTransactionsByAccountNumber(accountNumber);
        } catch (DatabaseException e) {
           throw new DataAccessException("Error accessing data", e);
        }

    }
}
