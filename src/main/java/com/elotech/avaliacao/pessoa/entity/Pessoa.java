package com.elotech.avaliacao.pessoa.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.elotech.avaliacao.common.ApplicationEntity;

import lombok.Getter;

@Entity
@Table(name = "pessoa")
public class Pessoa implements ApplicationEntity<UUID> {

    private static final long serialVersionUID = -4346405067680838815L;

    @Id
    @Getter
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Getter
    @Column(name = "nome")
    private String nome;

    @Embedded
    private Cpf cpf;

    @Getter
    @Column(name = "data_de_nascimento")
    private LocalDate dataDeNascimento;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pessoa_id")
    private List<Contato> contatos = new ArrayList<>();

    public String getCpf() {
        return this.cpf.getCpfValue();
    }
}
