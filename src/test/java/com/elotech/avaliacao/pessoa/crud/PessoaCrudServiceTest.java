package com.elotech.avaliacao.pessoa.crud;

import com.elotech.avaliacao.common.crud.Mapper;
import com.elotech.avaliacao.pessoa.database.PessoaRepository;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import com.elotech.avaliacao.pessoa.entity.PessoaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PessoaCrudServiceTest {

    private PessoaCrudService pessoaCrudService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private PessoaValidatorService validatorService;

    @Mock
    private Mapper<Pessoa, PessoaDto> aMapper;

    @Mock
    private Mapper<PessoaDto, Pessoa> anotherMapper;

    @BeforeEach
    void setUp() {
        pessoaCrudService = new PessoaCrudService(pessoaRepository, validatorService, aMapper, anotherMapper);
    }

    @Test
    void shouldCallPageableFindAllWhenSearchValueIsEmpty() {
        Pageable pageable = Pageable.unpaged();
        pessoaCrudService.paginatedSearch(pageable, "");
        verify(pessoaRepository, times(1)).findAll(pageable);
    }

    @Test
    void shouldCallFindAllNomeIsLikeWhenSearchValueIsNotEmpty() {
        Pageable pageable = Pageable.unpaged();
        pessoaCrudService.paginatedSearch(pageable, "Test");
        verify(pessoaRepository, times(1)).findAllByNomeIsLike("Test", pageable);
    }
}