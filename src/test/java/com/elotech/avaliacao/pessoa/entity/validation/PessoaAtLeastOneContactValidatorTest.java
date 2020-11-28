package com.elotech.avaliacao.pessoa.entity.validation;

import com.elotech.avaliacao.common.validation.Error;
import com.elotech.avaliacao.pessoa.entity.Contato;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PessoaAtLeastOneContactValidatorTest {

    @Test
    void shouldReturnErrorWhenContactListIsEmpty() {
        Pessoa pessoa = new Pessoa();
        PessoaAtLeastOneContactValidator pessoaAtLeastOneContactValidator = new PessoaAtLeastOneContactValidator();
        List<Error> errors = pessoaAtLeastOneContactValidator.validate(pessoa);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("A lista de contatos está vazia.", errors.get(0).getMessage());
    }

    @Test
    void shouldNotReturnErrorWhenContactListIsEmpty() {
        Pessoa pessoa = new Pessoa();
        pessoa.setContatos(Collections.singletonList(new Contato()));
        PessoaAtLeastOneContactValidator pessoaAtLeastOneContactValidator = new PessoaAtLeastOneContactValidator();
        List<Error> errors = pessoaAtLeastOneContactValidator.validate(pessoa);
        assertTrue(errors.isEmpty());
    }
}