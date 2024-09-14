package com.vikram.macquarie.bankdemo.integrationtest.service;

import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import com.vikram.macquarie.bankdemo.integrationtest.database.IntegrationTestData;
import com.vikram.macquarie.bankdemo.service.DataAccessException;
import com.vikram.macquarie.bankdemo.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceIntegrationTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testGetAllTransactionsByAccountNumber() throws DataAccessException {
        // Act
        List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(
                IntegrationTestData.TEST_ACCOUNT_NUMBER);

        // Assert
        assertNotNull(transactions);
        assertFalse(transactions.isEmpty()); // Assuming there is at least One Transaction in the test data
    }
}
