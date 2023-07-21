package com.stiggles.smp5.listeners;

import com.stiggles.smp5.dungeons.DungeonManager;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class DungeonKey implements Listener {
    SMP5 main;

    public DungeonKey(SMP5 main) {
        this.main = main;
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getItem() == null)
            return;

        ItemMeta im = e.getItem().getItemMeta();
        if (im == null)
            return;

        Player p = e.getPlayer();
        String localName = im.getLocalizedName();

        if (localName.equals("cave_key")) {
            e.setCancelled(true);
            e.getItem().setAmount(0);
            e.getPlayer().sendMessage(ChatColor.DARK_GRAY + "The gate to the Cave Dungeon begins to open...");
            e.getPlayer().playSound(e.getPlayer(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 0);
            Bukkit.getScheduler().runTaskLater(main, () -> {
                DungeonManager.addPlayer(e.getPlayer(), "testdungeon");
            }, 40);
            /*
            if (GateCuboids.getCaveGate().contains(e.getPlayer().getLocation())) {
                e.getItem().setAmount(0);
                e.getPlayer().sendMessage(ChatColor.DARK_GRAY + "The gate to the Cave Dungeon begins to open...");
                e.getPlayer().playSound(e.getPlayer(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 0);
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    DungeonManager.addPlayer(e.getPlayer(), "testdungeon");
                }, 40);
            }
            else {
                e.getPlayer().sendMessage(ChatColor.DARK_GRAY + "You can't use that here...");
            }*/
        }
    }
}
