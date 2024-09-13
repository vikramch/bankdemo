package com.vikram.macquarie.bankdemo.repository;

import com.vikram.macquarie.bankdemo.database.entity.AccountEntity;
import com.vikram.macquarie.bankdemo.database.entity.TransactionEntity;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface TransactionRepository  extends JpaRepository<TransactionEntity, String> {
    default List<Transaction> findAllTransactionsByAccountNumber(String accountNumber) {
        return getTransactionsByAccountNumber(accountNumber).stream()
                .map(transactionEntity -> {
                    Transaction transaction = new Transaction(transactionEntity.getTransactionId(),
                            transactionEntity.getAccountEntity().getAccountName(),
                            transactionEntity.getAccountEntity().getAccountNumber(),
                            transactionEntity.getAccountEntity().getCurrency(),
                            transactionEntity.getValueDate(),
                            transactionEntity.getAmount(),
                            transactionEntity.getTransactionType(),
                            transactionEntity.getTransactionNarrative());

                    return transaction;
                })
                .collect(Collectors.toList());
    }

    @Query("SELECT t FROM TransactionEntity t WHERE t.accountEntity.accountNumber = :accountNumber")
    List<TransactionEntity> getTransactionsByAccountNumber(@Param("accountNumber") String accountNumber);

    default List<Transaction> findAllTransactions() {
        return findAll().stream()
                .map(transactionEntity -> {
                    Transaction transaction = new Transaction(transactionEntity.getTransactionId(),
                            transactionEntity.getAccountEntity().getAccountName(),
                            transactionEntity.getAccountEntity().getAccountNumber(),
                            transactionEntity.getAccountEntity().getCurrency(),
                            transactionEntity.getValueDate(),
                            transactionEntity.getAmount(),
                            transactionEntity.getTransactionType(),
                            transactionEntity.getTransactionNarrative());

                    return transaction;
                })
                .collect(Collectors.toList());
    }
}
