package com.br.logistics.model.dao;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Data
public abstract class AbstractDAOConnection {

    String url = "jdbc:postgresql://172.19.0.20:5432/postgres";
    String username = "postgres";
    String password = "admin";
    Connection conn;
    Statement stmt;

    public void connection() {
        {
            try {
                setConn(DriverManager.getConnection(getUrl(), getUsername(), getPassword()));
                stmt = getConn().createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void clouseConnection() {
        {
            try {
                getStmt().close();
                getConn().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
