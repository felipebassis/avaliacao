package com.elotech.avaliacao.pessoa.crud;

import com.elotech.avaliacao.common.crud.CrudService;
import com.elotech.avaliacao.common.crud.Mapper;
import com.elotech.avaliacao.common.validation.ValidatorService;
import com.elotech.avaliacao.pessoa.database.PessoaRepository;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import com.elotech.avaliacao.pessoa.entity.PessoaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
class PessoaCrudService extends CrudService<PessoaRepository, Pessoa, UUID, PessoaDto> {

    protected PessoaCrudService(PessoaRepository repository,
                                ValidatorService<Pessoa> validatorService,
                                Mapper<Pessoa, PessoaDto> mapperToDto,
                                Mapper<PessoaDto, Pessoa> mapperToEntity) {
        super(repository, validatorService, mapperToDto, mapperToEntity);
    }


    @Override
    protected Page<Pessoa> paginatedSearch(Pageable pageable, String searchValue) {
        if (StringUtils.hasText(searchValue)) {
            return this.repository.findAllByNomeIsLike(searchValue, pageable);
        }
        return this.repository.findAll(pageable);
    }
}
