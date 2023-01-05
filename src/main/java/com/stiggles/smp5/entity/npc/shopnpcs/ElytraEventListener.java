package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ElytraEventListener implements Listener {

    private SMP5 main;

    public ElytraEventListener (SMP5 main) {
        this.main = main;
    }

    @EventHandler
    public void OnPlayerInteract (PlayerInteractEvent e) {
        if (e.getPlayer ().isGliding() && e.hasItem() && e.getItem().getType().equals (Material.FIREWORK_ROCKET)) {
                e.getPlayer().sendMessage("You can not fly here!");
                e.setCancelled(true);
        }
    }
}
