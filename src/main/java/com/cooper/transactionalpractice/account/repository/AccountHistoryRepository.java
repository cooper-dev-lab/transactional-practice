package com.cooper.transactionalpractice.account.repository;

import com.cooper.transactionalpractice.account.domain.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {
}
