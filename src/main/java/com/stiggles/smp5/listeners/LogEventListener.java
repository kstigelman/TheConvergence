package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Listen to player connect/disconnect events. Log player information to SQL database.
 *
 */
public class LogEventListener implements Listener {

    private SMP5 main;
    private final ArrayList<UUID> registeredUUIDs;

    public LogEventListener (SMP5 main) {
        this.main = main;

        //Load all players that have previously joined the server
        registeredUUIDs = new ArrayList<>();
        try {
            ResultSet rs = main.getDatabase().query("SELECT uuid FROM players");
            if (rs != null) {
                while (rs.next())
                    registeredUUIDs.add(UUID.fromString(rs.getString(1)));
                rs.close();
            }
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("LogEventListener: Could not query database");
        }
    }
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {
        Player p = e.getPlayer();
        //Check if player has joined the server before
        if (registeredUUIDs.contains(p.getUniqueId())) {
            e.setJoinMessage(ChatColor.LIGHT_PURPLE + p.getName() + " has entered The Convergence");
            log (e.getPlayer(), "LOGIN");
            return;
        }
        //New player
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
        BankManager.addPlayer (p);
        e.setJoinMessage(ChatColor.LIGHT_PURPLE + p.getName() + " has fallen into The Convergence");
    }

    @EventHandler
    public void onPlayerLeave (PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.DARK_PURPLE + e.getPlayer().getName() + " has left The Convergence");
        log (e.getPlayer(), "LOGOUT");
    }

    public void log (Player p, String logType){
        String world = p.getWorld ().getName();
        if (p.getWorld().getEnvironment() == World.Environment.NETHER)
            world = "nether";
        if (p.getWorld().getEnvironment() == World.Environment.THE_END)
            world = "end";

        try {
            main.getDatabase().execute(
                    "INSERT INTO log VALUES ('"
                            + p.getUniqueId() + "', '"
                            + LocalDateTime.now().format(main.getFormatter()) + "', '"
                            + logType + "', '"
                            + world + "', "
                            + p.getLocation().getBlockX() + ", "
                            + p.getLocation().getBlockY() + ", "
                            + p.getLocation().getBlockZ() + ")"
            );
        }
        catch (SQLException event) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to log player " + logType);
        }
    }
}