package com.elotech.avaliacao.pessoa.crud;

import com.elotech.avaliacao.common.validation.Error;
import com.elotech.avaliacao.common.validation.ValidationException;
import com.elotech.avaliacao.common.validation.Validator;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaValidatorServiceTest {

    private PessoaValidatorService pessoaValidatorService;

    @Mock
    private Validator<Pessoa> aValidator;

    @Mock
    private Validator<Pessoa> anotherValidator;

    @BeforeEach
    void setUp() {
        pessoaValidatorService = new PessoaValidatorService(Arrays.asList(aValidator, anotherValidator));
    }

    @Test
    void shouldThrowExceptionWhenValidationHasErrors() throws Exception {
        Pessoa pessoa = new Pessoa();
        List<Error> errors = Collections.singletonList(Error.withMessage("Test error"));
        when(aValidator.validate(pessoa)).thenReturn(errors);
        when(anotherValidator.validate(pessoa)).thenReturn(new ArrayList<>());

        ValidationException validationException = assertThrows(ValidationException.class, () -> pessoaValidatorService.validate(pessoa));
        assertEquals("Falha ao validar a entidade pessoa.", validationException.getMessage());
        assertEquals(Pessoa.class.getSimpleName(), validationException.getFailedValidationType().getSimpleName());
        assertEquals(errors, validationException.getCauses());
    }

    @Test
    void shouldThrowExceptionWhenValidationThrowsExceptions() throws Exception {
        Pessoa pessoa = new Pessoa();
        List<Error> errors = Collections.singletonList(Error.withMessage("Test error"));
        when(aValidator.validate(pessoa)).thenReturn(errors);
        when(anotherValidator.validate(pessoa)).thenThrow(new RuntimeException("Test error by exception."));

        ValidationException validationException = assertThrows(ValidationException.class, () -> pessoaValidatorService.validate(pessoa));
        assertEquals("Falha ao validar a entidade pessoa.", validationException.getMessage());
        assertEquals(Pessoa.class.getSimpleName(), validationException.getFailedValidationType().getSimpleName());
        List<Error> expectedErrors = new ArrayList<>(errors);
        expectedErrors.add(Error.withMessage("Test error by exception."));
        assertEquals(expectedErrors, validationException.getCauses());
    }

    @Test
    void shouldNotThrowExceptionWhenValidationHasNoErrors() throws Exception {
        Pessoa pessoa = new Pessoa();
        when(aValidator.validate(pessoa)).thenReturn(new ArrayList<>());
        when(anotherValidator.validate(pessoa)).thenReturn(new ArrayList<>());

        assertDoesNotThrow(() -> pessoaValidatorService.validate(pessoa));
    }


}