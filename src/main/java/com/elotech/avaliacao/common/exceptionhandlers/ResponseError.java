package com.elotech.avaliacao.common.exceptionhandlers;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseError {
    private String message;

    private final List<String> failedValidations = new ArrayList<>();

    public void addFailedValidation(String message) {
        this.failedValidations.add(message);
    }
}
