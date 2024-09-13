package com.vikram.macquarie.bankdemo.service;

import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import com.vikram.macquarie.bankdemo.repository.DatabaseException;
import com.vikram.macquarie.bankdemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsByAccountNumber(String accountNumber)
    throws DataAccessException {
        try {
            return transactionRepository.getTransactionsByAccountNumber(accountNumber);
        } catch (DatabaseException e) {
           throw new DataAccessException("Error accessing data", e);
        }

    }
}
