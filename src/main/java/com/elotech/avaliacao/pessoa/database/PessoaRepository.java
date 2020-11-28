package com.elotech.avaliacao.pessoa.database;

import com.elotech.avaliacao.pessoa.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    Page<Pessoa> findAllByNomeIsLike(String nome, Pageable pageable);
}
