package com.vikram.macquarie.bankdemo.repository;

import com.vikram.macquarie.bankdemo.database.entity.TransactionEntity;
import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import com.vikram.macquarie.bankdemo.mapper.TransactionMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository for accessing transaction data.
 *
 * Can provide CRUD (Create, Read, Update, Delete) operations for transactions.
 *
 * Acts as an intermediary between the TransactionService and the database.
 */
@Repository
public interface TransactionRepository  extends JpaRepository<TransactionEntity, String> {
    default List<Transaction> getTransactionsByAccountNumber(String accountNumber)
    throws DatabaseException {
        try {
            return findTransactionsByAccountNumber(accountNumber).stream()
                    .map(TransactionMapper::getTransaction)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseException("Error querying database", e);
        }

    }

    @Query("SELECT t FROM TransactionEntity t WHERE t.accountEntity.accountNumber = :accountNumber")
    List<TransactionEntity> findTransactionsByAccountNumber(@Param("accountNumber") String accountNumber);
}
