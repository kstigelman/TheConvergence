/**
 * This class contains everything relating to the metal detector.
 *
 * @author Kael Hufford, Kyler Stigelman
 */

package com.stiggles.smp5.items;

import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.loot.LootTables;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class MetalDetector implements Listener {
    /**
     * The variable being used for the Treasure Chest inventories (To be more efficient)
     */
    //private static Inventory treasureInventory;
    private static HashMap<UUID, Double> cooldownMD;
    /**
     * Has a map of the Chests, as well as their location to assure the right block, that are Treasure Chests
     */
    //HashMap<Chest, Location> treasureBlockByLocation = new HashMap<>();
    /**
     * Has a map of the Chests, paired with the inventory for that chest
     */
    //HashMap<Chest, Inventory> treasureInventoryByBlock = new HashMap<>();
    SMP5 main;

    public MetalDetector(SMP5 main) {
        this.main = main;

    }

    public static void setupCooldown() {
        cooldownMD = new HashMap<>();
    }

    /**
     * Gets the Metal Detector In Mineral Mode
     *
     * @return ItemStack of the Mineral Mode Metal Detector
     */
    public static ItemStack getMineralModeDetector() {
        ItemStack item = new ItemStack(Material.NETHERITE_SHOVEL);
        item.setItemMeta(getMineralMeta());
        return item;
    }

    /**
     * Gets the Metal Detector In Treasure Mode
     *
     * @return ItemStack of the Treasure Mode Metal Detector
     */
    public static ItemStack getTreasureModeDetector() {
        ItemStack item = new ItemStack(Material.NETHERITE_SHOVEL);
        item.setItemMeta(getTreasureMeta());
        return item;
    }

    /**
     * Gets the Metal Detector's ItemMeta in Mineral Mode
     *
     * @return ItemMeta of the Mineral Mode Metal Detector
     */
    private static ItemMeta getMineralMeta() {
        ItemStack item = new ItemStack(Material.NETHERITE_SHOVEL);
        ItemMeta mineralMeta = item.getItemMeta();
        mineralMeta.setUnbreakable(true);
        mineralMeta.setDisplayName(ChatColor.AQUA + "Metal Detector");
        mineralMeta.setLore(Arrays.asList(
                ChatColor.GRAY + " ",
                ChatColor.AQUA + "-- SPECIAL ITEM --",
                ChatColor.GRAY.toString() + ChatColor.BOLD + "Mode: Mineral Mode",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "Left-Click to change mode",
                ChatColor.GRAY + " ",
                ChatColor.GRAY + "In this mode, it looks for ores.",
                ChatColor.GRAY + "When an ore is found, the block will",
                ChatColor.GRAY + "glow for 5 seconds before it stops.",
                ChatColor.GRAY + " ",
                ChatColor.GRAY.toString() + ChatColor.BOLD + "Radius: 3*4*3",
                ChatColor.GRAY + " ",
                ChatColor.GRAY.toString() + ChatColor.BOLD + "Cooldown: 5 Seconds",
                ChatColor.GRAY + " "));
        mineralMeta.setLocalizedName("metal_detector_mineral_mode");
        return mineralMeta;
    }

    /**
     * Gets the Metal Detector's ItemMeta in Treasure Mode
     *
     * @return ItemMeta of the Treasure Mode Metal Detector
     */
    private static ItemMeta getTreasureMeta() {
        ItemStack item = new ItemStack(Material.NETHERITE_SHOVEL);
        ItemMeta treasureMeta = item.getItemMeta();
        treasureMeta.setUnbreakable(true);
        treasureMeta.setDisplayName(ChatColor.AQUA + "Metal Detector");
        treasureMeta.setLore(Arrays.asList(
                ChatColor.GRAY + " ",
                ChatColor.AQUA + "-- SPECIAL ITEM --",
                ChatColor.GRAY.toString() + ChatColor.BOLD + "Mode: Treasure Mode",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "Left-Click to change mode",
                ChatColor.GRAY + " ",
                ChatColor.GRAY + "In this mode, it looks for buried treasures",
                ChatColor.GRAY + "that have loot. When a loot chest is found,",
                ChatColor.GRAY + "it will glow for 5 seconds before not glowing.",
                ChatColor.GRAY + " ",
                ChatColor.GRAY.toString() + ChatColor.BOLD + "Radius: 5*4*5",
                ChatColor.GRAY + " ",
                ChatColor.GRAY.toString() + ChatColor.BOLD + "Cooldown: 10 Seconds",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "(5 Seconds If Nothing Is Found)",
                ChatColor.GRAY + " "));
        treasureMeta.setLocalizedName("metal_detector_treasure_mode");
        return treasureMeta;
    }

    /**
     * Changes the ItemMeta of the ItemStack then sends a message and jingle to the player to notify.
     *
     * @param itemStack The item that is being changed
     *                  must be a metal detector for the
     *                  method to follow through.
     * @param player    The player that is using the metal detector
     */
    private static void changeMode(ItemStack itemStack, Player player) {
        if (isCustom(itemStack)) {
            if (isDetector(itemStack)) {
                if (getMode(itemStack).equals("mineral_mode")) { // Changes from Mineral to Treasure
                    itemStack.setItemMeta(getTreasureMeta());
                    player.playSound(player, Sound.BLOCK_ANVIL_LAND, 1, 2);
                    player.playSound(player, Sound.BLOCK_ANVIL_USE, 1, 2);
                    player.sendMessage(ChatColor.GRAY + "The mode of your metal detector has been changed from:\n" +
                            "[ Mineral Mode ] --> [ Treasure Mode }");

                } else if (getMode(itemStack).equals("treasure_mode")) { // Changes from Treasure to Mineral
                    itemStack.setItemMeta(getMineralMeta());
                    player.playSound(player, Sound.BLOCK_ANVIL_LAND, 1, 2);
                    player.playSound(player, Sound.BLOCK_ANVIL_USE, 1, 2);
                    player.sendMessage(ChatColor.GRAY + "The mode of your metal detector has been changed from:\n" +
                            "[Treasure Mode] --> [Mineral Mode]");
                }
            }
        }


    }

    /**
     * Get the mode of the ItemStack and returns it as an int
     *
     * @param itemStack The item that is being checked
     *                  for what mode it is in.
     * @return int value of mode 1- Mineral | 2- Treasure
     */
    private static String getMode(ItemStack itemStack) {
        if (isCustom(itemStack) && itemStack.getItemMeta().getLocalizedName().contains("metal_detector")) {
            String localizedName = itemStack.getItemMeta().getLocalizedName();
            if (localizedName.endsWith("_mineral_mode") || localizedName.contains("_mineral_mode")) {
                return "mineral_mode";
            } else if (localizedName.endsWith("_treasure_mode") || localizedName.contains("_treasure_mode")) {
                return "treasure_mode";
            }
        }
        return "no_mode";
    }

    /**
     * Tells if the item is the Metal Detector or not
     *
     * @param item The item being tested if it
     *             is the Metal Detector
     * @return True / False based off of item
     */
    private static boolean isDetector(ItemStack item) {
        if (isCustom(item)) {
            return item.getItemMeta().getLocalizedName().contains("metal_detector");
        }
        return false;
    }

    /**
     * Tells if the item is custom or not
     *
     * @param item The item being tested if it
     *             is custom.
     * @return If item is custom
     */
    private static boolean isCustom(ItemStack item) {
        if (item.hasItemMeta()) {
            return item.getItemMeta().hasLocalizedName() && item.getItemMeta().hasLore();
        } else {
            return false;
        }
    }

    /**
     * Adds a cooldown associated with that player regarding Metal Detectors
     *
     * @param player  The player that the cooldown gets assigned to.
     * @param seconds The amount of seconds that the cooldown lasts for.
     */
    private static void setCooldown(Player player, int seconds) {
        double delay = System.currentTimeMillis() + (seconds * 1000L);
        cooldownMD.put(player.getUniqueId(), delay);
    }

    /**
     * Checks if there is an associated with that player regarding Metal Detectors
     *
     * @param player The player that is being checked for a cooldown.
     */
    private static boolean checkCooldown(Player player) {
        return !cooldownMD.containsKey(player.getUniqueId()) || cooldownMD.get(player.getUniqueId()) <= System.currentTimeMillis();
    }

    //TODO --> Put in all the @EventHandlers and make the item work
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Action action = e.getAction();
        Block eventBlock = e.getClickedBlock();
        Player player = e.getPlayer();
        Bukkit.getConsoleSender().sendMessage("1");
        if (checkCooldown(player)) {
            Bukkit.getConsoleSender().sendMessage("2");
            ItemStack mainHandItem = player.getInventory().getItemInMainHand();
            if (isDetector(player.getInventory().getItemInMainHand())) {
                Bukkit.getConsoleSender().sendMessage("3");
                if (action.equals(Action.RIGHT_CLICK_BLOCK)) { // Search
                    Bukkit.getConsoleSender().sendMessage("4");
                    if (getMode(mainHandItem).equals("mineral_mode")) {
                        Bukkit.getConsoleSender().sendMessage("5");
                        if (eventBlock != null) {
                            Bukkit.getConsoleSender().sendMessage("6");
                            Cuboid mineralCuboid = getScanedCuboid(eventBlock.getLocation(), 5, 5);
                            for (Block block : mineralCuboid.getBlocks()) {
                                Bukkit.getConsoleSender().sendMessage("7");

                                if (isMineral(block.getType())) {
                                    Bukkit.getConsoleSender().sendMessage("8");
                                    makeGlowing(block.getLocation());
                                    setCooldown(player, 5);
                                }
                            }
                        }
                    } else if (getMode(mainHandItem).equals("treasure_mode")) {
                        Bukkit.getConsoleSender().sendMessage("9");
                        if (eventBlock != null) {
                            Bukkit.getConsoleSender().sendMessage("10");
                            Cuboid treasureCuboid = getScanedCuboid(eventBlock.getLocation(), 5, 5);
                            if (SMP5.rollNumber(1, 3) == 1) { // Change to 5 if seen to be to OP
                                Bukkit.getConsoleSender().sendMessage("11");
                                Location center = treasureCuboid.getCenter();
                                Location lootLocation = getRandomLocation(center, 5, center.getWorld());
                                makeGlowing(lootLocation);
                                spawnLoot(lootLocation);
                                setCooldown(player, 10);
                            } else {
                                Bukkit.getConsoleSender().sendMessage("12");
                                setCooldown(player, 5);
                            }
                        }

                    }

                } else if (action.equals(Action.LEFT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_AIR)) { //Change mode
                    changeMode(mainHandItem, player);
                    if (e.getClickedBlock() != null && e.getClickedBlock().isEmpty()) {
                        e.getClickedBlock().setType(e.getClickedBlock().getType());
                    }
                }
            }
        } else {
            player.sendMessage(ChatColor.RED + "You are currently on a cooldown with using this, please wait!");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if (isDetector(mainHandItem)) {
            e.setCancelled(true);
        }
    }

    /**
     * Generates random location.
     *
     * @param center The center point of which the random location
     *               is chosen
     * @param range  The range of the number from
     *               the center point
     * @param world  The world in which the location must
     *               be in.
     * @return The random location generated.
     */
    private Location getRandomLocation(Location center, int range, World world) {
        double x = center.getX() + (Math.random() * range * 2 - range);
        double y = center.getY();
        double z = center.getZ() + (Math.random() * range * 2 - range);
        return new Location(world, x, y, z);
    }

    /**
     * Gets the loot of the block being interacted with
     *
     * @param location The location of the block
     *                 given in case of unexpected null.
     */
    private void spawnLoot(Location location) {
        Location chestLocation = location.subtract(0, 1, 0);
        chestLocation.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) chestLocation.getBlock().getState();
        int roll = SMP5.rollNumber(1, 8);
        if (roll <= 3) {
            chest.setLootTable(LootTables.ANCIENT_CITY_ICE_BOX.getLootTable());
            chest.update();
        } else if (roll <= 6) {
            chest.setLootTable(LootTables.BURIED_TREASURE.getLootTable());
            chest.update();
        } else if (roll == 7) {
            chest.setLootTable(LootTables.ANCIENT_CITY.getLootTable());
            chest.update();
        } else if (roll == 8) {
            chest.setLootTable(LootTables.STRONGHOLD_LIBRARY.getLootTable());
            chest.update();
        }
        chest.update(true);

    }

    /**
     * Method in which scans for minerals in that radius
     *
     * @param location The center location for the plugin
     *                 to form the search cuboid out off
     * @param radius   The radius of the cuboid, based off of
     *                 the location provided.
     * @param depth    The amount of blocks on the Y axis that
     *                 will be accounted for in the cuboid, per
     *                 the value given.
     */
    private Cuboid getScanedCuboid(Location location, int radius, int depth) {
        Location minimumCorner = location.subtract(radius, depth, radius);
        Location maximumCorner = location.add(radius, 0, radius);

        return new Cuboid(minimumCorner, maximumCorner);
    }

    /**
     * Checks if the material given can be used on by the Metal Detector
     *
     * @param material The material of a block that this method is checking
     * @return If the material can be used on by the Metal Detector
     */
    private boolean isMineral(Material material) {
        if (material.equals(Material.COAL_ORE) || material.equals(Material.DEEPSLATE_COAL_ORE)) {
            return true;
        } else if (material.equals(Material.DIAMOND_ORE) || material.equals(Material.DEEPSLATE_DIAMOND_ORE)) {
            return true;
        } else if (material.equals(Material.REDSTONE_ORE) || material.equals(Material.DEEPSLATE_REDSTONE_ORE)) {
            return true;
        } else if (material.equals(Material.EMERALD_ORE) || material.equals(Material.DEEPSLATE_EMERALD_ORE)) {
            return true;
        } else if (material.equals(Material.LAPIS_ORE) || material.equals(Material.DEEPSLATE_LAPIS_ORE)) {
            return true;
        } else if (material.equals(Material.NETHER_QUARTZ_ORE)) {
            return true;
        } else if (material.equals(Material.ANCIENT_DEBRIS)) {
            return true;
        } else {
            return material.equals(Material.NETHER_GOLD_ORE);
        }
    }

    /**
     * Spawns an invulnerable and invisible shulker that glows (appearing like a block is glowing)
     *
     * @param location The location that will be set to glow.
     */
    private void makeGlowing(Location location) {
        Shulker shulker = location.getWorld().spawn(location, Shulker.class);
        shulker.setInvulnerable(true);
        shulker.setAI(false);
        shulker.setAware(false);
        shulker.setSilent(true);
        shulker.setCollidable(false);
        shulker.setInvisible(true);
        shulker.setGlowing(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                shulker.remove();
            }
        }.runTaskLater(main, 20 * 5);
    }

}