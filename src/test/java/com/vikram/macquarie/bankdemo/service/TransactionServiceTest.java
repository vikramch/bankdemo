package com.vikram.macquarie.bankdemo.service;

import com.vikram.macquarie.bankdemo.database.entity.MockEntityUtil;
import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import com.vikram.macquarie.bankdemo.repository.TransactionRepository;
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
class TransactionServiceTest {

    @BeforeAll
    static void setUp() {
        MockitoAnnotations.openMocks(TransactionServiceTest.class);
    }

    @Mock
    private TransactionRepository mockTransactionRepository;

    @InjectMocks
    private TransactionService transactionService;



    @Test
    void getTransactionsByAccountNumber() throws Exception {
        when(this.mockTransactionRepository.getTransactionsByAccountNumber(MockEntityUtil.mockAccountNumber1))
                .thenReturn(MockEntityUtil.getMockTransactions());

        List<Transaction> transactionList = this.transactionService.getTransactionsByAccountNumber(MockEntityUtil.mockAccountNumber1);

        verify(this.mockTransactionRepository, times(1))
                .getTransactionsByAccountNumber(MockEntityUtil.mockAccountNumber1);

        assertEquals(transactionList, MockEntityUtil.getMockTransactions());
    }
}