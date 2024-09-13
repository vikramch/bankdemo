package com.vikram.macquarie.bankdemo.response;

import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class AccountTransactionsResponse extends BaseResponse {
    private List<Transaction> transactions;

    public AccountTransactionsResponse(Status status) {
        super(status);
    }
}
