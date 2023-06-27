package com.stiggles.smp5.entity;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Random;

public class Entities implements Listener {

    private static final SMP5 plugin = SMP5.getPlugin(SMP5.class);


    public static void spawnStrayGroup(Location location) {
        spawnHeadStray(location);
        spawnLookoutStray(location);
        spawnGuard(location, 4);
        spawnGuard(location, 4);
        spawnGuard(location, 4);
        spawnGuard(location, 4);
        spawnGuard(location, 4);

    }

    public static void spawnHeadStray(Location location) {
        Stray mob = location.getWorld().spawn(location, Stray.class);
        mob.setCustomName(ChatColor.LIGHT_PURPLE + "Stray Herald");
        mob.setCustomNameVisible(true);
        Attributable mobAt = mob;
        AttributeInstance attributeDamage = mobAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance attributeHealth = mobAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeDamage.setBaseValue(10);
        attributeHealth.setBaseValue(41);
        mob.setHealth(40);

        mob.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET), true);
        mob.getEquipment().setHelmetDropChance(0);

        mob.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE), true);
        mob.getEquipment().setChestplateDropChance(0);

        mob.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS), true);
        mob.getEquipment().setLeggingsDropChance(0);

        mob.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS), true);
        mob.getEquipment().setBootsDropChance(0);


        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName((ChatColor.WHITE + "Boom Boom Bow"));
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.GOLD + "-- SPECIAL ITEM --",
                ChatColor.GRAY + "The arrows shot from this",
                ChatColor.GRAY + "bow explode on impact!"));
        meta.setLocalizedName("boom_bow");
        bow.setItemMeta(meta);

        mob.getEquipment().setItemInMainHand(bow);
        mob.getEquipment().setItemInMainHandDropChance(50);

        new BukkitRunnable() {
            public void run() {
                if (!mob.isDead()) {
                    if (mob.getTarget() == null) {
                        for (Entity entity : mob.getNearbyEntities(40, 40, 40)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setTarget(p);
                            }
                        }
                        for (Entity entity : mob.getNearbyEntities(5, 2, 5)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setInvulnerable(true);
                                mob.setInvisible(true);
                            } else {
                                mob.setInvisible(false);
                                mob.setInvulnerable(false);
                            }
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);

    }

    public static void spawnLookoutStray(Location location) {
        Stray mob = location.getWorld().spawn(location, Stray.class);
        mob.setCustomName(ChatColor.LIGHT_PURPLE + "Lookout Stray");
        mob.setCustomNameVisible(false);
        Attributable mobAt = mob;
        AttributeInstance attributeDamage = mobAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance attributeHealth = mobAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeDamage.setBaseValue(10);
        attributeHealth.setBaseValue(41);
        mob.setHealth(40);

        mob.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET), true);
        mob.getEquipment().setHelmetDropChance(0);

        mob.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE), true);
        mob.getEquipment().setChestplateDropChance(0);

        mob.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS), true);
        mob.getEquipment().setLeggingsDropChance(0);

        mob.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS), true);
        mob.getEquipment().setBootsDropChance(0);

        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setUnbreakable(false);
        meta.setDisplayName((ChatColor.WHITE + "Recon Bow"));
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.GOLD + "-- SPECIAL ITEM --",
                ChatColor.GRAY + "This bow scans nearby entities",
                ChatColor.GRAY + "within a 10x10x10 block radius",
                ChatColor.GRAY + "then makes them GLOW for 10 seconds"));
        meta.setLocalizedName("glow_bow");
        bow.setItemMeta(meta);

        mob.getEquipment().setItemInMainHand(bow);
        mob.getEquipment().setItemInMainHandDropChance(50);

        new BukkitRunnable() {
            public void run() {
                if (!mob.isDead()) {
                    if (mob.getTarget() == null) {
                        for (Entity entity : mob.getNearbyEntities(40, 40, 40)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setTarget(p);
                            }
                        }
                        for (Entity entity : mob.getNearbyEntities(5, 2, 5)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setInvulnerable(true);
                                mob.setInvisible(true);
                            } else {
                                mob.setInvisible(false);
                                mob.setInvulnerable(false);
                            }
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);

    }

    public static void spawnGuard(Location center, int range) {
        double x = center.getX() + (Math.random() * range * 2 - range);
        double y = center.getY();
        double z = center.getZ() + (Math.random() * range * 2 - range);
        Location lowRandomLocation = new Location(center.getWorld(), x, y, z);
        y = lowRandomLocation.getWorld().getHighestBlockYAt(lowRandomLocation) + 2;
        Location highRandomLocation = new Location(center.getWorld(), x, y, z);

        Stray mob = highRandomLocation.getWorld().spawn(highRandomLocation, Stray.class);
        mob.setCustomName(ChatColor.LIGHT_PURPLE + "Guard of the Herald");
        mob.setCustomNameVisible(true);
        Attributable mobAt = mob;
        AttributeInstance attributeDamage = mobAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance attributeHealth = mobAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeDamage.setBaseValue(10);
        attributeHealth.setBaseValue(21);
        mob.setHealth(20);

        mob.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE), true);
        mob.getEquipment().setChestplateDropChance(0);

        Random rand = new Random();
        int randomNumber = rand.nextInt(2) + 1;

        if (randomNumber == 1) {
            ItemStack item = new ItemStack(Material.IRON_SWORD);
            mob.getEquipment().setItemInMainHand(item);
        } else {
            ItemStack item = new ItemStack(Material.BOW);
            mob.getEquipment().setItemInMainHand(item);
        }
        mob.getEquipment().setItemInMainHandDropChance(10);

        new BukkitRunnable() {
            public void run() {
                if (!mob.isDead()) {
                    if (mob.getTarget() == null) {
                        for (Entity entity : mob.getNearbyEntities(40, 40, 40)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setTarget(p);
                            }
                        }
                        for (Entity entity : mob.getNearbyEntities(5, 2, 5)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setInvulnerable(true);
                                mob.setInvisible(true);
                                mob.setTarget(p);
                            } else {
                                mob.setInvisible(false);
                                mob.setInvulnerable(false);
                            }
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);
    }

    public static void spawnBlazingBeast(Location loc) {
        MagmaCube blazeIng = loc.getWorld().spawn(loc, MagmaCube.class);
        blazeIng.setCustomName(ChatColor.GOLD + "Blazing Beast");
        blazeIng.setSize(20);
        blazeIng.setCustomNameVisible(true);
        Attributable mobAt = blazeIng;
        AttributeInstance attributeDamage = mobAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance attributeHealth = mobAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeDamage.setBaseValue(50);
        attributeHealth.setBaseValue(750);
        blazeIng.setHealth(750);

        new BukkitRunnable() {
            public void run() {
                if (!blazeIng.isDead()) {
                    for (Entity entity : blazeIng.getNearbyEntities(180, 180, 180)) {
                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            blazeIng.setTarget(p);
                        }
                    }
                    for (Entity entity : blazeIng.getNearbyEntities(20, 180, 20)) {
                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            blazeIng.setTarget(p);
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);
        preformAttacks(blazeIng);
    }

    private static void preformAttacks(MagmaCube blazeIng) {
        new BukkitRunnable() {
            public void run() {
                if (!blazeIng.isDead()) {
                    for (Entity entity : blazeIng.getNearbyEntities(90, 32, 90)) {
                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            blazeIng.setTarget(p);
                            p.getWorld().strikeLightning(p.getLocation());
                            p.getWorld().strikeLightning(p.getLocation());

                            p.getWorld().spawnEntity(p.getLocation(), EntityType.MAGMA_CUBE);
                            p.getWorld().spawnEntity(p.getLocation().add(0, 2, 0), EntityType.BLAZE);
                        }
                    }
                    for (Entity entity : blazeIng.getNearbyEntities(32, 32, 32)) {
                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 1, false, false));
                            p.getWorld().createExplosion(p.getLocation(), 2, false, false);

                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20 * 20);
        new BukkitRunnable() {
            public void run() {
                if (!blazeIng.isDead()) {
                    Location center = blazeIng.getLocation();
                    double range = 30;
                    double x = center.getX() + (Math.random() * range * 2 - range);
                    double y = center.getY();
                    double z = center.getZ() + (Math.random() * range * 2 - range);
                    Location randomLocation = new Location(center.getWorld(), x, y, z);
                    randomLocation.getWorld().strikeLightningEffect(randomLocation);
                    new BukkitRunnable() {
                        public void run() {
                            randomLocation.getWorld().createExplosion(randomLocation, 4, false, false);
                        }
                    }.runTaskLater(plugin, 60);
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 250);
    }


    public static int rollNumber(int min, int max) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(max) + min;

        return randomNumber;
    }

    private boolean nearPlayer(Stray e) {
        for (Entity entity : e.getNearbyEntities(200, 128, 200)) {
            if (entity instanceof Player) {
                return true;
            } else {
                e.remove();
                return false;
            }
        }
        return true;
    }

    private Location getRandomLocation(Location center, double range) {
        double x = center.getX() + (Math.random() * range * 2 - range);
        double y = center.getY();
        double z = center.getZ() + (Math.random() * range * 2 - range);
        Location randomLocation = new Location(center.getWorld(), x, y, z);
        return randomLocation;
    }


}