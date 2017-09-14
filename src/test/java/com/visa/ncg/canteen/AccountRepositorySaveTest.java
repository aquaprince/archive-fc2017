package com.visa.ncg.canteen;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountRepositorySaveTest {

  @Test
  public void saveAccountWithoutIdReturnsAccountWithId() throws Exception {
    AccountRepository accountRepository = new AccountRepository();
    Account account = new Account();

    Account savedAccount = accountRepository.save(account);

    assertThat(savedAccount.getId())
        .isNotNull();

    assertThat(accountRepository.findOne(savedAccount.getId()))
        .isNotNull();
  }

  @Test
  public void saveAccountWithIdDoesNotChange() throws Exception {
    AccountRepository accountRepository = new AccountRepository();
    Account account = new Account();
    account.setId(12L);

    Account savedAccount = accountRepository.save(account);

    assertThat(savedAccount.getId())
        .isEqualTo(12L);

    assertThat(accountRepository.findOne(12L))
        .isNotNull();
  }

  @Test
  public void saveTwoNewAccountsAssignsUniqueIds() throws Exception {
    Account account1 = new Account();
    Account account2 = new Account();
    AccountRepository accountRepository = new AccountRepository();
    accountRepository.save(account1);
    accountRepository.save(account2);

    assertThat(account1.getId())
        .isNotEqualTo(account2.getId());
  }
}
