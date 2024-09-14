package com.vikram.macquarie.bankdemo.controller;

import com.vikram.macquarie.bankdemo.common.error.ErrorCodeEnum;
import com.vikram.macquarie.bankdemo.database.entity.MockEntityUtil;
import com.vikram.macquarie.bankdemo.response.AccountTransactionsResponse;
import com.vikram.macquarie.bankdemo.response.Status;
import com.vikram.macquarie.bankdemo.service.AccountService;
import com.vikram.macquarie.bankdemo.service.DataAccessException;
import com.vikram.macquarie.bankdemo.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private AccountService accountService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void testGetTransactionsByAccountNumberSuccess() throws DataAccessException {
        // Arrange
        when(request.getHeader("user_id")).thenReturn(MockEntityUtil.mockUserEntity1.getUserId());
        when(accountService.doesUserHaveAccessToAccount(any(), any())).thenReturn(true);
        when(transactionService.getTransactionsByAccountNumber(MockEntityUtil.mockAccountNumber1))
                .thenReturn(MockEntityUtil.getMockTransactions());

        // Act
        ResponseEntity<AccountTransactionsResponse> response = transactionController
                .getTransactionsByAccountNumber(MockEntityUtil.mockAccountNumber1, request, 0, 100);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Status.SUCCESS, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals(MockEntityUtil.getMockTransactions(), response.getBody().getTransactions());
    }

    @Test
    void testGetTransactionsByAccountNumberAccessDenied() throws DataAccessException {
        // Arrange
        when(request.getHeader("user_id")).thenReturn(MockEntityUtil.mockUserEntity1.getUserId());
        when(accountService.doesUserHaveAccessToAccount(any(), any())).thenReturn(false);

        // Act
        ResponseEntity<AccountTransactionsResponse> response = transactionController
                .getTransactionsByAccountNumber(MockEntityUtil.mockAccountNumber1, request, 0, 100);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(Status.ERROR, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals(ErrorCodeEnum.ACCOUNT_ACCESS_DENIED.getErrorCode(), response.getBody().getErrors().getFirst().getErrorCode());
    }

    @Test
    void testGetTransactionsByAccountNumberDataAccessException() throws DataAccessException {
        // Arrange
        when(request.getHeader("user_id")).thenReturn(MockEntityUtil.mockUserEntity1.getUserId());
        when(accountService.doesUserHaveAccessToAccount(any(), any())).thenReturn(true);
        when(transactionService.getTransactionsByAccountNumber(MockEntityUtil.mockAccountNumber1))
                .thenThrow(new DataAccessException("Data access exception"));

        // Act
        ResponseEntity<AccountTransactionsResponse> response = transactionController
                .getTransactionsByAccountNumber(MockEntityUtil.mockAccountNumber1, request, 0, 100);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(Status.ERROR, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals(ErrorCodeEnum.ERROR_ACCESSING_TRANSACTIONS_DATA.getErrorCode(), response.getBody().getErrors().get(0).getErrorCode());
    }

    @Test
    void testGetTransactionsByAccountNumberInternalServerError() throws DataAccessException {
        // Arrange
        when(request.getHeader("user_id")).thenReturn(MockEntityUtil.mockUserEntity1.getUserId());
        when(accountService.doesUserHaveAccessToAccount(any(), any())).thenReturn(true);
        when(transactionService.getTransactionsByAccountNumber(MockEntityUtil.mockAccountNumber1)).thenThrow(new RuntimeException());

        // Act
        ResponseEntity<AccountTransactionsResponse> response = transactionController
                .getTransactionsByAccountNumber(MockEntityUtil.mockAccountNumber1, request, 0, 100);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(Status.ERROR, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals(ErrorCodeEnum.INTERNAL_SERVICE_ERROR.getErrorCode(), response.getBody().getErrors().getFirst().getErrorCode());
    }
}