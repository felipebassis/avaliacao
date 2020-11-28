package com.elotech.avaliacao.common.validation;

import java.util.List;

public interface Validator<T> {

    List<Error> validate(T entity) throws Exception;
}
