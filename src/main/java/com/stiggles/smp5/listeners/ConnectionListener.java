package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    private SMP5 main;

    public ConnectionListener (SMP5 main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {
        main.packetListener.inject (e.getPlayer());
        Bukkit.getServer ().broadcastMessage("Injecting packets!");

    }

    @EventHandler
    public void onPlayerQuit (PlayerQuitEvent e) {
        main.packetListener.stop (e.getPlayer());
    }
}
