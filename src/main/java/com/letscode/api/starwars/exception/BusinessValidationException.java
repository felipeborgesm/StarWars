package com.letscode.api.starwars.exception;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessValidationException extends RuntimeException {
    @Getter
    private final List<String> validationErrors;

    public BusinessValidationException(List<String> validationErrors) {
        super(validationErrors.stream().collect(Collectors.joining(";")));
        this.validationErrors = validationErrors;
    }

    public BusinessValidationException(String validationErrorMessage){
        super(validationErrorMessage);
        this.validationErrors = List.of(validationErrorMessage);
    }
}
