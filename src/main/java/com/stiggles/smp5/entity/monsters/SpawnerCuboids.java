package com.stiggles.smp5.entity.monsters;

import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class SpawnerCuboids implements Listener {
    int activeCuboid;
    public ArrayList<Cuboid> cuboids;
    public ArrayList<Block> red_spawners;
    public ArrayList<Block> purple_spawners;
    public ArrayList<Block> black_spawners;
    public ArrayList<Player> players;

    SMP5 main;
    public SpawnerCuboids (SMP5 main) {
        this.main = main;

        players = new ArrayList<>();
        cuboids = new ArrayList<>();
        red_spawners = new ArrayList<>();
        purple_spawners = new ArrayList<>();
        black_spawners = new ArrayList<>();

        //Island 0
        cuboids.add(new Cuboid(Objects.requireNonNull(Bukkit.getWorld("testdungeon")), 1000, 95, 1000, 860, 120, 900));
        //Mesa 1
        cuboids.add(new Cuboid(Objects.requireNonNull(Bukkit.getWorld("world")), -610, 110, -1142, -532, 122, -1059));
        //Ruins 2
        cuboids.add(new Cuboid(Objects.requireNonNull(Bukkit.getWorld("world")), -247, 68, 1347, -329, 88, 1277));
        //Smurf 3
        cuboids.add(new Cuboid(Objects.requireNonNull(Bukkit.getWorld("world")), 1269, 84, 1506, 1222, 99, 1467));
        //Final fight 4
        cuboids.add(new Cuboid(Objects.requireNonNull(Bukkit.getWorld("testdungeon")), 845, 93, 921, 805, 93, 961));
        activeCuboid = 0;
        findSpawners();

        new BukkitRunnable() {
            @Override
            public void run() {
                findNearbyPlayers();
            }
        }.runTaskTimer(main, 100, 600);
    }
    public ArrayList<Block> getSpawners(String type) {
        if (type.equals("red"))
            return red_spawners;
        if (type.equals("purple"))
            return purple_spawners;
        else
            return black_spawners;
    }

    @EventHandler
    public void onBlockBreak (BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.RED_CANDLE || e.getBlock().getType() == Material.BLACK_CANDLE || e.getBlock().getType() == Material.PURPLE_CANDLE) {
            for (Cuboid c : cuboids) {
                if (c.contains(e.getBlock().getLocation()))
                    e.setCancelled(true);
            }
        }
    }
    public int getActiveCuboid () {
        return activeCuboid;
    }
    public void setActiveCuboid (int active) {
        if (active >= cuboids.size())
            return;
        activeCuboid = active;
        findSpawners();
    }

    public ArrayList<Player> getNearbyPlayers () {
        return players;
    }
    public int getNearbySize () {
        return players.size();
    }
    public void findNearbyPlayers () {
        if (activeCuboid >= cuboids.size())
            return;

        if (!players.isEmpty())
            players.clear();
        for (Entity e : Objects.requireNonNull(cuboids.get(activeCuboid).getWorld()).getNearbyEntities(cuboids.get(activeCuboid).getCenter(), 100, 100, 100)) {
            if (e instanceof Player)
                players.add((Player) e);
        }

    }
    public void findSpawners () {
        if (activeCuboid >= cuboids.size())
            return;
        red_spawners.clear();
        purple_spawners.clear();
        black_spawners.clear();
        for (Block b : cuboids.get(activeCuboid)) {
            if (b.getType().equals(Material.RED_CANDLE))
                red_spawners.add(b);
            if (b.getType().equals(Material.PURPLE_CANDLE))
                purple_spawners.add(b);
            if (b.getType().equals(Material.BLACK_CANDLE))
                black_spawners.add(b);
        }
    }


}
