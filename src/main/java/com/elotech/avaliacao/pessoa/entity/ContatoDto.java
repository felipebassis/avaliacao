package com.elotech.avaliacao.pessoa.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class ContatoDto {

    private UUID id;

    private String nome;

    private String telefone;

    private String email;
}
