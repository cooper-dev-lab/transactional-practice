package com.cooper.transactionalpractice.account.service;

import com.cooper.transactionalpractice.account.domain.Account;
import com.cooper.transactionalpractice.account.domain.AccountHistory;
import com.cooper.transactionalpractice.account.repository.AccountHistoryRepository;
import com.cooper.transactionalpractice.account.repository.AccountRepository;
import com.cooper.transactionalpractice.account.service.required.AccountRequiredService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AccountRequiredServiceTest {

    @Autowired
    private AccountRequiredService accountRequiredService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHistoryRepository accountHistoryRepository;

    @BeforeEach
    void init() {
        accountRepository.deleteAll();
        accountHistoryRepository.deleteAll();
    }

    @DisplayName("required 일 경우, 하나의 트랜잭션으로 묶인다")
    @Test
    void saveAccountWhenHistoryRequired() {
        accountRepository.save(Account.create("123", "123", 123));
        accountRepository.save(Account.create("234", "234", 234));

        // if transaction commit,  Account 1 + AccountHistory 1
        accountRequiredService.saveAccountWhenHistoryRequired(Account.create("345", "345", 345));

        List<Account> allAccounts = accountRepository.findAll();
        List<AccountHistory> allAccountHistories = accountHistoryRepository.findAll();

        assertThat(allAccounts).hasSize(3);
        assertThat(allAccountHistories).hasSize(1);
    }

    @DisplayName("required 일 경우, 자식 트랜잭션에서 예외가 발생하면 부모 트랜잭션까지 롤백한다")
    @Test
    void throwExceptionWhenRequired() {
        accountRepository.save(Account.create("123", "123", 123));
        accountRepository.save(Account.create("234", "234", 234));

        // if transaction commit,  Account 1 + AccountHistory 1
        Assertions.assertThatThrownBy(() -> accountRequiredService.saveAccountWhenHistoryThrowException(Account.create("345", "345", 345)))
                .isInstanceOf(UnexpectedRollbackException.class);

        List<Account> allAccounts = accountRepository.findAll();
        List<AccountHistory> allAccountHistories = accountHistoryRepository.findAll();

        assertThat(allAccounts).hasSize(2);
        assertThat(allAccountHistories).hasSize(0);
    }

}