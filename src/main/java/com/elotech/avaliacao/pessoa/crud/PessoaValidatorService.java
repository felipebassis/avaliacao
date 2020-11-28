package com.elotech.avaliacao.pessoa.crud;

import com.elotech.avaliacao.common.validation.Error;
import com.elotech.avaliacao.common.validation.ValidationException;
import com.elotech.avaliacao.common.validation.Validator;
import com.elotech.avaliacao.common.validation.ValidatorService;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class PessoaValidatorService implements ValidatorService<Pessoa> {

    private final List<Validator<Pessoa>> validators;

    public PessoaValidatorService(List<Validator<Pessoa>> validators) {
        this.validators = validators;
    }

    @Override
    public void validate(Pessoa objectToValidate) throws ValidationException {
        List<Error> errors = new LinkedList<>();
        for (Validator<Pessoa> validator : validators) {
            try {
                List<Error> validate = validator.validate(objectToValidate);
                errors.addAll(validate);
            } catch (Exception e) {
                log.error("Erro ao validar uma pessoa de Id {}.", objectToValidate.getId(), e);
                errors.add(Error.withMessage(e.getMessage()));
            }
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("Falha ao validar a entidade pessoa.", objectToValidate.getClass(), errors);
        }
    }
}
