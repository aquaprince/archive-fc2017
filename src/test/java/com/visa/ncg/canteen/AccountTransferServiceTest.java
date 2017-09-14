package com.visa.ncg.canteen;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class AccountTransferServiceTest {

  @Test
  public void transfer10ToEmptyAccountResultsInSecondAccountHaving10() {
    Account source = new Account(10);
    Account dest = new Account(0);
    AccountTransferService accountTransferService = new AccountTransferService();
    accountTransferService.transfer(source, dest, 10);
    assertThat(source.balance())
        .isZero();
    assertThat(dest.balance())
        .isEqualTo(10);
  }

  @Test
  public void transferNegativeThrowsException() {
    AccountTransferService accountTransferService = new AccountTransferService();

    assertThatThrownBy(() -> {
      accountTransferService.transfer(new Account(), new Account(), -10);
    }).isInstanceOf(InvalidAmountException.class);

  }
  @Test
  public void transferZeroThrowsException() {
    AccountTransferService accountTransferService = new AccountTransferService();

    assertThatThrownBy(() -> {
      accountTransferService.transfer(new Account(), new Account(), 0);
    }).isInstanceOf(InvalidAmountException.class);

  }
  @Test
  public void nullSourceThrowsIllegalArgumentException() {
    AccountTransferService accountTransferService = new AccountTransferService();

    assertThatThrownBy(() -> {
      accountTransferService.transfer(null, new Account(), 10);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void nullDestThrowsIllegalArgumentException() {
    AccountTransferService tService = new AccountTransferService();

    assertThatThrownBy(() -> {
      tService.transfer(new Account(), null, 10);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void transferGreaterThanAvailableBalanceThrowsInsufficientFundsException() {
    Account accountWith1Dollar = new Account(1);
    Account dest = new Account();
    AccountTransferService tService = new AccountTransferService();

    assertThatThrownBy(() -> {
      tService.transfer(accountWith1Dollar, dest, 5);
    }).isInstanceOf(InsufficientFundsException.class);
  }

}