package com.visa.ncg.canteen;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class AccountTransferServiceTest {

    @Test
    public void transfer10ResultsIn10Transferred() {
        Account account1 = new Account(10);
        Account account2 = new Account();
        AccountTransferService tService = new AccountTransferService();
        tService.transfer(account1, account2, 10);
        assertThat(account1.balance())
                .isEqualTo(0);
        assertThat(account2.balance())
                .isEqualTo(10);
    }
    @Test
    public void transferNegativeThrowsException() {
        Account account1 = new Account(10);
        Account account2 = new Account();
        int initialBalance = account1.balance();
        AccountTransferService tService = new AccountTransferService();

        assertThatThrownBy(() -> {
            tService.transfer(account1, account2, -10);
        }).isInstanceOf(InvalidAmountException.class);

    }
    @Test
    public void transferZeroThrowsException() {
        Account account1 = new Account(10);
        Account account2 = new Account();
        int initialBalance = account1.balance();
        AccountTransferService tService = new AccountTransferService();

        assertThatThrownBy(() -> {
            tService.transfer(account1, account2, 0);
        }).isInstanceOf(InvalidAmountException.class);

    }
    @Test
    public void nullSourceThrowsIllegalArgumentException() {
        Account account1 = null;
        Account account2 = new Account();
        AccountTransferService tService = new AccountTransferService();

        assertThatThrownBy(() -> {
            tService.transfer(account1, account2, 10);
        }).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void nullDestThrowsIllegalArgumentException() {
        Account account1 = new Account(10);
        Account account2 = null;
        AccountTransferService tService = new AccountTransferService();

        assertThatThrownBy(() -> {
            tService.transfer(account1, account2, 10);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void transferGreaterThanAvailableBalanceThrowsInsufficientBalanceException() {
        Account account1 = new Account(1);
        Account account2 = new Account(10);
        AccountTransferService tService = new AccountTransferService();

        assertThatThrownBy(() -> {
            tService.transfer(account1, account2, 10);
        }).isInstanceOf(InsufficientBalanceException.class);
    }

}
