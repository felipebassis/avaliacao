package com.elotech.avaliacao.pessoa.entity;

import com.elotech.avaliacao.common.crud.ApplicationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "pessoa")
public class Pessoa implements ApplicationEntity<UUID> {

    private static final long serialVersionUID = -4346405067680838815L;

    @Id
    @Getter
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Getter
    @Setter
    @Column(name = "nome")
    private String nome;

    @Embedded
    @Setter
    private Cpf cpf;

    @Getter
    @Setter
    @Column(name = "data_de_nascimento")
    private LocalDate dataDeNascimento;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private List<Contato> contatos = new ArrayList<>();

    public String getCpf() {
        return Optional.ofNullable(this.cpf)
                .map(Cpf::getCpfValue)
                .orElse(null);
    }
}
