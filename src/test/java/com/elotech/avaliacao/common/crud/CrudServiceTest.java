package com.elotech.avaliacao.common.crud;

import com.elotech.avaliacao.common.validation.ValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrudServiceTest {

    private CrudService<JpaRepository<ApplicationEntity<Serializable>, Serializable>,
            ApplicationEntity<Serializable>,
            Serializable,
            CrudDto<Serializable>> crudService;

    @Mock
    private JpaRepository<ApplicationEntity<Serializable>, Serializable> repository;

    @Mock
    private ValidatorService<ApplicationEntity<Serializable>> validatorService;

    @Mock
    private Mapper<ApplicationEntity<Serializable>, CrudDto<Serializable>> mapperToDto;

    @Mock
    private Mapper<CrudDto<Serializable>, ApplicationEntity<Serializable>> mapperToEntity;

    @BeforeEach
    void setUp() {
        crudService = Mockito.spy(new CrudService<JpaRepository<ApplicationEntity<Serializable>, Serializable>,
                ApplicationEntity<Serializable>,
                Serializable,
                CrudDto<Serializable>>(repository, validatorService, mapperToDto, mapperToEntity) {
            @Override
            protected Page<ApplicationEntity<Serializable>> paginatedSearch(Pageable pageable, String searchValue) {
                return new PageImpl<>(new ArrayList<>(), pageable, 0);
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldFindEntityById() {
        Serializable mockId = mock(Serializable.class);
        ApplicationEntity<Serializable> mockEntity = mock(ApplicationEntity.class);
        CrudDto<Serializable> mockDto = mock(CrudDto.class);
        when(repository.findById(mockId)).thenReturn(Optional.of(mockEntity));
        when(mapperToDto.convert(mockEntity)).thenReturn(mockDto);

        assertEquals(mockDto, crudService.findById(mockId));
    }

    @Test
    void shouldThrowExceptionWhenEntityNotFound() {
        Serializable mockId = mock(Serializable.class);
        when(repository.findById(mockId)).thenReturn(Optional.empty());

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> crudService.findById(mockId));
        assertEquals("Não foi possivel encontrar a entidade com ID " + mockId.toString(), illegalArgumentException.getMessage());
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldCallCorrectMethodsWhileSaving() {
        CrudDto<Serializable> mockDto = mock(CrudDto.class);
        ApplicationEntity<Serializable> mockEntity = mock(ApplicationEntity.class);
        when(mapperToEntity.convert(mockDto)).thenReturn(mockEntity);
        crudService.save(mockDto);
        verify(validatorService, times(1)).validate(mockEntity);
        verify(crudService, times(1)).beforeSave(mockEntity);
        verify(repository, times(1)).save(mockEntity);
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldCallCorrectMethodsWhileUpdating() {
        CrudDto<Serializable> mockDto = mock(CrudDto.class);
        ApplicationEntity<Serializable> mockEntity = mock(ApplicationEntity.class);
        Serializable mockId = mock(Serializable.class);
        when(mapperToEntity.convert(mockDto)).thenReturn(mockEntity);
        crudService.update(mockId, mockDto);
        verify(mockDto, times(1)).setId(mockId);
        verify(validatorService, times(1)).validate(mockEntity);
        verify(crudService, times(1)).beforeUpdate(mockEntity);
        verify(repository, times(1)).save(mockEntity);
    }

    @Test
    void shouldCallCorrectMethodsWhileDeleting() {
        Serializable mockId = mock(Serializable.class);
        crudService.deleteById(mockId);
        verify(repository, times(1)).deleteById(mockId);
    }
}