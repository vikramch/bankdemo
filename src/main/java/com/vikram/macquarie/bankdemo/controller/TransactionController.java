package com.vikram.macquarie.bankdemo.controller;

import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import com.vikram.macquarie.bankdemo.response.AccountTransactionsResponse;
import com.vikram.macquarie.bankdemo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/account/{accountNumber}/transactions")
    public ResponseEntity<AccountTransactionsResponse> getTransactionsByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        List<Transaction> accountTransactions = transactionService.getTransactionsByAccountNumber(accountNumber);
        AccountTransactionsResponse response = new AccountTransactionsResponse();
        response.setTransactions(accountTransactions);
        return new ResponseEntity<AccountTransactionsResponse>(response, HttpStatus.OK);

    }

    @GetMapping("/transactions")
    public ResponseEntity<AccountTransactionsResponse> getAllTransactions() {
        List<Transaction> accountTransactions = transactionService.getAllTransactions();
        AccountTransactionsResponse response = new AccountTransactionsResponse();
        response.setTransactions(accountTransactions);
        return new ResponseEntity<AccountTransactionsResponse>(response, HttpStatus.OK);
    }


}
