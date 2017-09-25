package com.visa.ncg.canteen;

public class TransferForm {
  private long sourceAccountId;
  private long destinationAccountId;
  private int amount;

  public long getSourceAccountId() {
    return sourceAccountId;
  }

  public void setSourceAccountId(long sourceAccountId) {
    this.sourceAccountId = sourceAccountId;
  }

  public long getDestinationAccountId() {
    return destinationAccountId;
  }

  public void setDestinationAccountId(long destinationAccountId) {
    this.destinationAccountId = destinationAccountId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
