package com.visa.ncg.canteen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountWebController {

  private final AccountRepository accountRepository;
  private final AccountTransferService accountTransferService;

  @Autowired
  public AccountWebController(AccountRepository accountRepository, AccountTransferService accountTransferService) {
    this.accountRepository = accountRepository;
    this.accountTransferService = accountTransferService;
  }

  @GetMapping("/account/{id}")
  public String accountView(@PathVariable("id") String accountId,
                            Model model) {
    Account account = accountRepository.findOne(Long.valueOf(accountId));
    if (account == null) {
      throw new AccountIdNotFoundException(accountId);
    }
    AccountView accountView = AccountView.from(account);
    model.addAttribute("account", accountView);
    return "account-view";
  }

  @GetMapping("/create-account")
  public String createAccountForm(Model model,
                                  CreateForm createForm) {
    model.addAttribute("createForm", createForm);
    return "create-account";
  }

  @PostMapping("/create-account")
  public String createAccount(CreateForm createForm) {
    Account account = new Account(createForm.getInitialDeposit());
    account.setName(createForm.getAccountName());
    Account savedAccount = accountRepository.save(account);
    return "redirect:/account/" +savedAccount.getId();
  }

  @ModelAttribute("createForm")
  public CreateForm createForm() {
    CreateForm createForm = new CreateForm();
    createForm.setAccountName("");
    createForm.setInitialDeposit(10);
    return createForm;
  }

  @GetMapping("/transfer")
  public String showTransferForm(Model model) {
    model.addAttribute("transfer", new TransferForm());
    return "transfer";
  }

  @PostMapping("/transfer")
  public String processTransfer(TransferForm transferForm) {
    long sourceAccountId = transferForm.getSourceAccountId();
    Account source = accountRepository.findOne(sourceAccountId);
    Account dest = accountRepository.findOne(transferForm.getDestinationAccountId());

    accountTransferService.transfer(source, dest, transferForm.getAmount());

    return "redirect:/account/" + sourceAccountId;
  }
}
