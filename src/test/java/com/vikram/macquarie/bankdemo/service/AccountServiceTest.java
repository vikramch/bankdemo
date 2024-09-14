package com.vikram.macquarie.bankdemo.service;

import com.vikram.macquarie.bankdemo.context.RequestContext;
import com.vikram.macquarie.bankdemo.database.entity.MockEntityUtil;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @BeforeAll
    static void setUp() {
        MockitoAnnotations.openMocks(AccountServiceTest.class);
    }

    @Mock
    private AccountRepository mockAccountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void getAllAccounts() throws Exception {
        when(mockAccountRepository.getAccountsByUserId(MockEntityUtil.mockUserEntity1.getUserId()))
                .thenReturn(MockEntityUtil.getMockAccounts());

        List<Account> accountList = accountService.getAllAccounts(
                new RequestContext(MockEntityUtil.mockUserEntity1.getUserId()));

        verify(this.mockAccountRepository, times(1))
                .getAccountsByUserId(MockEntityUtil.mockUserEntity1.getUserId());

        assertEquals(accountList, MockEntityUtil.getMockAccounts());
    }

    @Test
    void doesUserHaveAccessToAccountTrue() throws Exception {
        when(mockAccountRepository.getAccountsByUserId(MockEntityUtil.mockUserEntity1.getUserId()))
                .thenReturn(MockEntityUtil.getMockAccounts());

        boolean doesUserHaveAccessToAccount = accountService.doesUserHaveAccessToAccount(
                new RequestContext(MockEntityUtil.mockUserEntity1.getUserId()),
                        MockEntityUtil.mockAccountEntity1.getAccountNumber());

        Assertions.assertTrue(doesUserHaveAccessToAccount);
    }

    @Test
    void doesUserHaveAccessToAccountFalse() throws Exception {
        when(mockAccountRepository.getAccountsByUserId(MockEntityUtil.mockUserEntity1.getUserId()))
                .thenReturn(MockEntityUtil.getMockAccounts());

        boolean doesUserHaveAccessToAccount = accountService.doesUserHaveAccessToAccount(
                new RequestContext(MockEntityUtil.mockUserEntity1.getUserId()),
                "random_account_number");

        Assertions.assertFalse(doesUserHaveAccessToAccount);
    }
}