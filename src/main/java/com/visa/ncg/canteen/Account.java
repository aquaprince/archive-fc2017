package com.visa.ncg.canteen;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
  private int balance;

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id = null;
  private String name;

  public Account(int initialAmount) {
    balance = initialAmount;
  }

  public Account() {
    balance = 0;
  }

  public int balance() {
    return balance;
  }

  public void deposit(int amount) {
    if (amount <= 0) {
      throw new DepositInvalidAmountException();
    }
    balance += amount;
  }

  public void withdraw(int amount) {
    if (amount > balance) {
      throw new InsufficientBalanceException();
    }
    if (amount <= 0) {
      throw new WithdrawInvalidAmountException();
    }
    balance -= amount;
  }
  public void setId(long id) {
    this.id = new Long(id);
  }

  public Long getId(){
    if (id == null) {
      return null;
    } else {
      return id;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
