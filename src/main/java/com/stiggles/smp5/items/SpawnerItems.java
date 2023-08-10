package com.stiggles.smp5.items;

import com.stiggles.smp5.entity.monsters.CustomSpawns;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;

public class SpawnerItems implements Listener {
    SMP5 main;
    int counter = 0;

    public SpawnerItems (SMP5 main) {
      this.main = main;
    }
    @EventHandler
    public void onRightClick (PlayerInteractEvent e) {
        if (!e.getAction().equals(Action.RIGHT_CLICK_AIR))
            return;
        if (e.getHand() == null || e.getHand().equals(EquipmentSlot.OFF_HAND))
            return;

        if (e.getItem() == null)
            return;

        if (!e.getItem().hasItemMeta() || !e.getItem().getItemMeta().hasDisplayName())
            return;

        if (e.getItem().getItemMeta().getDisplayName().contains("Sword of the Infected"))
            spawnInfected();

        if (e.getItem().getItemMeta().getDisplayName().contains("Sword of the Minions"))
            spawnMinion();

        if (e.getItem().getItemMeta().getDisplayName().contains("Sword of the Guards"))
            spawnGuard();

        if (e.getItem().getItemMeta().getDisplayName().contains("Sword of Taunts")) {
            if (counter == 0)
                Bukkit.broadcastMessage("<" + ChatColor.DARK_PURPLE + "Emperor Nouveau" + ChatColor.WHITE + "> " + ChatColor.RED + "WEAK.");
            if (counter == 1)
                Bukkit.broadcastMessage("<" + ChatColor.DARK_PURPLE + "Emperor Nouveau" + ChatColor.WHITE + "> " + ChatColor.RED + "YOU ARE POWERLESS.");
            if (counter == 2)
                Bukkit.broadcastMessage("<" + ChatColor.DARK_PURPLE + "Emperor Nouveau" + ChatColor.WHITE + "> " + ChatColor.RED + "HAHAHA.");
            if (counter == 3)
                Bukkit.broadcastMessage("<" + ChatColor.DARK_PURPLE + "Emperor Nouveau" + ChatColor.WHITE + "> " + ChatColor.RED + "YOU CAN'T WIN.");
            if (counter == 4)
                Bukkit.broadcastMessage("<" + ChatColor.DARK_PURPLE + "Emperor Nouveau" + ChatColor.WHITE + "> " + ChatColor.RED + "JUST GIVE UP.");
            ++counter;
            if (counter >= 5)
                counter = 0;
        }
    }
    public void spawnInfected () {
        for (Block b : main.getSpawners("red"))
            CustomSpawns.spawnCryptoid(b.getLocation());
    }
    public void spawnMinion () {
        for (Block b : main.getSpawners("purple"))
            CustomSpawns.spawnNouveauMinion(b.getLocation());
    }
    public void spawnGuard () {
        for (Block b : main.getSpawners("black"))
            CustomSpawns.spawnGuard(b.getLocation());
    }
}
