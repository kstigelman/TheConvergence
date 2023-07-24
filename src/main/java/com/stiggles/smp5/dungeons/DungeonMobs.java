package com.stiggles.smp5.dungeons;


import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class DungeonMobs implements Listener {

    private static final SMP5 plugin = SMP5.getPlugin(SMP5.class);
    private static final NamespacedKey necroArrow = new NamespacedKey(plugin, "necromancer_arrow");
    private static final NamespacedKey icedMonarchArrow = new NamespacedKey(plugin, "iced_monarch_arrow");
    private static final NamespacedKey icedMonarchOPArrow = new NamespacedKey(plugin, "iced_monarch_op_arrow");

    private static int rollNumber(int min, int max) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(max) + min;

        return randomNumber;
    }


    public static void spawnDungeonMobs(Location location, String type) {
        if (type.equals("zombie"))
            spawnDungeonZombie(location);
        else if (type.equals("skeleton"))
            spawnDungeonSkeleton(location);
        else if (type.equals("creeper"))
            spawnDungeonCreeper(location);
        else if (type.equals("wSkeleton"))
            spawnDungeonWitherSkelly(location);
    }

    public static void spawnDungeonZombie(Location location) {
        Zombie zombie = location.getWorld().spawn(location, Zombie.class);
        zombie.setCustomName(ChatColor.RED + "Dungeon Zombie");
        zombie.setCustomNameVisible(true);

        Attributable zombieAt = zombie;
        AttributeInstance attributeDamage = zombieAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance attributeHealth = zombieAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeDamage.setBaseValue(10);
        attributeHealth.setBaseValue(20);
        zombie.setHealth(10);

        new BukkitRunnable() {
            public void run() {
                if (!zombie.isDead()) {
                    if (zombie.getTarget() == null) {
                        for (Entity entity : zombie.getNearbyEntities(10, 10, 10)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                zombie.setTarget(p);
                            }
                        }
                    }

                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);
    }

    public static void spawnDungeonSkeleton(Location location) {
        Skeleton mob = location.getWorld().spawn(location, Skeleton.class);
        mob.setCustomName(ChatColor.RED + "Dungeon Skeleton");
        mob.setCustomNameVisible(true);
        Attributable mobAt = mob;
        AttributeInstance attributeDamage = mobAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance attributeHealth = mobAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeDamage.setBaseValue(5);
        attributeHealth.setBaseValue(30);
        mob.setHealth(30);

        new BukkitRunnable() {
            public void run() {
                if (!mob.isDead()) {
                    if (mob.getTarget() == null) {
                        for (Entity entity : mob.getNearbyEntities(20, 20, 20)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setTarget(p);
                            }
                        }
                    }

                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);
    }

    public static void spawnDungeonStray(Location location) {
        Stray mob = location.getWorld().spawn(location, Stray.class);
        mob.setCustomName(ChatColor.RED + "Dungeon Stray");
        mob.setCustomNameVisible(true);
        Attributable mobAt = mob;
        AttributeInstance attributeDamage = mobAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance attributeHealth = mobAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeDamage.setBaseValue(5);
        attributeHealth.setBaseValue(30);
        mob.setHealth(30);

        new BukkitRunnable() {
            public void run() {
                if (!mob.isDead()) {
                    if (mob.getTarget() == null) {
                        for (Entity entity : mob.getNearbyEntities(20, 20, 20)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setTarget(p);
                            }
                        }
                    }

                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);
    }

    public static void spawnDungeonCreeper(Location location) {
        Creeper mob = location.getWorld().spawn(location, Creeper.class);
        mob.setCustomName(ChatColor.RED + "Dungeon Creeper");
        mob.setCustomNameVisible(true);
        Attributable mobAt = mob;
        AttributeInstance attributeDamage = mobAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance attributeHealth = mobAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeDamage.setBaseValue(20);
        attributeHealth.setBaseValue(35);
        mob.setHealth(35);


        new BukkitRunnable() {
            public void run() {
                if (!mob.isDead()) {
                    if (mob.getTarget() == null) {
                        for (Entity entity : mob.getNearbyEntities(20, 20, 20)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setTarget(p);
                            }
                        }
                        for (Entity entity : mob.getNearbyEntities(5, 5, 5)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setTarget(p);
                            }
                        }
                    }

                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);
    }

    public static void spawnDungeonWitherSkelly(Location location) {
        WitherSkeleton mob = location.getWorld().spawn(location, WitherSkeleton.class);
        mob.setCustomName(ChatColor.RED + "Dungeon Wither Skeleton");
        mob.setCustomNameVisible(true);
        Attributable mobAt = mob;
        AttributeInstance attributeDamage = mobAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance attributeHealth = mobAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeDamage.setBaseValue(5);
        attributeHealth.setBaseValue(30);
        mob.setHealth(30);


        new BukkitRunnable() {
            public void run() {
                if (!mob.isDead()) {
                    if (mob.getTarget() == null) {
                        for (Entity entity : mob.getNearbyEntities(20, 20, 20)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setTarget(p);
                            }
                        }
                        for (Entity entity : mob.getNearbyEntities(5, 5, 5)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setTarget(p);
                            }
                        }
                    }

                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);
    }

    public static Zombie spawnKnightBoss(Location location) {
        Zombie boss = location.getWorld().spawn(location, Zombie.class);
        boss.setCustomName(ChatColor.GOLD + "Knight of the Dungeons");
        boss.setCustomNameVisible(true);
        Attributable bossAt = boss;
        AttributeInstance attributeHealth = bossAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeHealth.setBaseValue(60);
        boss.setHealth(60);
        AttributeInstance attributeAttack = bossAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        attributeAttack.setBaseValue(20);
        new BukkitRunnable() {
            public void run() {
                if (!boss.isDead()) {
                    if (boss.getTarget() == null) {
                        for (Entity entity : boss.getNearbyEntities(5, 5, 5)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                boss.setTarget(p);
                                return;
                            }
                        }
                        for (Entity entity : boss.getNearbyEntities(20, 20, 20)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                boss.setTarget(p);
                                return;
                            }
                        }
                    }
                } else {

                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);

        new BukkitRunnable() {
            public void run() {
                int attackNumber = rollNumber(1, 2);
                if (!boss.isDead()) {
                    if (boss.getTarget() != null) {
                        if (boss.getNearbyEntities(20, 20, 20).size() >= 20)
                            return;
                        for (Entity entity : boss.getNearbyEntities(20, 20, 20)) {
                            if (!(entity instanceof Player))
                                return;
                            if (attackNumber >= 2) {
                                if (entity.equals(boss.getTarget())) {
                                    Zombie guard = location.getWorld().spawn(location, Zombie.class);
                                    guard.setCustomName(ChatColor.GOLD + "Guard of the Knight");
                                    guard.setCustomNameVisible(true);

                                    guard.setVelocity(boss.getTarget().getLocation().add(0, 2, 0).subtract(guard.getLocation()).toVector().multiply(0.275));
                                    guard.setTarget(boss.getTarget());
                                }
                            } else {
                                Entity p = entity;
                                p.getLocation().getWorld().strikeLightning(p.getLocation());
                                Blaze blaze = p.getLocation().add(0, 3, 0).getWorld().spawn(p.getLocation().add(0, 3, 0), Blaze.class);
                                Attributable blazeAt = blaze;
                                AttributeInstance attributeHealth = blazeAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                                attributeHealth.setBaseValue(15);
                                blaze.setHealth(15);
                            }
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20 * 10);
        return boss;
    }

    public static WitherSkeleton spawnDreadedNecromancer(Location location) {
        WitherSkeleton boss = location.getWorld().spawn(location, WitherSkeleton.class);
        boss.setCustomName(ChatColor.BLUE + "Dreaded Necromancer");
        boss.setCustomNameVisible(true);
        boss.setCanPickupItems(false);

        boss.getEquipment().clear();
        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        boss.getEquipment().setHelmet(ironHelmet, true);

        Attributable bossAt = boss;
        AttributeInstance attributeHealth = bossAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeHealth.setBaseValue(56);
        boss.setHealth(56);
        AttributeInstance attributeAttack = bossAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        attributeAttack.setBaseValue(2);
        AttributeInstance attributeSpeed = bossAt.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        attributeSpeed.setBaseValue(0.20);

        new BukkitRunnable() {
            public void run() {
                if (!boss.isDead()) {
                    if (boss.getTarget() == null) {
                        for (Entity entity : boss.getNearbyEntities(40, 40, 40)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                boss.setTarget(p);
                            }
                        }
                        for (Entity entity : boss.getNearbyEntities(5, 5, 5)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                boss.setTarget(p);
                            }
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);

        new BukkitRunnable() {
            public void run() {
                int attackNumber = rollNumber(1, 3);
                if (!boss.isDead()) {
                    if (boss.getTarget() != null) {
                        if (attackNumber >= 3) {
                            /*
                            int zombies = rollNumber(1,3);
                            int skeletons = rollNumber(2,4);
                            int creepers = rollNumber(1,3);
                            int witherSkeletons = rollNumber(1,2);
                            SPAWN GROUP OF MOBS
                             */

                            spawnDungeonMobs(boss.getLocation(), "zombie");
                            spawnDungeonMobs(boss.getLocation(), "zombie");
                            spawnDungeonMobs(boss.getLocation(), "zombie");
                            spawnDungeonMobs(boss.getLocation(), "skeleton");
                            spawnDungeonMobs(boss.getLocation(), "skeleton");
                            spawnDungeonMobs(boss.getLocation(), "creeper");
                            spawnDungeonMobs(boss.getLocation(), "creeper");
                            spawnDungeonMobs(boss.getLocation(), "wSkeleton");

                        } else if (attackNumber == 2) {
                            for (Entity arrowAttackPlayer : boss.getNearbyEntities(40, 40, 40)) {
                                Location loc = arrowAttackPlayer.getLocation();
                                Vector dir = loc.getDirection();
                                Arrow arrow = boss.getTarget().getWorld().spawn(loc.add(0, 3, 0), Arrow.class);
                                arrow.getPersistentDataContainer().set(necroArrow, PersistentDataType.STRING, "necromancer_arrow");
                                double speed = 2.5;
                                arrow.setVelocity(dir.multiply(speed));
                            }
                        } else {
                            for (Entity lightningAttackPlayer : boss.getNearbyEntities(40, 40, 40)) {
                                lightningAttackPlayer.getLocation().getWorld().strikeLightning(lightningAttackPlayer.getLocation());
                                lightningAttackPlayer.getLocation().getWorld().createExplosion(
                                        lightningAttackPlayer.getLocation(), 1, false, false);
                            }
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20 * 10);
        return boss;
    }

    public static Stray spawnIcedMonarch(Location location) {
        Stray mob = location.getWorld().spawn(location, Stray.class);
        mob.setCustomName(ChatColor.AQUA + "Iced Monarch");
        mob.setCustomNameVisible(true);
        mob.setCanPickupItems(false);

        mob.getEquipment().clear();
        ItemStack ironHelmet = new ItemStack(Material.GOLDEN_HELMET);
        ItemStack iceBlock = new ItemStack(Material.FROSTED_ICE);
        ItemStack snowBlock = new ItemStack(Material.SNOWBALL);
        mob.getEquipment().setHelmet(ironHelmet, true);
        mob.getEquipment().setItemInMainHand(iceBlock, true);
        mob.getEquipment().setItemInOffHand(snowBlock, true);

        Attributable mobAt = mob;
        AttributeInstance attributeHealth = mobAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeHealth.setBaseValue(90);
        mob.setHealth(90);
        AttributeInstance attributeAttack = mobAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        attributeAttack.setBaseValue(10);
        AttributeInstance attributeSpeed = mobAt.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        attributeSpeed.setBaseValue(.2);

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
                        for (Entity entity : mob.getNearbyEntities(5, 5, 5)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                mob.setTarget(p);
                            }
                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 10);

        new BukkitRunnable() {
            public void run() {
                int attackNumber = rollNumber(1, 7);
                System.out.println(attackNumber);
                if (!mob.isDead()) {
                    if (mob.getTarget() != null) {
                        if (attackNumber >= 7) { // OP ATTACK
                            System.out.println("OP ATTACK");
                            mob.setAI(false);
                            mob.setInvulnerable(true);
                            mob.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20 * 3, 1, true, false, true));
                            mob.setGlowing(true);
                            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                mob.setAI(true);
                                mob.setInvulnerable(false);
                                mob.setGlowing(false);
                                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                    shootGroupOfIce(mob);
                                    shootGroupOfArrows(mob);

                                    // DO ATTACKS
                                }, 9);
                            }, 65);
                        } else if (attackNumber >= 5 && attackNumber <= 6) { //FREEZE ARROWS
                            System.out.println("FREEZE ARROWS");
                            for (Entity arrowAttackPlayer : mob.getNearbyEntities(40, 40, 40)) {
                                Location loc = arrowAttackPlayer.getLocation();
                                Vector dir = loc.getDirection();
                                Arrow arrow = mob.getTarget().getWorld().spawn(loc.add(0, 3, 0), Arrow.class);
                                arrow.getPersistentDataContainer().set(icedMonarchArrow, PersistentDataType.STRING, "iced_monarch_arrow");
                                double speed = 2.5;
                                arrow.setVelocity(dir.multiply(speed));
                            }

                        } else if (attackNumber >= 3 && attackNumber <= 4) { //ICE CHUNKS
                            System.out.println("ICE CHUNKS");
                            /*
                            for (Entity player : mob.getNearbyEntities(40, 40, 40)) {
                                spawnFallingIce(player);
                            }

                             */

                        } else { //STRAY SPAWN
                            System.out.println("STRAY SPAWN");
                            spawnDungeonStray(mob.getLocation());
                            spawnDungeonStray(mob.getLocation());
                            spawnDungeonStray(mob.getLocation());

                        }
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20 * 20);
        return mob;
    }

    public static void spawnFallingIce(Entity p) {
        String worldName = p.getWorld().getName();

        int topX = Math.addExact(2, p.getLocation().getBlockX());
        int topY = Math.addExact(4, p.getLocation().getBlockY());
        int topZ = Math.addExact(2, p.getLocation().getBlockZ());

        int bottomX = Math.subtractExact(2, p.getLocation().getBlockX());
        int bottomY = Math.subtractExact(3, p.getLocation().getBlockY());
        int bottomZ = Math.subtractExact(2, p.getLocation().getBlockZ());

        Cuboid iceChuncks = new Cuboid(
                new Location(Bukkit.getWorld(worldName), topX, topY, topZ),
                new Location(Bukkit.getWorld(worldName), bottomX, bottomY, bottomZ));
        for (Block block : iceChuncks.getBlocks()) {
            if (block.isEmpty()) {
                block.getWorld().spawnFallingBlock(block.getLocation(), Material.BLUE_ICE.createBlockData());
            }
        }
    }

    public static void shootGroupOfIce(Entity entity) {
        shootFallingIce(entity, 1, 0, 2);
        shootFallingIce(entity, -1, 0, 2);
        shootFallingIce(entity, 2, 0, 2);
        shootFallingIce(entity, -2, 0, 2);

        shootFallingIce(entity, 1, 0, 1);
        shootFallingIce(entity, -1, 0, 1);
        shootFallingIce(entity, 2, 0, 1);
        shootFallingIce(entity, -2, 0, 1);

        shootFallingIce(entity, 1, 0, 0);
        shootFallingIce(entity, -1, 0, 0);
        shootFallingIce(entity, 2, 0, 0);
        shootFallingIce(entity, -2, 0, 0);

        shootFallingIce(entity, 0, 0, 1);
        shootFallingIce(entity, 0, 0, -1);
        shootFallingIce(entity, 0, 0, 2);
        shootFallingIce(entity, 0, 0, -2);

        shootFallingIce(entity, 1, 0, -2);
        shootFallingIce(entity, -1, 0, -2);
        shootFallingIce(entity, 2, 0, -2);
        shootFallingIce(entity, -2, 0, -2);

        shootFallingIce(entity, 1, 0, -1);
        shootFallingIce(entity, -1, 0, -1);
        shootFallingIce(entity, 2, 0, -1);
        shootFallingIce(entity, -2, 0, -1);
    }

    public static void shootGroupOfArrows(Entity entity) {
        shootIceArrow(entity, 1, 1, 2);
        shootIceArrow(entity, -1, 1, 2);
        shootIceArrow(entity, 2, 1, 2);
        shootIceArrow(entity, -2, 1, 2);

        shootIceArrow(entity, 1, 1, 1);
        shootIceArrow(entity, -1, 1, 1);
        shootIceArrow(entity, 2, 1, 1);
        shootIceArrow(entity, -2, 1, 1);

        shootIceArrow(entity, 1, 1, 0);
        shootIceArrow(entity, -1, 1, 0);
        shootIceArrow(entity, 2, 1, 0);
        shootIceArrow(entity, -2, 1, 0);

        shootIceArrow(entity, 0, 1, 1);
        shootIceArrow(entity, 0, 1, -1);
        shootIceArrow(entity, 0, 1, 2);
        shootIceArrow(entity, 0, 1, -2);

        shootIceArrow(entity, 1, 1, -2);
        shootIceArrow(entity, -1, 1, -2);
        shootIceArrow(entity, 2, 1, -2);
        shootIceArrow(entity, -2, 1, -2);

        shootIceArrow(entity, 1, 1, -1);
        shootIceArrow(entity, -1, 1, -1);
        shootIceArrow(entity, 2, 1, -1);
        shootIceArrow(entity, -2, 1, -1);
    }

    public static void shootFallingIce(Entity entity, int locationX, int locationY, int locationZ) {
        FallingBlock ice = entity.getWorld().spawnFallingBlock(entity.getLocation(), Material.BLUE_ICE.createBlockData());

        ice.getLocation().setX(locationX);
        ice.getLocation().setZ(locationZ);
        ice.getLocation().setY(locationY);
    }

    public static void shootIceArrow(Entity entity, int vectorX, int vectorY, int vectorZ) {
        Arrow arrow = entity.getWorld().spawn(entity.getLocation(), Arrow.class);
        arrow.getPersistentDataContainer().set(icedMonarchOPArrow, PersistentDataType.STRING, "iced_monarch_op_arrow");
        Vector vector = entity.getLocation().getDirection();

        vector.setX(vectorX);
        vector.setY(vectorY);
        vector.setZ(vectorZ);

        arrow.setVelocity(vector);
    }

    @EventHandler
    public void onArrowAttack(ProjectileHitEvent e) {
        if (e.getHitEntity() != null) {
            if (e.getHitEntity() instanceof Player) {
                Player p = (Player) e.getHitEntity();
                PersistentDataContainer container = e.getEntity().getPersistentDataContainer();
                if (!container.has(necroArrow, PersistentDataType.STRING) ||
                        !container.has(icedMonarchArrow, PersistentDataType.STRING) ||
                        !container.has(icedMonarchOPArrow, PersistentDataType.STRING)) {
                    return;
                }

                if (container.get(necroArrow, PersistentDataType.STRING).equals("necromancer_arrow")) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 * 15, 1, true, false, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 1, true, false, true));

                } else if (container.get(icedMonarchArrow, PersistentDataType.STRING).equals("iced_monarch_arrow")) {
                    //TODO --> ADD THE ICED MONARCH ARROWS
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 10, 1, true, false, true));
                    p.setFreezeTicks(20 * 15);
                } else if (container.get(icedMonarchOPArrow, PersistentDataType.STRING).equals("iced_monarch_op_arrow")) {
                    //TODO --> ADD THE ICED MONARCH ARROWS
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 10, 1, true, false, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 20, 2, true, false, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 10, 1, true, false, true));

                }
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof LightningStrike) {
            for (Entity p : e.getEntity().getNearbyEntities(40, 40, 40)) {
                if (p instanceof Player) {
                    ((Player) p).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 2, true, false, true));
                }
            }
        }
    }

    @EventHandler
    public void onOuchy(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            e.setCancelled(true);
        }
    }
}