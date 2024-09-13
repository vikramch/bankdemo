package com.vikram.macquarie.bankdemo.database.entity;

import com.vikram.macquarie.bankdemo.common.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
public class TransactionEntity {

    /**
     * Intentionally using String type for Transaction Identifier
     * - A custom Identifier generation mechanism can be designed to scale horizontally, reducing the load on the database.
     * - avoid bottlenecks where database needs to synchronize identifier generation across nodes.
     * - to ensure not to run out of Transaction Ids.
     * - gives us Flexibility in length
     * - we will never run out of Transaction Ids.
     */
    @Id
    @Column(name="tx_id", nullable = false)
    private String transactionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_number")
    private AccountEntity accountEntity;

    @Column(name="value_date", nullable = false)
    private LocalDate valueDate;

    @Column(name="amount", nullable = false)
    private Long amount;

    /**
     * EnumType.ORDINAL can be used as well for performance,
     * using EnumType.STRING for readability
     */
    @Column(name="transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name="transaction_narrative", nullable = false)
    private String transactionNarrative;



}
