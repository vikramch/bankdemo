package com.vikram.macquarie.bankdemo.common.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TransactionType {
    CREDIT("Credit"),
    Debit("Debit");

    private final String transactionTypeString;

    private TransactionType(String transactionTypeString) {
        this.transactionTypeString = transactionTypeString;
    }
}
