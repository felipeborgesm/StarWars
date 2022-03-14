package com.letscode.api.starwars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RebelNotFoundException extends RuntimeException{
  public RebelNotFoundException() {
    super("Cannot find the requested rebel");
  }

  public RebelNotFoundException(Long id) {
    super(String.format("A rebel with ID %d wasn't found in our records",id));
  }
}
