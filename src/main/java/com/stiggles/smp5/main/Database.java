package com.stiggles.smp5.main;

import org.bukkit.Bukkit;
import java.io.File;
import java.sql.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Database {

    private String HOST = "HIDDEN";
    private final int PORT = 3306;
    private final String DATABASE = "HIDDEN";
    private final String USERNAME = "HIDDEN";
    private final String PASSWORD = "HIDDEN";
    private final String FILEPATH = "smp5/host.txt";

    private Connection connection = null;
    private PriorityQueue<String> statements = new PriorityQueue<>();

    public Database () {
        File file = new File ("plugins/smp5/host.txt");
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNext())
                HOST = scanner.next ();
        }
        catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("Failed to fetch host from " + FILEPATH + ". Shutting down...");
            Bukkit.getServer().shutdown();
        }
    }
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + HOST);
        Bukkit.getConsoleSender().sendMessage("Successfully connected to Stiggles DB");
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
    public void disconnect() throws SQLException {
        if (isConnected())
                connection.close();
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