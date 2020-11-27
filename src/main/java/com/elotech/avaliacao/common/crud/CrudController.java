package com.elotech.avaliacao.common.crud;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.elotech.avaliacao.common.ApplicationEntity;

public abstract class CrudController<E extends ApplicationEntity<ID>, ID extends Serializable, DTO> {

    @GetMapping("{id}")
    public ResponseEntity<E> findById(@PathVariable(name = "id") ID id) {

    }

    @GetMapping
    public ResponseEntity<Page<E>> findAll(@RequestParam(name = "pageSize", defaultValue = "50") Long pageSize,
                                           @RequestParam(name = "pageNumber", defaultValue = "1") Long pageNumber,
                                           @RequestParam(name = "searchParam") String searchParam
                                           ) {

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody DTO dto) {

    }

    @PutMapping("{id}")
    public void update(@PathVariable(name = "id") Long id,
                       @RequestBody DTO dto) {

    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {

    }
}
