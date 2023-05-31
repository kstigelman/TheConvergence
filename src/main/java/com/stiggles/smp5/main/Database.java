package com.stiggles.smp5.main;

import org.bukkit.Bukkit;
import java.io.File;
import java.sql.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Database {

    private String[] HOST_INFO = new String[5];
    private final String FILEPATH = "smp5/host.txt";

    private Connection connection = null;
    private PriorityQueue<String> statements = new PriorityQueue<>();

    public Database() {
        File file = new File("plugins/smp5/host.txt");
        try {
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < 5; ++i) {
                if (scanner.hasNext())
                    HOST_INFO[i] = scanner.next();
                else
                    HOST_INFO[i] = "";
            }
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("Failed to fetch host from " + FILEPATH + ". Shutting down...");
            Bukkit.getServer().shutdown();
        }
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST_INFO[0] + ":" + HOST_INFO[1] + "/" + HOST_INFO[2] + "?useSSL=false",
                HOST_INFO[3],
                HOST_INFO[4]
        );
        Bukkit.getConsoleSender().sendMessage("Successfully connected to Stiggles DB");
    }

    public boolean isConnectionNull() {
        return connection == null;
    }
    public boolean isConnected() { return connection != null; }

    public Connection getConnection() { return connection; }

    public boolean execute (String str) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.execute(str);
    }
    public ResultSet query (String str) throws SQLException {
        Statement statement = connection.createStatement ();
        return statement.executeQuery(str);

    }
    public void disconnect() throws SQLException {
        if (isConnected())
                connection.close();
    }

    public void runQueue () throws SQLException {
        runQueue(statements.size());
    }
    public void runQueue (int amount) throws SQLException {
        if (amount > statements.size())
            amount = statements.size();


        for (int i = 0; i < amount; ++i) {
            try {
                execute(statements.peek());
                statements.remove();
            }
            catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("NVTECH: Could not execute statement from queue");

                /*try {
                    FileReader fileReader = new FileReader("plugins/smp5/journal.txt");
                    BufferedReader br = new BufferedReader(fileReader);

                    String entry;
                    while ((entry = br.readLine ()) != null)


                    int n = main.getRandom() % dialogueList.size ();
                    dialogue = dialogueList.get (n);
                }
                catch (Exception e) {

                }*/
            }
        }

    }
}