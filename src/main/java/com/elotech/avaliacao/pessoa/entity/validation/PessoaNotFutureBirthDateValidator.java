package com.elotech.avaliacao.pessoa.entity.validation;

import com.elotech.avaliacao.common.validation.Error;
import com.elotech.avaliacao.common.validation.Validator;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PessoaNotFutureBirthDateValidator implements Validator<Pessoa> {

    @Override
    public List<Error> validate(Pessoa entity) {
        List<Error> errors = new ArrayList<>();
        if (Optional.ofNullable(entity.getDataDeNascimento())
                .map(birthDate -> birthDate.isAfter(LocalDate.now()))
                .orElse(false)) {
            errors.add(Error.withMessage("Data de nascimento não pode ser maior que data atual."));
        }
        return errors;
    }
}
