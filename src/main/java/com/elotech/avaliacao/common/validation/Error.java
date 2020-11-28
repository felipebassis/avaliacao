package com.elotech.avaliacao.common.validation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Error {

    @Getter
    private final String message;

    private Error(String message) {
        this.message = message;
    }

    public static Error withMessage(String message) {
        return new Error(message);
    }
}
