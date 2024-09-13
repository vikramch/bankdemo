package com.vikram.macquarie.bankdemo.service;

import com.vikram.macquarie.bankdemo.context.RequestContext;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.repository.AccountRepository;
import com.vikram.macquarie.bankdemo.repository.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Get all Accounts
    public List<Account> fetchAllAccounts(RequestContext requestContext)
    throws DataAccessException {
        try {
            return accountRepository.getAccountsByUserId(requestContext.getUserId());
        } catch (DatabaseException e) {
            throw new DataAccessException("Error accessing data", e);
        }

    }

    public  boolean doesUserHaveAccessToAccount(RequestContext requestContext, String accountNumber)
    throws DataAccessException {
        List<Account> accounts = fetchAllAccounts(requestContext);
        return accounts.stream().anyMatch(
                (account) -> accountNumber.equals(account.getAccountNumber()));
    }
}
