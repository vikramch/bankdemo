package com.vikram.macquarie.bankdemo.domain.model;

import com.vikram.macquarie.bankdemo.common.enums.AccountType;
import com.vikram.macquarie.bankdemo.common.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Account {
    private String accountNumber;
    private String accountName;
    private AccountType accountType;
    private LocalDate balanceDate;
    private Currency currency;
    private Long openingAvailableBalance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
