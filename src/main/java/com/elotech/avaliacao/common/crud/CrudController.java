package com.elotech.avaliacao.common.crud;

import com.elotech.avaliacao.common.exceptionhandlers.ResponseError;
import com.elotech.avaliacao.common.validation.Error;
import com.elotech.avaliacao.common.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@Slf4j
public abstract class CrudController<S extends CrudService<?, E, ID, DTO>, E extends ApplicationEntity<ID>, ID extends Serializable, DTO extends CrudDto<ID>> {

    private final S crudService;

    protected CrudController(S crudService) {
        this.crudService = crudService;
    }

    @GetMapping("{id}")
    public ResponseEntity<DTO> findById(@PathVariable(name = "id") ID id) {
        return ResponseEntity.ok(crudService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<DTO>> findAll(@RequestParam(name = "pageSize", defaultValue = "50") Long pageSize,
                                             @RequestParam(name = "pageNumber", defaultValue = "0   ") Long pageNumber,
                                             @RequestParam(name = "searchParam", defaultValue = "") String searchParam
    ) {
        return ResponseEntity.ok(crudService.findAllPaginated(pageSize, pageNumber, searchParam));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody DTO dto) {
        crudService.save(dto);
    }

    @PutMapping("{id}")
    public void update(@PathVariable(name = "id") ID id,
                       @RequestBody DTO dto) {
        crudService.update(id, dto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable(name = "id") ID id) {
        crudService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ValidationException.class)
    public ResponseError test(ValidationException exception) {
        log.error("Erro ao validar o objeto do tipo {}", exception.getFailedValidationType().getSimpleName(), exception);
        ResponseError responseError = new ResponseError();
        responseError.setMessage(exception.getMessage());
        exception.getCauses()
                .stream()
                .map(Error::getMessage)
                .forEach(responseError::addFailedValidation);
        return responseError;
    }

}
