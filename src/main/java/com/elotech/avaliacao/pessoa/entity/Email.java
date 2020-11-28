package com.elotech.avaliacao.pessoa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email implements Serializable {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");

    @Getter
    @Column(name = "email")
    private String emailValue;

    public Email(String emailValue) {
        Matcher matcher = EMAIL_REGEX.matcher(emailValue);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Email invalido.");
        }
        this.emailValue = emailValue;
    }
}
