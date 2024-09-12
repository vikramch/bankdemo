package com.vikram.macquarie.bankdemo.common.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum AccountType {
    SAVINGS("Savings"),
    CURRENT("Current");

    private final String accountTypeString;

    private AccountType(String accountTypeString) {
        this.accountTypeString = accountTypeString;
    }
}
