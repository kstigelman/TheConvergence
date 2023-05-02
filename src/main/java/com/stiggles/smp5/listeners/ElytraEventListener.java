package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class ElytraEventListener implements Listener {

    private SMP5 main;
    private HashMap<UUID, LocalDateTime> timeSinceUsed;

    public ElytraEventListener (SMP5 main) {
        this.main = main;
        timeSinceUsed = new HashMap<>();
    }

    @EventHandler
    public void OnPlayerInteract (PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.isGliding() && e.hasItem() && e.getItem().getType().equals (Material.FIREWORK_ROCKET)) {
            if (!timeSinceUsed.get(p.getUniqueId()).isBefore(LocalDateTime.now().minusSeconds(300))) {
                p.sendMessage(ChatColor.RED + "[WINGSUIT]: Fuel use is still on cooldown!");
                e.setCancelled(true);
                return;
            }
            timeSinceUsed.put (p.getUniqueId(), LocalDateTime.now ());
            p.sendMessage(ChatColor.GRAY + "[WINGSUIT]: Recharged. Fuel use on cooldown for five minutes.");
        }
    }
}
