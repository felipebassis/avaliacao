package com.elotech.avaliacao.pessoa.entity.validation;

import com.elotech.avaliacao.common.validation.Error;
import com.elotech.avaliacao.common.validation.Validator;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PessoaAtLeastOneContactValidator implements Validator<Pessoa> {
    @Override
    public List<Error> validate(Pessoa entity) {
        List<Error> errors = new ArrayList<>();
        if (entity.getContatos().isEmpty()) {
            errors.add(Error.withMessage("A lista de contatos está vazia."));
        }
        return errors;
    }
}
