package com.elotech.avaliacao.pessoa.entity;

import com.elotech.avaliacao.common.crud.ApplicationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "contato")
public class Contato implements ApplicationEntity<UUID> {

    private static final long serialVersionUID = -1503249860861855058L;

    @Id
    @Getter
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Setter
    @Getter
    @Column(name = "nome")
    private String nome;

    @Setter
    @Getter
    @Column(name = "telefone")
    private String telefone;

    @Setter
    @Embedded
    private Email email;

    public String getEmail() {
        return Optional.ofNullable(this.email)
                .map(Email::getEmailValue)
                .orElse(null);
    }
}
