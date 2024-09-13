package com.vikram.macquarie.bankdemo.mapper;

import com.vikram.macquarie.bankdemo.database.entity.TransactionEntity;
import com.vikram.macquarie.bankdemo.domain.model.Transaction;

public class TransactionMapper {
    public static Transaction getTransaction(TransactionEntity transactionEntity) {
        return new Transaction(transactionEntity.getTransactionId(),
                transactionEntity.getAccountEntity().getAccountName(),
                transactionEntity.getAccountEntity().getAccountNumber(),
                transactionEntity.getAccountEntity().getCurrency(),
                transactionEntity.getValueDate(),
                transactionEntity.getAmount(),
                transactionEntity.getTransactionType(),
                transactionEntity.getTransactionNarrative());
    }
}
