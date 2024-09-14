package com.vikram.macquarie.bankdemo.integrationtest.service;

import com.vikram.macquarie.bankdemo.context.RequestContext;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.integrationtest.IntegrationTestData;
import com.vikram.macquarie.bankdemo.service.AccountService;
import com.vikram.macquarie.bankdemo.service.DataAccessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountServiceIntegrationTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testGetAllAccountsByUserId() throws DataAccessException {
        // Act
        List<Account> accounts = accountService.getAllAccounts(
                new RequestContext(IntegrationTestData.TEST_USER_ID));

        // Assert
        assertNotNull(accounts);
        assertEquals(1, accounts.size()); // Assuming there is One account in the test data
    }
}
