package com.visa.ncg.canteen;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccountWithdrawTest {

  @Test
  public void withdrawMoneyFromAccountWithInsufficientBalanceThrowsException() throws Exception {
    Account account = new Account(2);

    assertThatThrownBy(() -> { account.withdraw(12); })
        .isInstanceOf(InsufficientBalanceException.class);
  }

  @Test
  public void withdrawAllOfAccountBalanceResultsInZeroBalance() throws Exception {
    Account account = new Account(9);

    account.withdraw(9);

    assertThat(account.balance())
        .isZero();
  }

  @Test
  public void withdraw3DollarsFromAccountHaving7DollarsResultsIn4DollarBalance() throws Exception {
    Account account = new Account(7);

    account.withdraw(3);

    assertThat(account.balance())
        .isEqualTo(4);
  }

  @Test
  public void withdrawZeroDollarsResultsInInvalidAmountException() throws Exception {
    Account account = new Account(10);

    assertThatThrownBy(() -> account.withdraw(0))
        .isInstanceOf(InvalidAmountException.class);
  }

  @Test
  public void withdrawNegativeAmountResultsInInvalidAmountException() throws Exception {
    Account account = new Account(10);

    assertThatThrownBy(() -> account.withdraw(-1))
        .isInstanceOf(InvalidAmountException.class);
  }

}
