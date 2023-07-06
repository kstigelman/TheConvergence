package com.stiggles.smp5.entity.monsters;

import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import com.stiggles.smp5.entity.Entities;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CustomSpawns {
    private static final SMP5 main = SMP5.getPlugin(SMP5.class);
    public static boolean blazingBeastSpawned = false;

    public static void spawnTheBeast() {

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

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().getName().equals("world_nether")) {
                p.sendMessage(ChatColor.RED + "The Blazing Beast has spawned! Take this opportunity to acquire a rare and unique custom item!");
                p.playSound(p, Sound.ENTITY_ENDER_DRAGON_AMBIENT, 10, .1F);
                p.playSound(p, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 10, .01F);
                p.playSound(p, Sound.ENTITY_ENDER_DRAGON_GROWL, 10, .1F);
            }
        }

        Entities.spawnBlazingBeast(new Location(Bukkit.getWorld("world_nether"), 96, 206, 225));
        blazingBeastSpawned = true;
    }

    public static void snowySpawn() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().getBiome(p.getLocation()).equals(Biome.SNOWY_BEACH) ||
                    p.getWorld().getBiome(p.getLocation()).equals(Biome.SNOWY_PLAINS) ||
                    p.getWorld().getBiome(p.getLocation()).equals(Biome.SNOWY_SLOPES) ||
                    p.getWorld().getBiome(p.getLocation()).equals(Biome.SNOWY_TAIGA)) {

                if (Math.abs(main.getRandom()) % 4 == 0)
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

    public static void spawnWitherSkeleton() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            //We only want to spawn these in the nether
            if (!p.getWorld().getName().equals("world_nether")) {
                break;
            }
            Location location = p.getLocation();

            /*if (!location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1, location.getBlockZ()).getType().equals(Material.NETHER_BRICK)) {
                break;
            }*/
            if (Math.abs(main.getRandom()) % 2 == 0) {
                break;
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
                break;
            }
            Bukkit.getConsoleSender().sendMessage("Spawning");
            Bukkit.getWorld(p.getWorld().getName()).spawnEntity(highestRandomLocation, EntityType.WITHER_SKELETON);
        }
    }

    private static void groupLighning(Location location) {
        new BukkitRunnable() {
            public void run() {
                location.getWorld().strikeLightning(location);
            }
        }.runTaskLater(main, 15);
    }

    public void startCountForBlazingBeast() {
        Cuboid beastTower = new Cuboid(
                new Location(Bukkit.getWorld("world_nether"), 99, 206, 228),
                new Location(Bukkit.getWorld("world_nether"), 93, 206, 222));
        if (!blazingBeastSpawned) {
            for (Block block : beastTower.getBlocks()) {
                block.setType(Material.BARRIER);
            }
            new BukkitRunnable() {
                public void run() {
                    for (Block block : beastTower.getBlocks()) {
                        block.setType(Material.AIR);
                    }
                }
            }.runTaskLater(main, 20 * (60 * 25));
            new BukkitRunnable() {
                public void run() {
                    spawnTheBeast();
                }
            }.runTaskLater(main, ((20 * (60 * 25)) + 20 * (60L * SMP5.rollNumber(1, 3))));
        }
    }

    public static void spawnCryptoid (Location location) {
        Zombie zombie = location.getWorld().spawn(location, Zombie.class);
        zombie.setCustomName(ChatColor.DARK_RED + "Infected Cryptoid");
        zombie.setCustomNameVisible(true);

        Attributable zombieAt = zombie;
        AttributeInstance attributeDamage = zombieAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance attributeHealth = zombieAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeDamage.setBaseValue(4);
        attributeHealth.setBaseValue(30);
        zombie.setHealth(30);

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skull_meta = (SkullMeta) skull.getItemMeta();
        try {
            PlayerProfile p = Bukkit.createPlayerProfile(UUID.randomUUID());
            PlayerTextures pt = p.getTextures();
            pt.setSkin(new URL("http://textures.minecraft.net/texture/f6f69cc0a2896cba539714cc0a9bc54eee7f680ef86e1c13e7d15354f17ffbd9"
            ));
            p.setTextures(pt);
            skull_meta.setOwnerProfile(p);
        } catch (MalformedURLException e) {

        }
        skull_meta.setDisplayName(ChatColor.DARK_RED + "Infected Cryptoid Skull");
        skull_meta.setLore(Arrays.asList(ChatColor.GRAY + "The Cryptorg Virus was a zombie-like outbreak in the",
                ChatColor.GRAY + "world of DREAD. There is only " + ChatColor.RED + "1 " + ChatColor.GRAY + "known survivor from",
                ChatColor.GRAY + "this universe, a man who goes by the name Captain Beast."));
        skull.setItemMeta(skull_meta);
        zombie.getEquipment().setHelmet(skull);
        zombie.getEquipment().setHelmetDropChance(0.08f);

        ItemStack armor = new ItemStack (Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta leather_meta = (LeatherArmorMeta) armor.getItemMeta();
        leather_meta.setColor(Color.RED);

        armor.setItemMeta(leather_meta);
        zombie.getEquipment().setChestplate(armor);

        armor = new ItemStack (Material.LEATHER_LEGGINGS);
        armor.setItemMeta(leather_meta);
        zombie.getEquipment().setLeggings(armor);

        armor = new ItemStack (Material.LEATHER_BOOTS);
        armor.setItemMeta(leather_meta);
        zombie.getEquipment().setBoots(armor);
    }

    public static void checkCryptoidSpawns () {
        new BukkitRunnable () {
            @Override
            public void run() {
                World world;
                if ((world = Bukkit.getWorld ("world")) == null)
                    return;

                List<LivingEntity> entities = world.getLivingEntities();

                for (LivingEntity e : entities) {
                    if (!(e instanceof Zombie))
                        continue;
                    if (main.getRandom() % 5 != 0)
                        continue;
                    CustomSpawns.spawnCryptoid(e.getLocation());
                    e.remove();
                }
            }
        }.runTaskTimer(main, 60 * 20, 60 * 20);

    }

}
