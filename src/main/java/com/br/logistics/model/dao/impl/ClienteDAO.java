package com.br.logistics.model.dao.impl;

import com.br.logistics.model.dao.AbstractDAOConnection;
import com.br.logistics.model.dto.FormDTO;
import com.br.logistics.model.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ClienteDAO extends AbstractDAOConnection {


    public Cliente busca() {
        connection();
        String sql = "SELECT * FROM cliente";
        ResultSet rs;

        {
            try {
                rs = getStmt().executeQuery(sql);

                while (rs.next()) {
                    this.id = rs.getLong("id");
                    this.email = rs.getString("codigo");
                    this.nome = rs.getString("nome");
                    this.cnpj = rs.getString("cnpj");


                    // faça o que desejar com os dados do cliente
                }
                System.out.println("Cliente: " + this.nome + " " + this.email + " " + this.cnpj);
                Cliente aaa = new Cliente(this.id, this.nome, this.email, this.cnpj);
                rs.close();
                getStmt().close();
                getConn().close();
                return aaa;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    Long id;
    String nome;
    String email;
    String cnpj;

    public void save(FormDTO cliente) {
    }
}
