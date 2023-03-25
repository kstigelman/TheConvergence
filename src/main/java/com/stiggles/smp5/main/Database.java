package com.stiggles.smp5.main;

import org.bukkit.Bukkit;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Database {

    private final String HOST = "HIDDEN";
    private final int PORT = 3306;
    private final String DATABASE = "HIDDEN";
    private final String USERNAME = "HIDDEN";
    private final String PASSWORD = "HIDDEN";

    private Connection connection = null;
    private PriorityQueue<String> statements = new PriorityQueue<>();

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false");

    }
    public boolean isConnected() { return connection != null; }

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
        if (isConnected()) {
            try {
                connection.close();
            }
            catch (SQLException e) {
                Bukkit.broadcastMessage("NVTECH: Failed to disconnect from database.");

            }
        }
    }
    public void runQueue () {
        runQueue(statements.size());
    }
    public void runQueue (int amount) {
        if (amount > statements.size())
            amount = statements.size();

        for (int i = 0; i < amount; ++i) {
            try {
                execute(statements.peek());
                statements.remove();
            }
            catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("NVTECH: Could not execute statement from queue");
            }
        }
    }
}