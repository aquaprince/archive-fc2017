package com.visa.ncg.canteen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping("/api/accounts/{accountId}")
    public Account accountInfo(@PathVariable String accountId) {
        Account account = new Account(10);
        account.setId(Long.parseLong(accountId));
        return account;
    }
}
