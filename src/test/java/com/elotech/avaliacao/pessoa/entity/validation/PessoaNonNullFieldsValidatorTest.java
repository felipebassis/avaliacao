package com.elotech.avaliacao.pessoa.entity.validation;

import com.elotech.avaliacao.common.validation.Error;
import com.elotech.avaliacao.pessoa.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PessoaNonNullFieldsValidatorTest {

    @Test
    void shouldReturnErrorWhenHasAnyNullOrEmptyFields() {
        Pessoa pessoa = new Pessoa();
        Contato contato = new Contato();
        pessoa.setContatos(Collections.singletonList(contato));
        PessoaNonNullFieldsValidator pessoaNonNullFieldsValidator = new PessoaNonNullFieldsValidator();
        List<Error> errors = pessoaNonNullFieldsValidator.validate(pessoa);
        assertFalse(errors.isEmpty());
        assertEquals(6, errors.size());
        assertEquals("Nome da pessoa não pode estar nulo/vazio.", errors.get(0).getMessage());
        assertEquals("Data de nascimento não pode estar nulo.", errors.get(1).getMessage());
        assertEquals("Cpf não pode estar nulo.", errors.get(2).getMessage());
        assertEquals("Nome do contato de id " + contato.getId() + " não pode estar nulo/vazio.", errors.get(3).getMessage());
        assertEquals("Telefone do contato de id " + contato.getId() + " não pode estar nulo/vazio.", errors.get(4).getMessage());
        assertEquals("Email do contato de id " + contato.getId() + " não pode estar nulo/vazio.", errors.get(5).getMessage());
    }

    @Test
    void shouldNotReturnErrorWhenHasNotAnyNullOrEmptyValues() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Test");
        pessoa.setCpf(new Cpf("11174896981", CpfValidation.CURRENT));
        pessoa.setDataDeNascimento(LocalDate.now());
        Contato contato = new Contato();
        contato.setNome("Test");
        contato.setTelefone("44998770297");
        contato.setEmail(new Email("test@test.com"));
        pessoa.setContatos(Collections.singletonList(contato));
        PessoaNonNullFieldsValidator pessoaNonNullFieldsValidator = new PessoaNonNullFieldsValidator();
        List<Error> errors = pessoaNonNullFieldsValidator.validate(pessoa);
        assertTrue(errors.isEmpty());
    }
}