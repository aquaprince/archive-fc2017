package com.visa.ncg.canteen;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class AccountWebController {
    AccountRepository accountRepository;

    public AccountWebController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;

    }

    @GetMapping("/account/{id}")
    public String accountView(@PathVariable("id") String id, Model model) {
        AccountResponse response = new AccountResponse();
        Account account = accountRepository.findOne(Long.valueOf(id));
        response.setId(account.getId());
        response.setBalance(account.balance());
        response.setName(account.getName());
        model.addAttribute("account", response);

        return "account-view";
    }
}
