package com.vikram.macquarie.bankdemo.controller;

import com.vikram.macquarie.bankdemo.common.error.Error;
import com.vikram.macquarie.bankdemo.common.error.ErrorCodeEnum;
import com.vikram.macquarie.bankdemo.context.RequestContext;
import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import com.vikram.macquarie.bankdemo.response.AccountTransactionsResponse;
import com.vikram.macquarie.bankdemo.response.Status;
import com.vikram.macquarie.bankdemo.service.AccountService;
import com.vikram.macquarie.bankdemo.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
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
                                   HttpServletRequest request) {
        /*
         * For demo purposes, mocking the userId through a Request Header("user_id"),
         * In Prod, userId can be obtained from Security Context.
         */
        String userId  = Optional.ofNullable(request.getHeader("user_id")).orElse("1");
        //String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        RequestContext requestContext = new RequestContext(userId);

        //Check if User has permissions to Account to access its transactions
        boolean transactionAccessPermittedForUser =
                accountService.doesUserHaveAccessToAccount(requestContext, accountNumber);
        if (!transactionAccessPermittedForUser) {
            return getAcessNotPermittedResponse(userId, accountNumber);
        }

        //Get all transactions related to the Account
        List<Transaction> accountTransactions = transactionService.getTransactionsByAccountNumber(accountNumber);
        AccountTransactionsResponse response = new AccountTransactionsResponse(Status.SUCCESS);
        response.setTransactions(accountTransactions);
        return new ResponseEntity<AccountTransactionsResponse>(response, HttpStatus.OK);

    }

    private ResponseEntity<AccountTransactionsResponse> getAcessNotPermittedResponse(
            String userId, String accountNumber
    ) {
        Error accessNotPermittedError = new Error(ErrorCodeEnum.ACCOUNT_ACCESS_DENIED,
                String.format("User=%s doesn't have permission to access Account=%s", userId, accountNumber));
        AccountTransactionsResponse response = new AccountTransactionsResponse(Status.ERROR);
        response.setErrors(List.of(accessNotPermittedError));
        return new ResponseEntity<AccountTransactionsResponse>(response, HttpStatus.UNAUTHORIZED);
    }


}
