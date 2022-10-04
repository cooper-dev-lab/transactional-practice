package com.cooper.transactionalpractice.account.service.mandatory;

import com.cooper.transactionalpractice.account.domain.AccountHistory;
import com.cooper.transactionalpractice.account.dto.AccountHistoryCreateRequestDto;
import com.cooper.transactionalpractice.account.repository.AccountHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountHistoryMandatoryService {

    private final AccountHistoryRepository accountHistoryRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public void save(AccountHistoryCreateRequestDto accountHistoryCreateRequestDto) {
        AccountHistory accountHistory = accountHistoryCreateRequestDto.fromEntity();
        accountHistoryRepository.save(accountHistory);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void throwExceptionWhenSupports(AccountHistoryCreateRequestDto accountHistoryCreateRequestDto) {
            AccountHistory accountHistory = accountHistoryCreateRequestDto.fromEntity();
            accountHistoryRepository.save(accountHistory);
            throw new RuntimeException();
    }

}
