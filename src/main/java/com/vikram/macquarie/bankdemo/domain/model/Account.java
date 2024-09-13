package com.vikram.macquarie.bankdemo.domain.model;

import com.vikram.macquarie.bankdemo.common.enums.AccountType;
import com.vikram.macquarie.bankdemo.common.enums.Currency;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Account {
    private String accountNumber;
    private String accountName;
    private AccountType accountType;
    private LocalDate balanceDate;
    private Currency currency;
    private BigDecimal openingAvailableBalance;
}
