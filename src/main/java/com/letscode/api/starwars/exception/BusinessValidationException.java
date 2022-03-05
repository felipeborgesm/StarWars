package com.letscode.api.starwars.exception;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessValidationException extends RuntimeException {
    private final List<String> validationErrors;

    public BusinessValidationException(List<String> validationErrors) {
        super(validationErrors.stream().collect(Collectors.joining(";")));
        this.validationErrors = validationErrors;
    }

}
