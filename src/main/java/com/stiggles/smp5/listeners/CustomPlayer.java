package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.entity.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomPlayer {

    private SMP5 main;

    private Player player;
    private UUID uuid;
    private String rank;
    private String name;
    private String logOffWorld;
    private int logOffX;
    private int logOffY;
    private int logOffZ;
    private int coins;

    public CustomPlayer(SMP5 main, Player player) throws SQLException {
        this.main = main;
        this.player = player;
        this.uuid = player.getUniqueId();

        PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("SELECT RANK FROM player_info WHERE UUID = ?;");
        System.out.println ("Connected to database.");
        statement.setString(1, uuid.toString());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            rank = rs.getString("RANK");

        } else {
            rank = "SMP";
            name = player.getName().toString();
            logOffWorld = "world";
            logOffX = 0;
            logOffY = 64;
            logOffZ = 0;
            coins = 0;
            System.out.println ("Initialized values.");
            PreparedStatement statement1 = main.getDatabase().getConnection().prepareStatement("INSERT INTO player_info (ID, UUID, RANK, NAME, LOG_OFF_WORLD, LOG_OFF_X, LOG_OFF_Y, LOG_OFF_Z, COINS) VALUES (" +
                    "default," +
                    "'" + uuid.toString() +"'," +
                    "'" + rank +"'," +
                    "'" + name +"'," +
                    "'" + logOffWorld +"'," +
                    logOffX + "," +
                    logOffY + "," +
                    logOffZ + "," +
                    coins   + ");");
            System.out.println ("Added statements to database.");
            statement1.executeUpdate();
            System.out.println ("Updated database.");
        }

    }

    public void setRank(String rank) {
        this.rank = rank;
        try {
            PreparedStatement statement=  main.getDatabase().getConnection().prepareStatement("UPDATE player_info SET RANK = '" + rank + "' WHERE ``UUID`` = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLogOffWorld(String world){
        this.logOffWorld = world;
        try {
            PreparedStatement statement=  main.getDatabase().getConnection().prepareStatement("UPDATE player_info SET LOG_OFF_WORLD = '" + world + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLogOffX(int x){
        this.logOffZ = x;
        try {
            PreparedStatement statement=  main.getDatabase().getConnection().prepareStatement("UPDATE player_info SET LOG_OFF_X = " + x + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLogOffY(int y){
        this.logOffY = y;
        try {
            PreparedStatement statement=  main.getDatabase().getConnection().prepareStatement("UPDATE player_info SET LOG_OFF_Y = '" + y + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLogOffZ(int z){
        this.logOffZ = z;
        try {
            PreparedStatement statement=  main.getDatabase().getConnection().prepareStatement("UPDATE player_info SET LOG_OFF_Z = '" + z + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCoins (int c) {
        this.coins = c;
        try {
            PreparedStatement statement=  main.getDatabase().getConnection().prepareStatement("UPDATE player_info SET COINS = " + c + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getRank() { return rank; }

}