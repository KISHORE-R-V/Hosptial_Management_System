package com.jts.hms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnector {
    private static Connection conn;

    private static Connection createConn() throws ClassNotFoundException, SQLException {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Create a connection to the MySQL database
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Hostpital_Management_System",
                "root",
                "root");

        System.out.println("✅ Database connection created successfully.");

        return conn;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            return createConn();
        }
        return conn;
    }

}
