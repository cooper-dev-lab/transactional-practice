package com.cooper.transactionalpractice.account.service.required_new;

import com.cooper.transactionalpractice.account.domain.Account;
import com.cooper.transactionalpractice.account.dto.AccountHistoryCreateRequestDto;
import com.cooper.transactionalpractice.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountRequiredNewService {

    private final AccountRepository accountRepository;
    private final AccountHistoryRequiredNewService accountHistoryRequiredNewService;

    @Transactional
    public void saveAccountWhenHistoryRequiredNew(Account account) {
        Account savedAccount = accountRepository.save(account);

        AccountHistoryCreateRequestDto accountHistoryCreateRequestDto = AccountHistoryCreateRequestDto.create(
                savedAccount.getAccountNumber(),
                savedAccount.getBalance()
        );

        accountHistoryRequiredNewService.save(accountHistoryCreateRequestDto);
    }

    @Transactional
    public void saveAccountWhenHistoryThrowException(Account account) {
        Account savedAccount = accountRepository.save(account);

        AccountHistoryCreateRequestDto accountHistoryCreateRequestDto = AccountHistoryCreateRequestDto.create(
                savedAccount.getAccountNumber(),
                savedAccount.getBalance()
        );

        try {
            accountHistoryRequiredNewService.throwExceptionWhenRequiredNew(accountHistoryCreateRequestDto);
        } catch (Exception exception) {
            log.debug("exception : {}", exception.getMessage());
        }
    }

}
