package com.vikram.macquarie.bankdemo.integrationtest.service;

import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import com.vikram.macquarie.bankdemo.integrationtest.IntegrationTestData;
import com.vikram.macquarie.bankdemo.service.DataAccessException;
import com.vikram.macquarie.bankdemo.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransactionServiceIntegrationTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testGetAllTransactionsByUserIdAndAccountNumber() throws DataAccessException {
        // Act
        List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(
                IntegrationTestData.TEST_ACCOUNT_NUMBER);

        // Assert
        assertNotNull(transactions);
        assertEquals(1, transactions.size()); // Assuming there is One account in the test data
    }
}
