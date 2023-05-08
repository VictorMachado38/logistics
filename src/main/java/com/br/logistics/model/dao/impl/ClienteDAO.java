package com.br.logistics.model.dao.impl;

import com.br.logistics.model.dao.AbstractDAOConnection;
import com.br.logistics.model.dto.FormDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteDAO extends AbstractDAOConnection {

    public List<FormDTO> busca() {
        connection();
        //language=PostgreSQL
        String sql = "SELECT c.id,c.codigo,c.nome,c.cnpj,e.cep,e.cidade,e.complemento,e.estado,e.latitude,e.longitude,rua,e.setor,e.endereco FROM cliente c join endereco e on c.id = e.cliente_id";
        ResultSet rs;

        {
            try {
                rs = getStmt().executeQuery(sql);
                List<FormDTO> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(FormDTO.builder()
                            .clienteId(rs.getLong("id"))
                            .nome(rs.getString("nome"))
                            .codigo(rs.getString("codigo"))
                            .cnpj(rs.getString("cnpj"))
                            .enderecoId(rs.getLong("id"))
                            .endereco(rs.getString("endereco"))
                            .cep(rs.getString("cep"))
                            .cidade(rs.getString("cidade"))
                            .complemento(rs.getString("complemento"))
                            .estado(rs.getString("estado"))
                            .latitude(rs.getString("latitude"))
                            .longitude(rs.getString("longitude"))
                            .rua(rs.getString("rua"))
                            .setor(rs.getString("setor"))
                            .build());
                }
                clouseConnection();
                return list;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public FormDTO upgrade(FormDTO formDTO) {
        connection();
        //language=PostgreSQL
        String sql1 = "UPDATE cliente SET " +
                "nome = '" + formDTO.getNome() + "'," +
                "codigo = '" + formDTO.getCodigo() + "'," +
                "cnpj = '" + formDTO.getCnpj() + "'" +
                "WHERE id = " + formDTO.getClienteId();
        //language=PostgreSQL
        String sql2 = "UPDATE endereco SET " +
                "cep = '" + formDTO.getCep() + "'," +
                "cidade = '" + formDTO.getCidade() + "'," +
                "complemento = '" + formDTO.getComplemento() + "'," +
                "estado = '" + formDTO.getEstado() + "'," +
                "latitude = '" + formDTO.getLatitude() + "'," +
                "longitude = '" + formDTO.getLongitude() + "'," +
                "rua = '" + formDTO.getRua() + "'," +
                "setor = '" + formDTO.getSetor() + "'" +
                "WHERE id = " + formDTO.getEnderecoId();
        String unionSql = sql1 + " UNION " + sql2;
        {
            try {
                Integer a = getStmt().executeUpdate(sql1);
                Integer b = getStmt().executeUpdate(sql2);
                clouseConnection();
                return formDTO;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
