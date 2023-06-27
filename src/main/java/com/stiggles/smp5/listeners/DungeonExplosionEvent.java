package com.stiggles.smp5.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

//DONT FORGET TO ADD THIS LISTENER WHEN DUNGEONS ARE ADDED
public class DungeonExplosionEvent implements Listener {
    @EventHandler
    public void onBoomBoom(BlockExplodeEvent e) {
        if (e.getBlock().getWorld().equals("testdungeon")) {
            e.setCancelled(true);
        }
    }
}
