package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

/**
 * ElytraEventListener:
 * Checks if the player is attempting to use a firework while using an Elytra.
 * The player is limited to 1 firework use every 5 minutes to discourage use
 * of flight as a means of travel.
 */
public class ElytraEventListener implements Listener {
    //Five minute cooldown
    private static final int COOLDOWN_IN_SECONDS = 300;
    private final SMP5 main;
    private final HashMap<UUID, LocalDateTime> timeSinceUsed;

    public ElytraEventListener(SMP5 main) {
        this.main = main;
        timeSinceUsed = new HashMap<>();
    }

    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        if (p.isGliding() && e.hasItem() && e.getItem().getType().equals(Material.FIREWORK_ROCKET)) {
            LocalDateTime ldt = timeSinceUsed.get(p.getUniqueId());
            if (ldt != null) {
                if (!ldt.isBefore(LocalDateTime.now().minusSeconds(COOLDOWN_IN_SECONDS))) {
                    p.sendMessage(ChatColor.RED + "[WINGSUIT]: Fuel use is still on cooldown! " + (COOLDOWN_IN_SECONDS - Duration.between(timeSinceUsed.get(p.getUniqueId()), LocalDateTime.now()).getSeconds()) + "s");
                    e.setCancelled(true);
                    return;
                }
            }
            timeSinceUsed.put(p.getUniqueId(), LocalDateTime.now());
            p.sendMessage(ChatColor.GRAY + "[WINGSUIT]: Recharged. Fuel use on cooldown for five minutes.");
        }
    }
}
