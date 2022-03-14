package com.letscode.api.starwars.controller;

import com.letscode.api.starwars.exception.CannotTradeMoreThenHaveException;
import com.letscode.api.starwars.exception.CannotTradeWithSelfException;
import com.letscode.api.starwars.exception.RebelNotFoundException;
import com.letscode.api.starwars.exception.TraitorRebelException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {RebelNotFoundException.class})
  protected ResponseEntity<Object> handleNotFound(
      RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {TraitorRebelException.class})
  protected ResponseEntity<Object> handleTraitor(
      RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
  }

  @ExceptionHandler(value = {CannotTradeWithSelfException.class})
  protected ResponseEntity<Object> handleSelfTrade(
      RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {CannotTradeMoreThenHaveException.class})
  protected ResponseEntity<Object> handleNegative(
      RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
  }
}
