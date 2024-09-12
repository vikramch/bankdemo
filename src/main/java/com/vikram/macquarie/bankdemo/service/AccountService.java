package com.vikram.macquarie.bankdemo.service;

import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Get all Accounts
    public List<Account> fetchAllAccounts() {
        return accountRepository.findAllAccounts();
    }
}
