package com.stiggles.smp5.dungeons;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

public class TargetHitEvent {
    @EventHandler
    public void onHitTarget(ProjectileHitEvent e) {
        if (e.getHitBlock() == null)
            return;
        if (e.getHitBlock().getType().equals(Material.TARGET)) {

        }
    }
}
