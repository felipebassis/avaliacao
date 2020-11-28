package com.elotech.avaliacao.pessoa.crud;

import com.elotech.avaliacao.common.crud.CrudController;
import com.elotech.avaliacao.pessoa.entity.Pessoa;
import com.elotech.avaliacao.pessoa.entity.PessoaDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("pessoa")
public class PessoaCrudController extends CrudController<PessoaCrudService ,Pessoa, UUID, PessoaDto> {

    protected PessoaCrudController(PessoaCrudService crudService) {
        super(crudService);
    }
}
