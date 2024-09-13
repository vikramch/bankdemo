package com.vikram.macquarie.bankdemo.response;

import com.vikram.macquarie.bankdemo.domain.model.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class AccountListResponse extends BaseResponse {
    private List<Account> accounts;

    public AccountListResponse(Status status) {
        super(status);
        this.accounts = new ArrayList<>();
    }
}
