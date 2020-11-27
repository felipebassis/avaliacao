package com.elotech.avaliacao.pessoa.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.elotech.avaliacao.common.ApplicationEntity;

import lombok.Getter;

@Entity
@Table(name = "contato")
public class Contato implements ApplicationEntity<UUID> {

    private static final long serialVersionUID = -1503249860861855058L;

    @Id
    @Getter
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;
}
