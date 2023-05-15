package com.stiggles.smp5.towns;

import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Town {
    private Cuboid cuboid;
    private String name;
    private String description;

    public Town (String name, Cuboid cuboid) {
        this.name = name;
        this.cuboid = cuboid;
    }

    public Town (String name, Cuboid cuboid, String description) {
        this.name = name;
        this.cuboid = cuboid;
        this.description = description;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }
    public void setCuboid (Cuboid cuboid) {
        this.cuboid = cuboid;
    }
    public Cuboid getCuboid () {
        return cuboid;
    }
    public void setDescription (String description) {
        this.description = description;
    }
    public String getDescription () {
        return description;
    }

    public boolean contains (int x, int y, int z) {
        return cuboid.contains(x, y, z);
    }
    public boolean contains (Location location) {
        return cuboid.contains(location);
    }
    public boolean contains (Player player) {
        return cuboid.contains(player.getLocation());
    }
    public void showTitle (Player player) {
        player.sendTitle(ChatColor.GOLD + ChatColor.BOLD.toString () + name, description, 10, 60, 20);
        player.playSound(player.getLocation (), Sound.ENTITY_PLAYER_LEVELUP, 100, 0.f);
    }
}
