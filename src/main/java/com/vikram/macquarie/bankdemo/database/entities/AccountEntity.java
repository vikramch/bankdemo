package com.vikram.macquarie.bankdemo.database.entities;

import jakarta.persistence.*;

@Entity(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="account_name", nullable = false)
    private String accountName;

    public AccountEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }
}
