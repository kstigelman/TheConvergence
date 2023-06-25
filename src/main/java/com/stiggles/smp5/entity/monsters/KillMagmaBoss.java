package com.stiggles.smp5.entity.monsters;

import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.SculkSensor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class KillMagmaBoss implements Listener {

    private static SMP5 main;
    public KillMagmaBoss (SMP5 main) {
        this.main = main;
    }

    CustomSpawns spawns = new CustomSpawns();
    @EventHandler
    public void onKill(EntityDeathEvent e){
        if (e.getEntity().getCustomName() != null && e.getEntity().getCustomName().equals(ChatColor.GOLD + "Blazing Beast") && e.getEntity().getKiller() != null){
            CustomSpawns.blazingBeastSpawned = false;
            if (e.getEntity().getKiller() != null){
                Player p = e.getEntity().getKiller();
                p.sendMessage(ChatColor.GREEN + "The " + ChatColor.GOLD + "Magma Cutlass " + ChatColor.GREEN + "has been dropped as a reward for killing the Blazing Beast!");
                p.playSound(p.getLocation(), Sound.ENTITY_ALLAY_AMBIENT_WITH_ITEM, 10, 1);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, .5F);

                ItemStack cutlass = new ItemStack(Material.MAGMA_CREAM);
                ItemMeta meta = cutlass.getItemMeta();
                meta.setUnbreakable(true);
                meta.setDisplayName(ChatColor.GOLD + "Magma Cutlass");
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "",
                        ChatColor.GOLD +  "-- SPECIAL ITEM --",
                        ChatColor.GRAY + "When right-clicked, it uses 1",
                        ChatColor.GRAY + "experience level to shoot a",
                        ChatColor.GRAY + "flaming arrow in the direction",
                        ChatColor.GRAY + "you are facing.",
                        "",
                        ChatColor.GRAY + "While taking damage by fire,",
                        ChatColor.GRAY + "you will deal significantly more",
                        ChatColor.GRAY + "damage to all entities."));
                meta.setLocalizedName("magma_cutlass");
                cutlass.setItemMeta(meta);

                Bukkit.getWorld("world_nether").dropItemNaturally(e.getEntity().getLocation(), cutlass);

                Cuboid beastTower = new Cuboid(
                        new Location(Bukkit.getWorld("world_nether"), 99, 206, 228),
                        new Location(Bukkit.getWorld("world_nether"), 93, 206, 222));
                for (Block block : beastTower.getBlocks()){
                    block.setType(Material.SOUL_SAND);
                }
                spawns.startCountForBlazingBeast();
                killAfterSpawns(e.getEntity().getKiller());
            }
        }
    }

    private void killAfterSpawns(Entity e) {
        new BukkitRunnable() {
            public void run() {
                for (Entity magma : e.getNearbyEntities(100, 100, 100)) {
                    if (magma instanceof MagmaCube) {
                        magma.remove();
                    }
                }
            }
        }.runTaskLater(main, 4);
        new BukkitRunnable() {
            public void run() {
                for (Entity magma : e.getNearbyEntities(100, 100, 100)) {
                    if (magma instanceof MagmaCube) {
                        magma.remove();
                    }
                }
            }
        }.runTaskLater(main, 6);
        new BukkitRunnable() {
            public void run() {
                for (Entity magma : e.getNearbyEntities(100, 100, 100)) {
                    if (magma instanceof MagmaCube) {
                        magma.remove();
                    }
                }
            }
        }.runTaskLater(main, 8);
        new BukkitRunnable() {
            public void run() {
                for (Entity magma : e.getNearbyEntities(100, 100, 100)) {
                    if (magma instanceof MagmaCube) {
                        magma.remove();
                    }
                }
            }
        }.runTaskLater(main, 10);
        new BukkitRunnable() {
            public void run() {
                for (Entity magma : e.getNearbyEntities(100, 100, 100)) {
                    if (magma instanceof MagmaCube) {
                        magma.remove();
                    }
                }
            }
        }.runTaskLater(main, 12);
        new BukkitRunnable() {
            public void run() {
                for (Entity magma : e.getNearbyEntities(100, 100, 100)) {
                    if (magma instanceof MagmaCube) {
                        magma.remove();
                    }
                }
            }
        }.runTaskLater(main, 14);
    }
}
