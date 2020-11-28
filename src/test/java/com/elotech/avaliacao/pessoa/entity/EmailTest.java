package com.elotech.avaliacao.pessoa.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @Test
    void shouldThrowExceptionWhenEmailIsNotValid() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Email("test$@test."));
        assertEquals("Email invalido.", illegalArgumentException.getMessage());
    }

    @Test
    void shouldNotThrowExceptionWhenEmailIsValid() {
        Email email = new Email("test@test.com");
        assertEquals("test@test.com", email.getEmailValue());
    }
}