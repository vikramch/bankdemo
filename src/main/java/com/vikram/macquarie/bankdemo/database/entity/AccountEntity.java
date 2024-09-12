package com.vikram.macquarie.bankdemo.database.entity;

import com.vikram.macquarie.bankdemo.common.enums.AccountType;
import com.vikram.macquarie.bankdemo.common.enums.Currency;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "accounts")
@Data
public class AccountEntity {

    /**
     * Intentionally using String type to ensure not to run out of Accounts numbers.
     * Using a String gives us Flexibility in length, trailing 0's,
     * and we will never run out of Account numbers.
     */
    /**
     * Using Account number as Primary key,
     * don't want to depend on Generated values of the database.
     */
    @Id
    @Column(name="account_number", nullable = false)
    private String accountNumber;

    @Column(name="account_name", nullable = false)
    private String accountName;

    /**
     * EnumType.ORDINAL can be used as well,
     * using EnumType.STRING for readability
     */
    @Column(name="account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name="balance_date", nullable = false)
    private LocalDate balanceDate;

    /**
     * EnumType.ORDINAL can be used as well,
     * using EnumType.STRING for readability
     */
    @Column(name="currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    /**
     * Max Value of Long is ~9223 trillion.
     * To handle more than that,
     *      Use BigInteger (or)
     *      Use String or byte[] and use custom serialization/de-serialization.
     */
    @Column(name="opening_available_balance", nullable = false)
    private Long openingAvailableBalance;

    public AccountEntity() {
    }
}
