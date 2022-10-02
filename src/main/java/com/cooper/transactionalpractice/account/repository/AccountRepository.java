package com.cooper.transactionalpractice.account.repository;

import com.cooper.transactionalpractice.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
