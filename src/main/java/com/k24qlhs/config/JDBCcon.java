package com.k24qlhs.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCcon {
    public final static String dbName = "/k24qlhs";
    public final static String userame = "root";
    public final static String password = "12345";
    public final static String url = "jdbc:mysql://localhost:3306" + dbName;
    public static Connection connection () {
        try {
            Connection connection = DriverManager.getConnection(url,userame,password);
            return connection;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
