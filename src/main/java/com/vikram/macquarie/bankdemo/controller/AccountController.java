package com.vikram.macquarie.bankdemo.controller;

import com.vikram.macquarie.bankdemo.context.RequestContext;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.response.AccountListResponse;
import com.vikram.macquarie.bankdemo.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<AccountListResponse> getAccountList(HttpServletRequest request) {
        /**
         * For demo purposes, mocking the userId through a Request Header("user_id"),
         * In Prod, userId can be obtained from Security Context.
         */
        String userId  = Optional.ofNullable(request.getHeader("user_id")).orElse("1");
        //String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        RequestContext requestContext = new RequestContext(userId);
        List<Account> accounts = accountService.fetchAllAccounts(requestContext);
        AccountListResponse accountListResponse = new AccountListResponse();
        accountListResponse.setAccounts(accounts);
        return new ResponseEntity<AccountListResponse>(accountListResponse, HttpStatus.OK);
    }
}
