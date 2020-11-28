package com.elotech.avaliacao.common.validation;

public interface ValidatorService<T> {

    void validate(T objectToValidate) throws ValidationException;
}
