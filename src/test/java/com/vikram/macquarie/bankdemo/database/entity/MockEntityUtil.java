package com.vikram.macquarie.bankdemo.database.entity;

import com.vikram.macquarie.bankdemo.common.enums.AccountType;
import com.vikram.macquarie.bankdemo.common.enums.Currency;
import com.vikram.macquarie.bankdemo.common.enums.TransactionType;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import com.vikram.macquarie.bankdemo.mapper.AccountMapper;
import com.vikram.macquarie.bankdemo.mapper.TransactionMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MockEntityUtil {
    public static final UserEntity mockUserEntity1 = new UserEntity("1");
    static {
        mockUserEntity1.setUserName("VIKRAM");
    }

    public static final String mockAccountNumber1 = "585309209";

    public static AccountEntity mockAccountEntity1 = new AccountEntity(mockAccountNumber1);
    static {
        mockAccountEntity1.setUserEntity(mockUserEntity1);
        mockAccountEntity1.setAccountName("SGSavings726");
        mockAccountEntity1.setAccountType(AccountType.SAVINGS);
        mockAccountEntity1.setBalanceDate(LocalDate.of(2018, 11, 8));
        mockAccountEntity1.setCurrency(Currency.SGD);
        mockAccountEntity1.setOpeningAvailableBalance(new BigDecimal("84327.51"));
    }


    public static final TransactionEntity mockTransactionEntity1 = new TransactionEntity("1");
    static {
        mockTransactionEntity1.setAccountEntity(mockAccountEntity1);
        mockTransactionEntity1.setValueDate(LocalDate.of(2012, 1, 2));
        mockTransactionEntity1.setAmount(new BigDecimal("9540.98"));
        mockTransactionEntity1.setTransactionType(TransactionType.CREDIT);
    }


    public static final List<AccountEntity> mockAccountEntities = List.of(mockAccountEntity1);

    public static final List<TransactionEntity> mockTransactionEntities = List.of(mockTransactionEntity1);

    public static List<Transaction> getMockTransactions() {
        return mockTransactionEntities.stream().map(TransactionMapper::getTransaction).collect(Collectors.toList());
    }

    public static List<Account> getMockAccounts() {
        return mockAccountEntities.stream().map(AccountMapper::getAccount).collect(Collectors.toList());
    }
}