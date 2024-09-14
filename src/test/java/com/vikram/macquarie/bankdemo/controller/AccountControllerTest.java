package com.vikram.macquarie.bankdemo.controller;

import com.vikram.macquarie.bankdemo.common.error.ErrorCodeEnum;
import com.vikram.macquarie.bankdemo.database.entity.MockEntityUtil;
import com.vikram.macquarie.bankdemo.response.AccountListResponse;
import com.vikram.macquarie.bankdemo.response.Status;
import com.vikram.macquarie.bankdemo.service.AccountService;
import com.vikram.macquarie.bankdemo.service.DataAccessException;
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
class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AccountController accountController;

    @Test
    void testGetAccountListSuccess() throws Exception {
        // Arrange
        when(request.getHeader("user_id")).thenReturn(MockEntityUtil.mockUserEntity1.getUserId());
        when(accountService.getAllAccounts(any())).thenReturn(MockEntityUtil.getMockAccounts());

        // Act
        ResponseEntity<AccountListResponse> response = accountController.getAccountList(request, 0, 10);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Status.SUCCESS, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals(MockEntityUtil.getMockAccounts(), response.getBody().getAccounts());
    }

    @Test
    void testGetAccountListError() throws DataAccessException {
        // Arrange
        when(request.getHeader("user_id")).thenReturn(MockEntityUtil.mockUserEntity1.getUserId());
        when(accountService.getAllAccounts(any())).thenThrow(new RuntimeException());

        // Act
        ResponseEntity<AccountListResponse> response = accountController.getAccountList(request, 0, 10);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(Status.ERROR, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals(ErrorCodeEnum.ERROR_ACCESSING_ACCOUNTS_DATA.getErrorCode(), response.getBody().getErrors().getFirst().getErrorCode());
    }
}