package com.elotech.avaliacao.pessoa.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cpf {

    @Getter
    @Column(name = "cpf")
    private String cpfValue;

    public Cpf(String cpfValue, CpfValidator validator) {
        validator.validate(cpfValue);
        this.cpfValue = cpfValue;
    }
}
