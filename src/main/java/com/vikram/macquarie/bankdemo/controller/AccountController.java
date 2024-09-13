package com.vikram.macquarie.bankdemo.controller;

import com.vikram.macquarie.bankdemo.common.error.Error;
import com.vikram.macquarie.bankdemo.common.error.ErrorCodeEnum;
import com.vikram.macquarie.bankdemo.context.RequestContext;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.response.AccountListResponse;
import com.vikram.macquarie.bankdemo.response.Pagination;
import com.vikram.macquarie.bankdemo.response.Status;
import com.vikram.macquarie.bankdemo.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@Slf4j
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<AccountListResponse> getAccountList(HttpServletRequest request,
                                                              @RequestParam(defaultValue = "0") int offset,
                                                              @RequestParam(defaultValue = "10") int limit) {
        /*
         * For demo purposes, getting the userId through a Request Header("user_id"),
         * In Prod, userId can be obtained from Security Context.
         */
        String userId  = Optional.ofNullable(request.getHeader("user_id")).orElse("1");
        //String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        RequestContext requestContext = new RequestContext(userId);

        //Fetch all accessible accounts
        try {
            List<Account> accounts = accountService.fetchAllAccounts(requestContext);
            AccountListResponse accountListResponse = new AccountListResponse(Status.SUCCESS);
            accountListResponse.setAccounts(accounts);
            handlePagination(offset, limit, accountListResponse);
            return new ResponseEntity<AccountListResponse>(accountListResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error(String.format("Error accessing list of accounts for userId=%s", userId), e);
            AccountListResponse accountListResponse = new AccountListResponse(Status.ERROR);
            accountListResponse.setErrors(List.of(new Error(ErrorCodeEnum.INTERNAL_SERVICE_ERROR)));
            return new ResponseEntity<AccountListResponse>(accountListResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * TODO: Implement pagination properly
     */
    private void handlePagination(int offset, int limit, AccountListResponse response) {
        response.setPagination(new Pagination(offset, limit, response.getAccounts().size()));
    }
}
