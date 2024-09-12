package com.vikram.macquarie.bankdemo.controller;

import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.responses.AccountListResponse;
import com.vikram.macquarie.bankdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<AccountListResponse> getAccountList() {

        List<Account> accounts = accountService.fetchAllAccounts();
        AccountListResponse accountListResponse = new AccountListResponse();
        accountListResponse.setAccounts(accounts);
        return new ResponseEntity<AccountListResponse>(accountListResponse, HttpStatus.OK);
    }
}
