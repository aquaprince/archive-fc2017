package com.visa.ncg.canteen;

public class InvalidAmountException extends RuntimeException {
  public InvalidAmountException() {
    super();
  }

  public InvalidAmountException(String message) {
    super(message);
  }
}
