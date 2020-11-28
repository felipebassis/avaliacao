package com.elotech.avaliacao.common.crud;

import com.elotech.avaliacao.common.validation.ValidatorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional
public abstract class CrudService<R extends JpaRepository<E, ID>,E extends ApplicationEntity<ID>, ID extends Serializable, DTO extends CrudDto<ID>> {

    protected final R repository;
    protected final ValidatorService<E> validatorService;
    protected final Mapper<E, DTO> mapperToDto;
    protected final Mapper<DTO, E> mapperToEntity;

    protected CrudService(R repository,
                          ValidatorService<E> validatorService,
                          Mapper<E, DTO> mapperToDto,
                          Mapper<DTO, E> mapperToEntity) {
        this.repository = repository;
        this.validatorService = validatorService;
        this.mapperToDto = mapperToDto;
        this.mapperToEntity = mapperToEntity;
    }

    public DTO findById(ID id) {
        return repository.findById(id)
                .map(mapperToDto::convert)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrar a entidade com ID " + id.toString()));
    }

    public Page<DTO> findAllPaginated(Long pageSize, Long pageNumber, String searchValue) {
        return this.paginatedSearch(PageRequest.of(pageNumber.intValue(), pageSize.intValue()), searchValue)
                .map(this.mapperToDto::convert);
    }

    protected abstract Page<E> paginatedSearch(Pageable pageable, String searchValue);

    public void save(DTO dto) {
        E entity = mapperToEntity.convert(dto);
        this.beforeSave(entity);
        this.validatorService.validate(entity);
        repository.save(entity);
    }

    protected void beforeSave(E entity) {

    }

    public void update(ID id, DTO dto) {
        dto.setId(id);
        E entity = mapperToEntity.convert(dto);
        this.beforeUpdate(entity);
        this.validatorService.validate(entity);
        this.repository.save(entity);
    }

    protected void beforeUpdate(E entity) {

    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

}
