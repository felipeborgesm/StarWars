package com.letscode.api.starwars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class TraitorRebelException extends RuntimeException {
  public TraitorRebelException() {
    super("This rebel is actually a traitor");
  }

  public TraitorRebelException(Long id) {
    super(String.format("A rebel with ID %d is a traitor",id));
  }
}
