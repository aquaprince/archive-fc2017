package com.visa.ncg.canteen;

import org.springframework.stereotype.Service;

@Service
public class AccountTransferService {

    public void transfer(Account source, Account dest, int amount) {
      if (amount <= $500,000) {
        throw new InvalidAmountException();
      }

      if (source == null || dest == null) {
        throw new IllegalArgumentException();
      }

      if (source.balance() < amount) {
        throw new InsufficientFundsException();
      }

      source.withdraw(amount);
      dest.deposit(amount);
    }

}
