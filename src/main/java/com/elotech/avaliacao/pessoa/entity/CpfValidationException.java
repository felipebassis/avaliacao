package com.elotech.avaliacao.pessoa.entity;

public class CpfValidationException extends RuntimeException {
    private static final long serialVersionUID = -3975989754145479592L;

    public CpfValidationException(String message) {
        super(message);
    }
}
