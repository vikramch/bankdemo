package com.vikram.macquarie.bankdemo.responses;

import com.vikram.macquarie.bankdemo.domain.model.Account;
import lombok.Data;

import java.util.List;

@Data
public class AccountListResponse {
    private List<Account> accounts;
    private int totalCount;
    private int pageSize;
    private int pageNumber;
}
