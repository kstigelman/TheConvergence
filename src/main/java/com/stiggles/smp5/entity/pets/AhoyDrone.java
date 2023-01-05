package com.stiggles.smp5.entity.pets;


import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Bee;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.entity.Player;

import java.util.List;

public class AhoyDrone extends Bee {

    public AhoyDrone (Location loc, Player player) {
        super (EntityType.BEE, ((CraftWorld) loc.getWorld()).getHandle ());
        this.setPos (loc.getX (), loc.getY (), loc.getZ ());

        this.setInvulnerable(true);
        this.setInvisible(true);

    }


}
