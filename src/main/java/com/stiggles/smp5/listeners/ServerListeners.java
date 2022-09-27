package com.stiggles.smp5.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerListeners implements Listener {

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {
        //if player is NOT found in database, run nbew player
    }

    @EventHandler
    public void onPlayerLeave (PlayerQuitEvent e) {
        //Update player database
    }
}
