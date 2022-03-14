package com.letscode.api.starwars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CannotTradeWithSelfException extends RuntimeException{
  public CannotTradeWithSelfException(Long id){
    super(String.format("Rebel %d cannot trade with self",id));
  }
}
