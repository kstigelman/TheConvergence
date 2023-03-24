package com.stiggles.smp5.main;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Database {

    private final String HOST = "HIDDEN";
    private final int PORT = 3306;
    private final String DATABASE = "HIDDEN";
    private final String USERNAME = "HIDDEN";
    private final String PASSWORD = "HIDDEN";

    private Connection connection = null;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false");
    }
    public boolean isConnected(){ return connection != null; }

    public Connection getConnection() { return connection; }

    public boolean execute (String str) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.execute(str);
    }
    public ResultSet query (String str) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(str);
    }
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