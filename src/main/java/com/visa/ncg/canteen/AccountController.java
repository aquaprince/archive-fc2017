package com.visa.ncg.canteen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController
@Controller
@ResponseBody
public class AccountController {

  AccountRepository accountRepository;

  public AccountController(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @GetMapping("/api/accounts/{accountId}")
  public AccountResponse accountInfo(@PathVariable("accountId") String accountId) {
    Account account = accountRepository.findOne(Long.valueOf(accountId));
    if (account == null) {
      throw new AccountIdNotFoundException(accountId);
    }

    AccountResponse accountResponse = new AccountResponse();
    accountResponse.setId(account.getId());
    accountResponse.setBalance(account.balance());
    return accountResponse;
  }

}
