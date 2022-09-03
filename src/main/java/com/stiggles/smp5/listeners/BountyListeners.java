package com.stiggles.smp5.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BountyListeners implements Listener {

    @EventHandler
    public void OnPlayerDeath (PlayerDeathEvent e) {
        if (e.getPlayer ().getKiller () == null)
            return;
        Player victim = e.getPlayer ();
        Player killer = victim.getKiller ();




    }
}
