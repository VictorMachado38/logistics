package com.br.logistics.model.entity;

import com.br.logistics.model.dto.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Data;


@Entity
@Transactional
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "cliente", schema = "public")
@Data
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nome;
    private String codigo;
    private String cnpj;

    @Builder
    public Cliente(Long id, String nome, String codigo, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.cnpj = cnpj;
    }

    public Cliente() {

    }

    public ClienteDTO toDTO() {
        return ClienteDTO.builder()
                .id(this.id)
                .nome(this.nome)
                .codigo(this.codigo)
                .cnpj(this.cnpj)
                .build();
    }
}