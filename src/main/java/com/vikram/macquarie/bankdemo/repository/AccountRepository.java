package com.vikram.macquarie.bankdemo.repository;

import com.vikram.macquarie.bankdemo.database.entities.AccountEntity;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    default List<Account> findAllAccounts() {
        return findAll().stream()
                .map(accountEntity -> new Account(accountEntity.getAccountName()))
                .collect(Collectors.toList());
    }
}
