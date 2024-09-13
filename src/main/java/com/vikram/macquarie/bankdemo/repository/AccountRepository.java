package com.vikram.macquarie.bankdemo.repository;

import com.vikram.macquarie.bankdemo.database.entity.AccountEntity;
import com.vikram.macquarie.bankdemo.database.entity.TransactionEntity;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    default List<Account> getAccountsByUserId(String userId) {
        return findAccountsByUserId(userId).stream()
                .map(accountEntity -> {
                    return new Account(accountEntity.getAccountNumber(),
                            accountEntity.getAccountName(),
                            accountEntity.getAccountType(),
                            accountEntity.getBalanceDate(),
                            accountEntity.getCurrency(),
                            accountEntity.getOpeningAvailableBalance());
                })
                .collect(Collectors.toList());
    }

    @Query("SELECT a FROM AccountEntity a WHERE a.userEntity.userId = :userId")
    List<AccountEntity> findAccountsByUserId(@Param("userId") String userId);
}
