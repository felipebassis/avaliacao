package com.elotech.avaliacao.pessoa.crud;

import com.elotech.avaliacao.pessoa.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PessoaToPessoaDtoMapperTest {

    private PessoaToPessoaDtoMapper pessoaToPessoaDtoMapper = new PessoaToPessoaDtoMapper();

    @Test
    void shouldCorrectlyMapDto() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Test2");
        pessoa.setCpf(new Cpf("11174896981", CpfValidation.CURRENT));
        pessoa.setDataDeNascimento(LocalDate.now());
        Contato contato = new Contato();
        contato.setNome("Test50");
        contato.setEmail(new Email("test87@test.com"));
        contato.setTelefone("9985467884");
        pessoa.setContatos(Collections.singletonList(contato));

        PessoaDto convert = pessoaToPessoaDtoMapper.convert(pessoa);

        assertEquals(pessoa.getId(), convert.getId());
        assertEquals(pessoa.getNome(), convert.getNome());
        assertEquals(pessoa.getCpf(), convert.getCpf());
        assertEquals(pessoa.getDataDeNascimento(), convert.getDataDeNascimento());

        List<ContatoDto> contatos = convert.getContatos();
        assertEquals(1, contatos.size());
        ContatoDto contatoDto = contatos.get(0);

        assertEquals(contato.getId(), contatoDto.getId());
        assertEquals(contato.getNome(), contatoDto.getNome());
        assertEquals(contato.getEmail(), contatoDto.getEmail());
        assertEquals(contato.getTelefone(), contatoDto.getTelefone());
    }
}