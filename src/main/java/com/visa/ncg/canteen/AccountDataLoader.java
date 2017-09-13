package com.visa.ncg.canteen;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountDataLoader implements ApplicationRunner {
    private AccountRepository accountRepository;

    public AccountDataLoader(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.deposit(10);
        account.setId(123L);
        accountRepository.save(account);
        Account account1 = new Account(10);
        account1.setId(1L);
        accountRepository.save(account1);
    }
}