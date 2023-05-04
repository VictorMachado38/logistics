package com.br.logistics.model.dao.impl;

import com.br.logistics.model.entity.Cliente;

import java.sql.*;

public class ClienteDAO2 {


    String url = "jdbc:postgresql://localhost:5432/postgres";
    String username = "postgres";
    String password = "admin";
    Connection conn;


    public Cliente tesets() {

        {
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        Statement stmt;

        {
            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String sql = "SELECT * FROM cliente";
        ResultSet rs;

        {
            try {
                rs = stmt.executeQuery(sql);

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
                stmt.close();
                conn.close();
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

}
