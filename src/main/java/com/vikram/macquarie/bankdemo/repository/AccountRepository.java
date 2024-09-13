package com.vikram.macquarie.bankdemo.repository;

import com.vikram.macquarie.bankdemo.database.entity.AccountEntity;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    default List<Account> findAllAccounts() {
        return findAll().stream()
                .map(accountEntity -> {
                    Account account = new Account(accountEntity.getAccountNumber(),
                            accountEntity.getAccountName(),
                            accountEntity.getAccountType(),
                            accountEntity.getBalanceDate(),
                            accountEntity.getCurrency(),
                            accountEntity.getOpeningAvailableBalance());

                    return account;
                })
                .collect(Collectors.toList());
    }
}
