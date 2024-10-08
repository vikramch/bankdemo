package com.vikram.macquarie.bankdemo.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vikram.macquarie.bankdemo.common.enums.AccountType;
import com.vikram.macquarie.bankdemo.common.enums.Currency;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class AccountEntity {

    /**
     * Intentionally using String type for Account Number Identifier
     * - A custom Identifier generation mechanism can be designed to scale horizontally, reducing the load on the database.
     * - avoid bottlenecks where database needs to synchronize identifier generation across nodes.
     * - to ensure not to run out of Accounts numbers.
     * - gives us Flexibility in length
     * - trailing 0's,
     * - we will never run out of Account numbers.
     */
    /**
     * Using Account number as Primary key,
     * don't want to depend on Generated values of the database,
     * see reasons above.
     */
    @Id
    @Column(name="account_number", nullable = false)
    private String accountNumber;

    @OneToMany(mappedBy = "accountEntity")
    @JsonIgnore //needed to avoid infinite recursion
    List<TransactionEntity> transactionEntities;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    @Column(name="account_name", nullable = false)
    private String accountName;

    /**
     * EnumType.ORDINAL can be used as well  for performance,
     * using EnumType.STRING for readability
     */
    @Column(name="account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name="balance_date", nullable = false)
    private LocalDate balanceDate;

    /**
     * EnumType.ORDINAL can be used as well for performance,
     * using EnumType.STRING for readability
     */
    @Column(name="currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name="opening_available_balance", nullable = false)
    private BigDecimal openingAvailableBalance;

    public AccountEntity(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
