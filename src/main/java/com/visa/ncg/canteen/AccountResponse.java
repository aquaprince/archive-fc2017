package com.visa.ncg.canteen;

public class AccountResponse {
    private int balance;
    private Long id;
    public void setId(Long id) {
        this.id = id;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public int getBalance() {
        return balance;
    }
    public Long getId() {
        return id;
    }
}