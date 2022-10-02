package com.cooper.transactionalpractice.account.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class AccountHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", updatable = false)
    private String accountNumber;

    @Column(name = "current_balance")
    private long currentBalance;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    private AccountHistory(String accountNumber, long currentBalance, LocalDateTime createdTime) {
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.createdTime = createdTime;
    }

    public static AccountHistory create (String accountNumber, long currentBalance, LocalDateTime createdTime) {
        return new AccountHistory(accountNumber, currentBalance, createdTime);
    }

}
