package com.vikram.macquarie.bankdemo.controller;

import com.vikram.macquarie.bankdemo.common.error.Error;
import com.vikram.macquarie.bankdemo.common.error.ErrorCodeEnum;
import com.vikram.macquarie.bankdemo.context.RequestContext;
import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import com.vikram.macquarie.bankdemo.response.AccountTransactionsResponse;
import com.vikram.macquarie.bankdemo.response.Pagination;
import com.vikram.macquarie.bankdemo.response.Status;
import com.vikram.macquarie.bankdemo.service.AccountService;
import com.vikram.macquarie.bankdemo.service.DataAccessException;
import com.vikram.macquarie.bankdemo.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    private final AccountService accountService;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GetMapping("/account/{accountNumber}/transactions")
    public ResponseEntity<AccountTransactionsResponse>
    getTransactionsByAccountNumber(@PathVariable("accountNumber") String accountNumber,
                                   HttpServletRequest request,
                                   @RequestParam(defaultValue = "0") int offset,
                                   @RequestParam(defaultValue = "100") int limit) {
        /*
         * For demo purposes, getting the userId through a Request Header("user_id"),
         * In Prod, userId can be obtained from Security Context.
         */
        String userId  = Optional.ofNullable(request.getHeader("user_id")).orElse("1");
        //String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        RequestContext requestContext = new RequestContext(userId);

        //Check if User has permissions to Account to access its transactions
        ResponseEntity<AccountTransactionsResponse> errorResponse =
                validateAccountAccessForUser(requestContext, accountNumber);
        if (errorResponse != null) return errorResponse;

        //Get all transactions related to the Account
        List<Transaction> accountTransactions = null;
        try {
            accountTransactions = transactionService.getTransactionsByAccountNumber(accountNumber);
        } catch (DataAccessException e) {
            AccountTransactionsResponse response = new AccountTransactionsResponse(Status.ERROR);
            response.setErrors(List.of(new Error(ErrorCodeEnum.ERROR_ACCESSING_DATA)));
            return new ResponseEntity<AccountTransactionsResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            AccountTransactionsResponse response = new AccountTransactionsResponse(Status.ERROR);
            response.setErrors(List.of(new Error(ErrorCodeEnum.INTERNAL_SERVICE_ERROR)));
            return new ResponseEntity<AccountTransactionsResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        AccountTransactionsResponse response = new AccountTransactionsResponse(Status.SUCCESS);
        response.setTransactions(accountTransactions);
        handlePagination(offset, limit, response);
        return new ResponseEntity<AccountTransactionsResponse>(response, HttpStatus.OK);
    }

    /**
     * TODO: Implement pagination properly
     */
    private void handlePagination(int offset, int limit, AccountTransactionsResponse response) {
        response.setPagination(new Pagination(offset, limit, response.getTransactions().size()));
    }

    private ResponseEntity<AccountTransactionsResponse> validateAccountAccessForUser(
            RequestContext requestContext, String accountNumber) {
        boolean transactionAccessPermittedForUser = false;
        try {
            transactionAccessPermittedForUser =
                    accountService.doesUserHaveAccessToAccount(requestContext, accountNumber);
        } catch (Exception e) {
            AccountTransactionsResponse response = new AccountTransactionsResponse(Status.ERROR);
            response.setErrors(List.of(new Error(ErrorCodeEnum.ERROR_ACCESSING_DATA)));
            return new ResponseEntity<AccountTransactionsResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (transactionAccessPermittedForUser) {
            return null;
        }

        Error accessNotPermittedError = new Error(ErrorCodeEnum.ACCOUNT_ACCESS_DENIED,
                String.format("User=%s doesn't have permission to access Account=%s",
                        requestContext.getUserId(), accountNumber));
        AccountTransactionsResponse response = new AccountTransactionsResponse(Status.ERROR);
        response.setErrors(List.of(accessNotPermittedError));
        return new ResponseEntity<AccountTransactionsResponse>(response, HttpStatus.UNAUTHORIZED);
    }
}
