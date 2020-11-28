package com.elotech.avaliacao.pessoa.entity.validation;

import com.elotech.avaliacao.common.validation.Error;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PessoaNotFutureBirthDateValidatorTest {

    @Test
    void shouldReturnErrorWhenBirthDateIsAfterCurrentDate() {
        Pessoa pessoa = new Pessoa();
        pessoa.setDataDeNascimento(LocalDate.now().plusDays(1));
        PessoaNotFutureBirthDateValidator pessoaNotFutureBirthDateValidator = new PessoaNotFutureBirthDateValidator();
        List<Error> errors = pessoaNotFutureBirthDateValidator.validate(pessoa);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("Data de nascimento não pode ser maior que data atual.", errors.get(0).getMessage());
    }

    @Test
    void shouldNotReturnErrorWhenBirthDateIsBeforeCurrentDate() {
        Pessoa pessoa = new Pessoa();
        pessoa.setDataDeNascimento(LocalDate.now().minusDays(1));
        PessoaNotFutureBirthDateValidator pessoaNotFutureBirthDateValidator = new PessoaNotFutureBirthDateValidator();
        List<Error> errors = pessoaNotFutureBirthDateValidator.validate(pessoa);
        assertTrue(errors.isEmpty());
    }
}