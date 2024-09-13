package com.vikram.macquarie.bankdemo.domain.model;

import com.vikram.macquarie.bankdemo.common.enums.Currency;
import com.vikram.macquarie.bankdemo.common.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Transaction {
    private String transactionId;

    private String accountName;
    private String accountNumber;
    private Currency currency;

    private LocalDate valueDate;
    private Long amount;
    private TransactionType transactionType;
    private String transactionNarrative;
}
