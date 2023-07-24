package com.stiggles.smp5.entity.monsters;

import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import com.stiggles.smp5.items.Pickaxes;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PillagerCastle implements Listener {

    SMP5 main;

    private static boolean active = false;
    public PillagerCastle(SMP5 main) {
        this.main = main;
    }

    public static Cuboid castleRegion = new Cuboid(
        new Location(Bukkit.getWorld("world"), -9, 68, 345),
        new Location(Bukkit.getWorld("world"), -56, 82, 286));


    public static void spawnCastlePillager(Location loc, SMP5 main) {
        Pillager pillager = loc.getWorld().spawn(loc, Pillager.class);
        Attributable mobAt = pillager;
        AttributeInstance attributeHealth = mobAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeHealth.setBaseValue(32);
        pillager.setHealth(32);
        pillager.setPatrolLeader(false);

        new BukkitRunnable() {
            public void run() {
                if (!pillager.isDead()) {
                    for (Entity entity : pillager.getNearbyEntities(20, 20, 20)) {
                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            pillager.setTarget(p);
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(main, 0, 10);
    }
    public static void spawnCastleGuard(Location loc, SMP5 main) {
        Vindicator vindicator = loc.getWorld().spawn(loc, Vindicator.class);
        vindicator.setPatrolLeader(false);

        new BukkitRunnable() {
            public void run() {
                if (!vindicator.isDead()) {
                    for (Entity entity : vindicator.getNearbyEntities(20, 20, 20)) {
                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            vindicator.setTarget(p);
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(main, 0, 10);
    }

    public static void spawnCastleLeader(Location loc, SMP5 main) {
        Evoker evoker = loc.getWorld().spawn(loc, Evoker.class);
        evoker.setPatrolLeader(true);
        evoker.setPersistent(true);
        active = true;
        new BukkitRunnable() {
            public void run() {
                if (!evoker.isDead()) {
                    for (Entity entity : evoker.getNearbyEntities(20, 20, 20)) {
                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            evoker.setTarget(p);
                        }
                    }
                } else {
                    cancel();

                    new BukkitRunnable() {
                        public void run() {
                            active = false;
                        }
                    }.runTaskLater(main, 20 * 300);
                }
            }
        }.runTaskLater(main, 0);
    }
    public static boolean isActive () {
        return active;
    }
    @EventHandler
    public void wololo(EntitySpellCastEvent e){
        if (e.getEntity() instanceof Evoker){
            if (castleRegion.contains(e.getEntity().getLocation())){
                int rng = SMP5.rollNumber(1,4);
                switch (rng) {
                    case 1:
                        e.getEntity().setSpell(Spellcaster.Spell.BLINDNESS);
                        break;
                    case 2:
                        e.getEntity().setSpell(Spellcaster.Spell.DISAPPEAR);
                        break;
                    case 3:
                        e.getEntity().setSpell(Spellcaster.Spell.SUMMON_VEX);
                        break;
                    case 4:
                        e.getEntity().setSpell(Spellcaster.Spell.FANGS);
                        break;
                }
            }
        }
    }

}
