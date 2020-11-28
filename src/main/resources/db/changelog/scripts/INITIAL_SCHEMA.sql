--liquibase formatted sql

--changeset master:1
create extension if not exists "uuid-ossp";

--changeset master:2
create table "pessoa" (
    "id"                 uuid           not null default uuid_generate_v4(),
    "nome"               varchar(600)   not null,
    "cpf"                varchar(11)    not null,
    "data_de_nascimento" date           not null,
    constraint "pessoa_pk" primary key ("id")
);
--rollback drop table pessoa;

--changeset master:3
create table "contato" (
    "id" uuid not null default uuid_generate_v4(),
    "nome" varchar(600) not null,
    "telefone" varchar(30) not null,
    "email" varchar(1000) not null,
    "pessoa_id" uuid null,
    constraint "contato_pk" primary key ("id"),
    constraint "contato_pessoa_fk" foreign key ("pessoa_id") references pessoa("id")
);
--rollback drop table contato;