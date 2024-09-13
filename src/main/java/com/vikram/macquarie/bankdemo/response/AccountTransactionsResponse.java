package com.vikram.macquarie.bankdemo.response;

import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class AccountTransactionsResponse {
    private List<Transaction> transactions;
    private int totalCount;
    private int pageSize;
    private int pageNumber;
}
