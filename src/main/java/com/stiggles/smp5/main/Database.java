package com.stiggles.smp5.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String HOST = "na04-sql.pebblehost.com";
    private final int PORT = 3306;
    private final String DATABASE = "customer_400683_stiggles";
    private final String USERNAME = "customer_400683_stiggles";
    private final String PASSWORD = "X$M5A!@F4dYU9qpf0fYQ";

    private Connection connection;

    public void connect() throws SQLException {
        connection= DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false",
                USERNAME,
                PASSWORD
        );
    }
    public boolean isConnected(){ return connection != null; }

    public Connection getConnection() { return connection; }

    public void disconnect() {
        if (isConnected()){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}