package com.k24qlhs.config;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class JDBCConnect {
    public final static String dbName = "/k24qlhs";
    public final static String userame = "root";
    public final static String password = "12345";
    public final static String url = "jdbc:mysql://localhost:3306" + dbName;
    public Connection connection () {
        try {
            Connection connection = DriverManager.getConnection(url,userame,password);
            return connection;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
