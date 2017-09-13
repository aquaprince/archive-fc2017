package com.visa.ncg.canteen;

import org.springframework.stereotype.Service;

@Service
public class AccountTransferService {
    public void transfer(Account source, Account dest, int amount) {
        if (amount <= 0) throw new InvalidAmountException();
        else if (source == null || dest == null) throw new IllegalArgumentException();
        else if (source.balance() < amount) throw new InsufficientFundsException();
        else {
            source.withdraw(amount);
            dest.deposit(amount);
        }
    }
}
