package com.stiggles.smp5.dungeons.locations;

import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import org.bukkit.Bukkit;

public class GateCuboids {

    public static Cuboid caveGate = new Cuboid(Bukkit.getWorld("world"), 0, 0, 0, 1, 1, 1);

    public static Cuboid getCaveGate() {
        return caveGate;
    }
}
