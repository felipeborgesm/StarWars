package com.letscode.api.starwars.exception;

import java.util.List;

import lombok.Getter;

public class BusinessValidationException extends RuntimeException {

  @Getter
  private final List<String> validationErrors;

  public BusinessValidationException(List<String> validationErrors) {
    super(String.join(";", validationErrors));
    this.validationErrors = validationErrors;
  }

  public BusinessValidationException(String validationErrorMessage) {
    this(List.of(validationErrorMessage));
  }
}
