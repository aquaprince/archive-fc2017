package com.visa.ncg.canteen;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Account ID")
public class AccountIdNotFoundException extends RuntimeException {
  public AccountIdNotFoundException() {
    super();
  }

  public AccountIdNotFoundException(String message) {
    super(message);
  }
}
