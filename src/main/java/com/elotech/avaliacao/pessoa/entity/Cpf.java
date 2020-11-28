package com.elotech.avaliacao.pessoa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cpf implements Serializable {

    @Getter
    @Column(name = "cpf")
    private String cpfValue;

    public Cpf(String cpfValue, CpfValidator validator) {
        validator.validate(cpfValue);
        this.cpfValue = cpfValue;
    }
}
