package com.elotech.avaliacao.pessoa.crud;

import com.elotech.avaliacao.pessoa.database.PessoaRepository;
import com.elotech.avaliacao.pessoa.entity.Contato;
import com.elotech.avaliacao.pessoa.entity.ContatoDto;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import com.elotech.avaliacao.pessoa.entity.PessoaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaDtoToPessoaMapperTest {

    private PessoaDtoToPessoaMapper pessoaDtoToPessoaMapper;

    @Mock
    private PessoaRepository pessoaRepository;

    @BeforeEach
    void setUp() {
        pessoaDtoToPessoaMapper = new PessoaDtoToPessoaMapper(pessoaRepository);
    }

    @Test
    void shouldCorrectlyMapNewEntity() {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Test2");
        pessoaDto.setCpf("11174896981");
        pessoaDto.setDataDeNascimento(LocalDate.now());
        ContatoDto contatoDto = new ContatoDto();
        contatoDto.setNome("Test50");
        contatoDto.setEmail("test87@test.com");
        contatoDto.setTelefone("9985467884");
        pessoaDto.setContatos(Collections.singletonList(contatoDto));

        Pessoa convert = pessoaDtoToPessoaMapper.convert(pessoaDto);

        assertEquals(pessoaDto.getNome(), convert.getNome());
        assertEquals(pessoaDto.getCpf(), convert.getCpf());
        assertEquals(pessoaDto.getDataDeNascimento(), convert.getDataDeNascimento());

        List<Contato> contatos = convert.getContatos();
        assertEquals(1, contatos.size());
        Contato contato = contatos.get(0);
        assertEquals(contatoDto.getNome(), contato.getNome());
        assertEquals(contatoDto.getEmail(), contato.getEmail());
        assertEquals(contatoDto.getTelefone(), contato.getTelefone());
    }

    @Test
    void shouldCorrectlyMapExistingEntity() {
        PessoaDto pessoaDto = new PessoaDto();
        Pessoa pessoa = new Pessoa();
        Contato contato = new Contato();
        pessoa.setContatos(Collections.singletonList(contato));
        UUID id = pessoa.getId();
        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));

        pessoaDto.setId(id);
        pessoaDto.setNome("Test2");
        pessoaDto.setCpf("11174896981");
        pessoaDto.setDataDeNascimento(LocalDate.now());
        ContatoDto contatoDto = new ContatoDto();
        contatoDto.setId(contato.getId());
        contatoDto.setNome("Test50");
        contatoDto.setEmail("test87@test.com");
        contatoDto.setTelefone("9985467884");
        pessoaDto.setContatos(Collections.singletonList(contatoDto));

        Pessoa convert = pessoaDtoToPessoaMapper.convert(pessoaDto);

        assertEquals(pessoaDto.getNome(), convert.getNome());
        assertEquals(pessoaDto.getCpf(), convert.getCpf());
        assertEquals(pessoaDto.getDataDeNascimento(), convert.getDataDeNascimento());

        List<Contato> contatos = convert.getContatos();
        assertEquals(1, contatos.size());
        Contato contatoMapped = contatos.get(0);
        assertEquals(contatoDto.getNome(), contatoMapped.getNome());
        assertEquals(contatoDto.getEmail(), contatoMapped.getEmail());
        assertEquals(contatoDto.getTelefone(), contatoMapped.getTelefone());
    }
}