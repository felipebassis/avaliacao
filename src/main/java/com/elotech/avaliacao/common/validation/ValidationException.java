package com.elotech.avaliacao.common.validation;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private final Class<?> failedValidationType;
    private final List<Error> causes;

    public ValidationException(String message, Class<?> failedValidationType, List<Error> causes) {
        super(message);
        this.failedValidationType = failedValidationType;
        this.causes = causes;
    }
}
