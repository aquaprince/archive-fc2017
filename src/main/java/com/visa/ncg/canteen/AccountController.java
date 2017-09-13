package com.visa.ncg.canteen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;

    }

    @GetMapping("/api/accounts/{accountId}")
    public AccountResponse accountInfo(@PathVariable String accountId) {
        Account account = accountRepository.findOne(Long.parseLong(accountId));
        AccountResponse response = new AccountResponse();
        response.setBalance(account.balance());
        response.setId(account.getId());
        return response;
    }
}
