package com.br.logistics.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Data;


@Entity
@Transactional
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "endereco", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cliente_id"}, name = "uk_cliente_id"),
})
@Data
public class Endereco {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String endereco;
    private String rua;
    private String estado;
    private String cidade;
    private String cep;
    private String setor;
    private String complemento;
    private String latitude;
    private String longitude;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente clienteId;


    public Endereco() {

    }

    @Builder
    public Endereco(Long id, String endereco, String rua, String estado, String cidade, String cep, String setor, String complemento, String latitude, String longitude, Cliente clienteId) {
        this.id = id;
        this.endereco = endereco;
        this.rua = rua;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.setor = setor;
        this.complemento = complemento;
        this.latitude = latitude;
        this.longitude = longitude;
        this.clienteId = clienteId;
    }
}