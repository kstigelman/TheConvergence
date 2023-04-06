package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.CoinBank;
import net.citizensnpcs.npc.ai.speech.Chat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.checkerframework.checker.units.qual.A;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;


public class LogEventListener implements Listener {

    private SMP5 main;
    private final ArrayList<UUID> registeredUUIDs;

    public LogEventListener (SMP5 main) {
        this.main = main;

        //Load all players that have previously joined the server
        registeredUUIDs = new ArrayList<>();
        try {
            ResultSet rs = main.getDatabase().query("SELECT uuid FROM players");
            while (rs.next ())
                registeredUUIDs.add (UUID.fromString(rs.getString(1)));
            rs.close ();
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("LogEventListener: Could not query database");
        }
    }
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {
        Player p = e.getPlayer();


        //Check if player has joined the server before
        if (!registeredUUIDs.contains(p.getUniqueId())) {
            try {
                //Register player record
                main.getDatabase().execute("INSERT INTO players VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', " + 0 + ")");
                //Register bank record
                main.getDatabase().execute("INSERT INTO bank VALUES ('" + p.getUniqueId() + "', '" + 0 + ")");
            }
            catch (SQLException event) {
                Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to register new player.");
            }
            registeredUUIDs.add (p.getUniqueId());
            e.setJoinMessage(ChatColor.LIGHT_PURPLE + p.getName() + " has fallen into The Convergence");
        }
        else
            e.setJoinMessage(ChatColor.LIGHT_PURPLE + p.getName() + " has entered The Convergence");

        try {
            main.getDatabase().execute(
                    "INSERT INTO log VALUES ('"
                            + e.getPlayer().getUniqueId() + "', '"
                            + LocalDateTime.now().format(main.getFormatter()) + "', "
                            + "'LOGIN', "
                            + e.getPlayer().getLocation().getBlockX() + ", "
                            + e.getPlayer().getLocation().getBlockY() + ", "
                            + e.getPlayer().getLocation().getBlockZ() + ")"
            );
        }
        catch (SQLException event) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to log player login");
        }
    }
    @EventHandler
    public void onPlayerLeave (PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.DARK_PURPLE + e.getPlayer().getName() + " has left The Convergence");
        try {
            main.getDatabase().execute(
                    "INSERT INTO log VALUES ('"
                            + e.getPlayer().getUniqueId() + "', '"
                            + LocalDateTime.now().format(main.getFormatter()) + "', "
                            + "'LOGOUT', "
                            + e.getPlayer().getLocation().getBlockX() + ", "
                            + e.getPlayer().getLocation().getBlockY() + ", "
                            + e.getPlayer().getLocation().getBlockZ() + ")"
            );
        }
        catch (SQLException event) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to log player logout");
        }
    }
    public void logout (Player p) {
        try {
            main.getDatabase().execute(
                    "INSERT INTO log VALUES ('"
                            + p.getUniqueId() + "', '"
                            + LocalDateTime.now().format(main.getFormatter()) + "', "
                            + "'LOGOUT', "
                            + p.getLocation().getBlockX() + ", "
                            + p.getLocation().getBlockY() + ", "
                            + p.getLocation().getBlockZ() + ")"
            );

        }
        catch (SQLException event) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to log player logout");
        }
    }
}
