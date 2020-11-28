package com.elotech.avaliacao.pessoa.crud;

import com.elotech.avaliacao.common.crud.Mapper;
import com.elotech.avaliacao.pessoa.database.PessoaRepository;
import com.elotech.avaliacao.pessoa.entity.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
class PessoaDtoToPessoaMapper implements Mapper<PessoaDto, Pessoa> {

    private final PessoaRepository repository;

    PessoaDtoToPessoaMapper(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pessoa convert(PessoaDto dto) {
        Pessoa pessoa = Optional.ofNullable(dto.getId())
                .flatMap(repository::findById)
                .orElseGet(Pessoa::new);
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(new Cpf(dto.getCpf(), CpfValidation.CURRENT));
        pessoa.setDataDeNascimento(dto.getDataDeNascimento());
        Map<UUID, Contato> contatos = pessoa.getContatos()
                .stream()
                .collect(Collectors.toMap(Contato::getId, contato -> contato));
        pessoa.setContatos(dto.getContatos()
                .stream()
                .map(contatoDto -> {
                    Contato contato = Optional.ofNullable(contatoDto.getId())
                            .map(contatos::get)
                            .orElseGet(Contato::new);
                    contato.setEmail(new Email(contatoDto.getEmail()));
                    contato.setNome(contatoDto.getNome());
                    contato.setTelefone(contatoDto.getTelefone());
                    return contato;
                })
                .collect(Collectors.toList()));
        return pessoa;
    }
}
