package com.stiggles.smp5.items;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {

    public static HashMap<UUID, Double> cooldown;

    public static void setupCooldown() {
        cooldown = new HashMap<>();
    }

    public static void setCooldown(Player player, int seconds) {
        double delay = System.currentTimeMillis() + (seconds * 1000L);
        cooldown.put(player.getUniqueId(), delay);
    }

    public static boolean checkCooldown(Player player) {
        return !cooldown.containsKey(player.getUniqueId()) || cooldown.get(player.getUniqueId()) <= System.currentTimeMillis();
    }

}
