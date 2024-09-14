package com.vikram.macquarie.bankdemo.repository;

import com.vikram.macquarie.bankdemo.database.entity.AccountEntity;
import com.vikram.macquarie.bankdemo.domain.model.Account;
import com.vikram.macquarie.bankdemo.mapper.AccountMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository for accessing account data.
 *
 * Can provide CRUD (Create, Read, Update, Delete) operations for accounts.
 *
 * Acts as an intermediary between the AccountService and the database.
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    default List<Account> getAccountsByUserId(String userId) throws DatabaseException {
        try {
            return findAccountsByUserId(userId).stream()
                    .map(AccountMapper::getAccount)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseException("Error querying database", e);
        }

    }

    @Query("SELECT a FROM AccountEntity a WHERE a.userEntity.userId = :userId")
    List<AccountEntity> findAccountsByUserId(@Param("userId") String userId);
}
