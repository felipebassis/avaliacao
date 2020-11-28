package com.elotech.avaliacao.pessoa.entity.validation;

import com.elotech.avaliacao.common.validation.Error;
import com.elotech.avaliacao.common.validation.Validator;
import com.elotech.avaliacao.pessoa.entity.Contato;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Order
@Component
public class PessoaNonNullFieldsValidator implements Validator<Pessoa> {

    @Override
    public List<Error> validate(Pessoa entity) {
        List<Error> errorMessages = new ArrayList<>();
        if (!StringUtils.hasText(entity.getNome())) {
            errorMessages.add(Error.withMessage("Nome da pessoa não pode estar nulo/vazio."));
        }
        if (Objects.isNull(entity.getDataDeNascimento())) {
            errorMessages.add(Error.withMessage("Data de nascimento não pode estar nulo."));
        }
        if (!StringUtils.hasText(entity.getCpf())) {
            errorMessages.add(Error.withMessage("Cpf não pode estar nulo."));
        }
        List<Contato> contatos = entity.getContatos();
        contatos.forEach(contato -> {
            if (!StringUtils.hasText(contato.getNome())) {
                errorMessages.add(Error.withMessage("Nome do contato de id " + contato.getId() + " não pode estar nulo/vazio."));
            }
            if (!StringUtils.hasText(contato.getTelefone())) {
                errorMessages.add(Error.withMessage("Telefone do contato de id " + contato.getId() + " não pode estar nulo/vazio."));
            }
            if (!StringUtils.hasText(contato.getEmail())) {
                errorMessages.add(Error.withMessage("Email do contato de id " + contato.getId() + " não pode estar nulo/vazio."));
            }
        });
        return Collections.unmodifiableList(errorMessages);
    }
}
