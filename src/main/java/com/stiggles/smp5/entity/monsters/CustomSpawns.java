package com.stiggles.smp5.entity.monsters;

import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import com.stiggles.smp5.entity.Entities;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomSpawns {
    public static boolean blazingBeastSpawned = false;
    private static SMP5 main = SMP5.getPlugin(SMP5.class);



    public void startCountForBlazingBeast(){
        Cuboid beastTower = new Cuboid(
                new Location(Bukkit.getWorld("world_nether"), 99, 206, 228),
                new Location(Bukkit.getWorld("world_nether"), 93, 206, 222));
        if (!blazingBeastSpawned) {
            for(Block block : beastTower.getBlocks()){
                block.setType(Material.BARRIER);
            }
            new BukkitRunnable() { public void run() {
                for(Block block : beastTower.getBlocks()){
                    block.setType(Material.AIR);
                }
            }
            }.runTaskLater(main, 20*(60*25));
            new BukkitRunnable() { public void run() {
                    spawnTheBeast();
            }
            }.runTaskLater(main, 20 * (60L * SMP5.rollNumber(1,3)));
        }
    }

    public static void spawnTheBeast(){

        groupLighning(new Location(Bukkit.getWorld("world_nether"), 96, 206, 225));
        groupLighning(new Location(Bukkit.getWorld("world_nether"), 96, 206, 225));
        groupLighning(new Location(Bukkit.getWorld("world_nether"), 96, 206, 225));
        groupLighning(new Location(Bukkit.getWorld("world_nether"), 96, 206, 225));
        groupLighning(new Location(Bukkit.getWorld("world_nether"), 96, 206, 225));
        groupLighning(new Location(Bukkit.getWorld("world_nether"), 96, 206, 225));
        groupLighning(new Location(Bukkit.getWorld("world_nether"), 96, 206, 225));
        groupLighning(new Location(Bukkit.getWorld("world_nether"), 96, 206, 225));
        groupLighning(new Location(Bukkit.getWorld("world_nether"), 96, 206, 225));
        groupLighning(new Location(Bukkit.getWorld("world_nether"), 96, 206, 225));

        for (Player p : Bukkit.getOnlinePlayers()){
            if (p.getWorld().getName().equals("world_nether")) {
                p.sendMessage(ChatColor.RED+"The Blazing Beast has spawned! Take this opportunity to acquire a rare and unique custom item!");
                p.playSound(p, Sound.ENTITY_ENDER_DRAGON_AMBIENT, 10, .1F);
                p.playSound(p, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 10, .01F);
                p.playSound(p, Sound.ENTITY_ENDER_DRAGON_GROWL, 10, .1F);
            }
        }

        Entities.spawnBlazingBeast(new Location(Bukkit.getWorld("world_nether"),96 ,206 ,225));
        blazingBeastSpawned = true;
    }

    public static void snowySpawn() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().getBiome(p.getLocation()).equals(Biome.SNOWY_BEACH) ||
                    p.getWorld().getBiome(p.getLocation()).equals(Biome.SNOWY_PLAINS) ||
                    p.getWorld().getBiome(p.getLocation()).equals(Biome.SNOWY_SLOPES) ||
                    p.getWorld().getBiome(p.getLocation()).equals(Biome.SNOWY_TAIGA)) {

                if (Math.abs (main.getRandom()) % 4 == 0)
                    return;

                Location center = p.getLocation();
                double range = 75;
                double x = center.getX() + (Math.random() * range * 2 - range);
                double y = center.getY();
                double z = center.getZ() + (Math.random() * range * 2 - range);
                Location lowRandomLocation = new Location(center.getWorld(), x, y, z);
                y = lowRandomLocation.getWorld().getHighestBlockYAt(lowRandomLocation) + 2;
                Location highRandomLocation = new Location(center.getWorld(), x, y, z);

                groupLighning(highRandomLocation);
                groupLighning(highRandomLocation);
                groupLighning(highRandomLocation);
                groupLighning(highRandomLocation);
                groupLighning(highRandomLocation);

                new BukkitRunnable() {
                    public void run() {
                        Entities.spawnStrayGroup(highRandomLocation);
                    }
                }.runTaskLater(main, 40);
            }
        }
    }


    public static void spawnWitherSkeleton () {
        for (Player p : Bukkit.getOnlinePlayers()) {
            //We only want to spawn these in the nether
            if (!p.getWorld().getName().equals("world_nether")) {
                break;
            }
            Location location = p.getLocation();
            if (!location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1, location.getBlockZ()).getType().equals(Material.NETHER_BRICK)) {
                break;
            }
            if (Math.abs (main.getRandom()) % 2 == 0) {
                return;
            }

            Location center = p.getLocation();
            double range = 75;
            double x = center.getX() + (Math.random() * range * 2 - range);
            double y = center.getY();
            double z = center.getZ() + (Math.random() * range * 2 - range);
            Location lowRandomLocation = new Location(center.getWorld(), x, y, z);
            y = lowRandomLocation.getWorld().getHighestBlockYAt(lowRandomLocation) + 2;
            Location highestRandomLocation = new Location(center.getWorld(), x, y, z);

            if (!highestRandomLocation.getBlock().getType().equals(Material.NETHER_BRICK)) {
                return;
            }

            Bukkit.getWorld(p.getWorld().getName()).spawnEntity(highestRandomLocation, EntityType.WITHER_SKELETON);
        }
    }

    private static void groupLighning(Location location) {
        new BukkitRunnable() { public void run() {
            location.getWorld().strikeLightning(location);
        }
        }.runTaskLater(main, 15);
    }

}
