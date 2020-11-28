package com.elotech.avaliacao.pessoa.entity;

import com.elotech.avaliacao.common.crud.CrudDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class PessoaDto implements CrudDto<UUID> {

    private UUID id;

    private String nome;

    private String cpf;

    private LocalDate dataDeNascimento;

    private List<ContatoDto> contatos = new ArrayList<>();
}
