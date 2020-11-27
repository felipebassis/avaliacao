package com.elotech.avaliacao.pessoa.entity;

public interface CpfValidator {

    void validate(String cpfToBeValidated) throws CpfValidationException;
}
