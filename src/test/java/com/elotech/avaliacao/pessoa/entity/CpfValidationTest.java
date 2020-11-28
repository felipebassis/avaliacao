package com.elotech.avaliacao.pessoa.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfValidationTest {

    @Test
    void shouldNotValidateCpfWithIncorrectLength() {
        CpfValidationException cpfValidationException = assertThrows(CpfValidationException.class, () -> CpfValidation.CURRENT.validate("111"));
        assertEquals("CPF não possui 11 caracteres.", cpfValidationException.getMessage());

    }

    @Test
    void shouldNotValidateIncorrectCpf() {
        CpfValidationException cpfValidationException = assertThrows(CpfValidationException.class, () -> CpfValidation.CURRENT.validate("11111121111"));
        assertEquals("Falha na verificação do primeiro dígito.", cpfValidationException.getMessage());
    }

    @Test
    void shouldNotValidateKnowInvalidCpf() {
        CpfValidationException cpfValidationException = assertThrows(CpfValidationException.class, () -> CpfValidation.CURRENT.validate("11111111111"));
        assertEquals("CPF inválido.", cpfValidationException.getMessage());
    }

    @Test
    void shouldValidateValidCpf() {
        assertDoesNotThrow(() -> CpfValidation.CURRENT.validate("11174896981"));
    }
}