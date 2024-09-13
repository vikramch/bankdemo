package com.vikram.macquarie.bankdemo.mapper;

import com.vikram.macquarie.bankdemo.database.entity.AccountEntity;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import org.junit.jupiter.api.Test;

import static com.vikram.macquarie.bankdemo.database.entity.MockEntityUtil.mockAccountEntity1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountMapperTest {

    @Test
    void testGetAccount() {
        // Arrange
        AccountEntity accountEntity = mockAccountEntity1;

        // Act
        Account account = AccountMapper.getAccount(accountEntity);

        // Assert
        assertEquals(mockAccountEntity1.getAccountNumber(), account.getAccountNumber());
        assertEquals(mockAccountEntity1.getAccountName(), account.getAccountName());
        assertEquals(mockAccountEntity1.getAccountType(), account.getAccountType());
        assertEquals(mockAccountEntity1.getBalanceDate(), account.getBalanceDate());
        assertEquals(mockAccountEntity1.getCurrency(), account.getCurrency());
        assertEquals(mockAccountEntity1.getOpeningAvailableBalance(), account.getOpeningAvailableBalance());
    }

    @Test
    void testGetAccountWithNullEntity() {
        // Act and Assert
        assertThrows(NullPointerException.class, () -> AccountMapper.getAccount(null));
    }
}