package com.visa.ncg.canteen;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;



@Controller
public class AccountWebController {
    private AccountRepository accountRepository;
    private CurrencyService currencyService;


    public AccountWebController(AccountRepository accountRepository, CurrencyService currencyService) {
        this.currencyService = currencyService;
        this.accountRepository = accountRepository;
    }


    @GetMapping("/account/{id}")
    public String accountView(@PathVariable("id") String id, Model model) {
        AccountResponse response = new AccountResponse();
        Account account = accountRepository.findOne(Long.valueOf(id));
        if (account == null) {
            //throw custom exception
        }
        response.setId(account.getId());
        response.setBalance(account.balance());
        response.setName(account.getName());
        model.addAttribute("account", response);
        int convertedCurrency = currencyService.convertToGbp(account.balance());
        model.addAttribute("gbpBalance", convertedCurrency);
        return "account-view";
    }

    @GetMapping("/account")
    public String allAccountsView(Model model) {
        Iterable<Account> accounts = accountRepository.findAll();
//        if (accounts.size() < 1) {
//            //throw custom exception
//        }
        List<AccountResponse> toReturn = new ArrayList<>();
        for (Account account : accounts) {
            AccountResponse response = new AccountResponse();
            response.setName(account.getName());
            response.setBalance(account.balance());
            response.setId(account.getId());
            toReturn.add(response);
        }
        model.addAttribute("accounts", toReturn);

        return "all-accounts";
    }

    @GetMapping("/create-account")
    public String createAccountView(Model model) {
        CreateForm createdForm = new CreateForm();
        createdForm.setAccountName("");
        createdForm.setInitialDeposit(10);
        model.addAttribute(createdForm);
        return "create-account";
    }

    @PostMapping("/create-account")
    public String createAccount(@ModelAttribute("accountName") String name) {
        Account toAdd = new Account();
        toAdd.setName(name);
        Account added = accountRepository.save(toAdd);

        return "redirect:/account/" +  added.getId();
    }



}
