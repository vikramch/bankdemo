package com.vikram.macquarie.bankdemo.integrationtest.service;

import com.vikram.macquarie.bankdemo.context.RequestContext;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.integrationtest.database.IntegrationTestData;
import com.vikram.macquarie.bankdemo.service.AccountService;
import com.vikram.macquarie.bankdemo.service.DataAccessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertFalse(accounts.isEmpty()); // Assuming there at least One account in the test data
    }
}
