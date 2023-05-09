package com.stiggles.smp5.dungeons;

import org.bukkit.Location;
import org.bukkit.World;

public class Field {

    int x, y, z;
    int radius;
    World world;
    public Field (World world, int x, int y, int z, int radius) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.world = world;
    }

    public Location getCenter () {
        return new Location(world, x, y, z);
    }

    public boolean contains (int x, int y, int z) {
        int distance = (int) Math.sqrt((this.x - x)^2 + (this.y - y)^2 + (this.z - z)^2);
        return distance < radius;
    }
    public boolean contains (Location l) {
        if (!this.world.equals(l.getWorld().getName())) return false;
            return this.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ());
    }
}
