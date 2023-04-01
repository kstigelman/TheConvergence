package com.stiggles.smp5.main;

import org.bukkit.Bukkit;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.sql.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Database {

    private String HOST = "HIDDEN";
    private final int PORT = 3306;
    private final String DATABASE = "HIDDEN";
    private final String USERNAME = "HIDDEN";
    private final String PASSWORD = "HIDDEN";

    private Connection connection = null;
    private PriorityQueue<String> statements = new PriorityQueue<>();

    public Database () {
        File file = new File("plugins/smp5/host.txt");

        try {
            Scanner scanner = new Scanner(file);
            HOST = scanner.next();
        }
        catch (Exception c) {
            HOST = "null";
            Bukkit.getConsoleSender().sendMessage("Failed to load file host.txt");
        }

        try {
            connect();
            connection.createStatement().execute("INSERT INTO log (x_pos, y_pos, z_pos) values (3, 4, 5);");
            Bukkit.getConsoleSender().sendMessage("Loaded values into DB 2");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Database connection failed. Server shutting down.");
            Bukkit.getServer().shutdown();
        }

    }
    public void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:sqlite:" + HOST + "?useSSL=false");
        Bukkit.getConsoleSender().sendMessage("Successfully connected to Stiggles DB");
        connection.createStatement().execute("INSERT INTO log (x_pos, y_pos, z_pos) values (1, 2, 3);");
        Bukkit.getConsoleSender().sendMessage("Loaded values into DB");
    }
    public boolean isConnected() { return connection != null; }

    public Connection getConnection() { return connection; }

    public boolean execute (String str) {
        try {
            Statement statement = connection.createStatement();
            return statement.execute(str);
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Failed to execute statement " + str);
            return false;
        }
    }
    public ResultSet query (String str) {
        try {
            Statement statement = connection.createStatement();
            Bukkit.getConsoleSender().sendMessage("FAIL");
            return statement.executeQuery(str);
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Failed to query statement " + str);
            return null;
        }
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
            boolean status = execute(statements.peek());
            if (status)
                statements.remove();
        }
    }
}