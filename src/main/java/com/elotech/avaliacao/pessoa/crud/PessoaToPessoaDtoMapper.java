package com.elotech.avaliacao.pessoa.crud;

import com.elotech.avaliacao.common.crud.Mapper;
import com.elotech.avaliacao.pessoa.entity.ContatoDto;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import com.elotech.avaliacao.pessoa.entity.PessoaDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
class PessoaToPessoaDtoMapper implements Mapper<Pessoa, PessoaDto> {

    @Override
    public PessoaDto convert(Pessoa pessoa) {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId(pessoa.getId());
        pessoaDto.setNome(pessoa.getNome());
        pessoaDto.setDataDeNascimento(pessoa.getDataDeNascimento());
        pessoaDto.setCpf(pessoa.getCpf());
        pessoaDto.setContatos(pessoa.getContatos()
                .stream()
                .map(contato -> {
                    ContatoDto contatoDto = new ContatoDto();
                    contatoDto.setId(contato.getId());
                    contatoDto.setNome(contato.getNome());
                    contatoDto.setEmail(contato.getEmail());
                    contatoDto.setTelefone(contato.getTelefone());
                    return contatoDto;
                })
                .collect(Collectors.toList()));
        return pessoaDto;
    }
}
