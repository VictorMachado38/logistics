package com.br.logistics.model.dto;

import com.br.logistics.model.entity.Cliente;
import com.br.logistics.model.entity.Endereco;
import lombok.Builder;
import lombok.Data;

@Data
public class FormDTO {

    private Long clienteId;
    private String nome;
    private String codigo;
    private String cnpj;
    private Long enderecoId;
    private String endereco;
    private String rua;
    private String estado;
    private String cidade;
    private String cep;
    private String setor;
    private String complemento;
    private String latitude;
    private String longitude;

    public FormDTO() {
    }
    @Builder
    public FormDTO(Long clienteId, String nome, String codigo, String cnpj, Long enderecoId, String endereco, String rua, String estado, String cidade, String cep, String setor, String complemento, String latitude, String longitude) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.codigo = codigo;
        this.cnpj = cnpj;
        this.enderecoId = enderecoId;
        this.endereco = endereco;
        this.rua = rua;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.setor = setor;
        this.complemento = complemento;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public Cliente toClientEntity() {
        return Cliente.builder()
                .id(this.clienteId)
                .nome(this.nome)
                .codigo(this.codigo)
                .cnpj(this.cnpj)
                .build();
    }

    public Endereco toEnderecoEntity(Cliente cliente) {
        return Endereco.builder()
                .id(this.enderecoId)
                .endereco(this.endereco)
                .rua(this.rua)
                .estado(this.estado)
                .cidade(this.cidade)
                .cep(this.cep)
                .setor(this.setor)
                .complemento(this.complemento)
                .clienteId(cliente)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}
