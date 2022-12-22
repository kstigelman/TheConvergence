package com.stiggles.smp5.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;

public abstract class StigglesEntity {

    Entity entity;

    public StigglesEntity (Location location)
    {

    }
    public abstract boolean spawn ();
}
