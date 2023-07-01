package com.stiggles.smp5.listeners;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

//DONT FORGET TO ADD THIS LISTENER WHEN DUNGEONS ARE ADDED

/**
 * For dungeons only.
 * Prevents creepers from destroying blocks and damaging "friendly"
 * entities a.k.a all non-player entities.
 */
public class DungeonExplosionEvent implements Listener {
    @EventHandler
    public void onBoomBoomBlock (BlockExplodeEvent e) {
        if (e.getBlock().getWorld().equals("testdungeon")) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBoomBoomEntity (EntityDamageByEntityEvent e) {
        if (!e.getEntity().getWorld().getName().equals("testdungeon"))
            return;
        if (!(e.getDamager() instanceof Creeper))
            return;
        if (e.getEntity() instanceof Player)
            return;
        e.setCancelled(true);
    }
}
