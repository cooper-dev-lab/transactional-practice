package com.cooper.transactionalpractice.account.service;

import com.cooper.transactionalpractice.account.repository.AccountHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountHistoryService {

    private final AccountHistoryRepository accountHistoryRepository;

}
