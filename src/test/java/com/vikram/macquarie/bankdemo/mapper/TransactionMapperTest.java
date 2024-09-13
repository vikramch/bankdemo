package com.vikram.macquarie.bankdemo.mapper;

import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import org.junit.jupiter.api.Test;

import static com.vikram.macquarie.bankdemo.database.entity.MockEntityUtil.mockTransactionEntity1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionMapperTest {

    @Test
    void testGetTransaction() {
        // Act
        Transaction transaction = TransactionMapper.getTransaction(mockTransactionEntity1);

        // Assert
        assertEquals(mockTransactionEntity1.getTransactionId(), transaction.getTransactionId());
        assertEquals(mockTransactionEntity1.getAccountEntity().getAccountName(), transaction.getAccountName());
        assertEquals(mockTransactionEntity1.getAccountEntity().getAccountNumber(), transaction.getAccountNumber());
        assertEquals(mockTransactionEntity1.getAccountEntity().getCurrency(), transaction.getCurrency());
        assertEquals(mockTransactionEntity1.getValueDate(), transaction.getValueDate());
        assertEquals(mockTransactionEntity1.getAmount(), transaction.getAmount());
        assertEquals(mockTransactionEntity1.getTransactionType(), transaction.getTransactionType());
        assertEquals(mockTransactionEntity1.getTransactionNarrative(), transaction.getTransactionNarrative());
    }

    @Test
    void testGetTransactionWithNullEntity() {
        // Act and Assert
        assertThrows(NullPointerException.class, () -> TransactionMapper.getTransaction(null));
    }
}