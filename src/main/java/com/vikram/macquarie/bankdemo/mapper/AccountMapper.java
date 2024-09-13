package com.vikram.macquarie.bankdemo.mapper;

import com.vikram.macquarie.bankdemo.database.entity.AccountEntity;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public static Account getAccount(AccountEntity accountEntity) {
        return new Account(accountEntity.getAccountNumber(),
                accountEntity.getAccountName(),
                accountEntity.getAccountType(),
                accountEntity.getBalanceDate(),
                accountEntity.getCurrency(),
                accountEntity.getOpeningAvailableBalance());
    }
}
