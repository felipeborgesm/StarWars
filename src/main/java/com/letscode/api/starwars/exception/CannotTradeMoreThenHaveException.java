package com.letscode.api.starwars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class CannotTradeMoreThenHaveException  extends RuntimeException{
  public CannotTradeMoreThenHaveException() {
    super("You cannot trade more items than you have");
  }

  public CannotTradeMoreThenHaveException(Long id) {
    super(String.format("A rebel with ID %d cannot trade more items than it have",id));
  }
}
