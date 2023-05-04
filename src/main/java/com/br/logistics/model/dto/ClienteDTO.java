package com.br.logistics.model.dto;

import com.br.logistics.model.entity.Cliente;
import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class ClienteDTO {

    private Long id;
    private String nome;
    private String codigo;
    private String cnpj;

    @Builder
    public ClienteDTO(Long id, String nome, String codigo, String cnpj) {
//        if (nome == null) {
//            throw new IllegalArgumentException("Nome não pode ser nulo");
//        }
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.cnpj = cnpj;
    }


    public Cliente toEntity() {
        return Cliente.builder()
                .id(this.id)
                .nome(this.nome)
                .codigo(this.codigo)
                .cnpj(this.cnpj)
                .build();
    }
}
