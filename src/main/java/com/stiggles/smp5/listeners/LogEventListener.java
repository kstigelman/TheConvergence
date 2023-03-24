package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogEventListener implements Listener {

    private SMP5 main;

    public LogEventListener (SMP5 main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) throws SQLException {
        main.getDatabase().execute(
                "INSERT INTO log VALUES ('"
                + e.getPlayer().getUniqueId() + "', '"
                + LocalDateTime.now().format(main.getFormatter()) + "', "
                + "'LOGIN'"
                + e.getPlayer().getLocation().getBlockX() + ", "
                + e.getPlayer().getLocation().getBlockY() + ", "
                + e.getPlayer().getLocation().getBlockZ() + ")"
        );
    }
    @EventHandler
    public void onPlayerLeave (PlayerQuitEvent e) throws SQLException {
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
}
