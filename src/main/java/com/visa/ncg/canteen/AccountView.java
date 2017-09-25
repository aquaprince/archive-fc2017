package com.visa.ncg.canteen;

public class AccountView {
  private long id;
  private String name;
  private int balance;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  public static AccountView from(Account account) {
    AccountView accountView = new AccountView();
    accountView.setBalance(account.balance());
    accountView.setId(account.getId());
    accountView.setName(account.getName());
    return accountView;
  }
}
