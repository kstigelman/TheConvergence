package com.stiggles.smp5.entity.lostMerchant;

import com.stiggles.smp5.items.BAGEL;
import com.stiggles.smp5.items.GrapplingHook;
import com.stiggles.smp5.items.Pickaxes;
import com.stiggles.smp5.items.Swords;
import com.stiggles.smp5.items.armor.PeacesSymphony;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class MerchantListener implements Listener {
    static Map<UUID, Boolean> merchantCheckList = new HashMap<UUID, Boolean>();
    private final SMP5 plugin; // Add a reference to the plugin instance
    InventoryManager inventoryManager = new InventoryManager();
    Pickaxes pickaxes = new Pickaxes();
    Swords swords = new Swords();
    BAGEL bagel = new BAGEL();
    GrapplingHook grapplingHook = new GrapplingHook();
    PeacesSymphony peacesSymphony = new PeacesSymphony();
    private Inventory inv;
    public MerchantListener(SMP5 plugin) {
        this.plugin = plugin;
    }

    public static void resetMerchantCheckMap() {
        merchantCheckList.clear();
    }

    public Inventory getInventory(UUID id) {
        return inventoryManager.getInventoryFromMap(id);
    }

    @EventHandler
    public void onInteraction(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.VILLAGER)) {
            if (e.getRightClicked().isCustomNameVisible()) {
                Player p = e.getPlayer();
                if (e.getRightClicked().getCustomName().equals(ChatColor.AQUA + "Merchant Marketeer")) {
                    Villager m = (Villager) e.getRightClicked();
                    if (merchantCheckList.get(m.getUniqueId()) == null) {
                        if (SMP5.rollNumber(1, 2) == 1) {
                            merchantCheckList.put(m.getUniqueId(), true);
                            inv = inventoryManager.makeInventory(p, 54, ChatColor.GRAY + "Lost Merchant Shop", inv);
                            inventoryManager.setInvMap(m.getUniqueId(), inv);
                            merchantCheckList.put(m.getUniqueId(), true);
                            p.openInventory(inv);
                        } else {
                            merchantCheckList.put(m.getUniqueId(), false);
                            p.sendMessage(ChatColor.RED + "This merchant does not currently have anything in stock! Please try again tomorrow.");
                        }

                    } else if (!merchantCheckList.get(m.getUniqueId())) {
                        p.sendMessage(ChatColor.RED + "This merchant does not currently have anything in stock! Please try again tomorrow.");
                    } else {
                        p.openInventory(inventoryManager.getInventoryFromMap(m.getUniqueId()));
                    }

                } else if (e.getRightClicked().getCustomName().equals(ChatColor.AQUA + "Merchant Representative")) {
                    Villager m = (Villager) e.getRightClicked();
                    p.playSound(p, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                    p.sendMessage(ChatColor.WHITE + "<Merchant Representative> Greetings! I represent the Merchant Coalition, dedicated to helping players and acquiring resources. " +
                            "During your exploration, you'll encounter our many merchants. " +
                            "Don't forget to pay them a visit! Our trades refresh every day, offering new opportunities. \n");
                    new BukkitRunnable() {
                        public void run() {
                            p.playSound(p, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                            p.sendMessage(ChatColor.WHITE + "\n<Merchant Representative> Exciting surprises await you! " +
                                    "Our trades occasionally feature remarkable and exclusive items. Stay alert for our presence and make the most of our offerings.");
                        }
                    }.runTaskLater(plugin, 100);
                    new BukkitRunnable() {
                        public void run() {
                            p.playSound(p, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                            p.sendMessage(ChatColor.WHITE + "\n<Merchant Representative> Here's a list of some of the items we offer: " +
                                    "\n [] Grappling Hook" +
                                    "\n [] Smurfs Handy Tool" +
                                    "\n [] Emerald Blade" +
                                    "\n [] The Entire Peace Armor Set" +
                                    "\n [] Bagels");
                        }
                    }.runTaskLater(plugin, 160);

                    new BukkitRunnable() {
                        public void run() {
                            p.playSound(p, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                            p.sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + "Merchant Representative whispers to you: My workers also change their location every 10 minutes as well! They may not always be in the same spot!");
                        }
                    }.runTaskLater(plugin, 180);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(ChatColor.GRAY + "Lost Merchant Shop") && e.getCurrentItem() != null) {
            e.setCancelled(true);

            if (e.getClickedInventory() == null)
                return;
            ItemStack itemStack = e.getClickedInventory().getItem(e.getSlot());
            Player p = (Player) e.getWhoClicked();
            if (itemStack == null)
                return;
            Material item = e.getClickedInventory().getItem(e.getSlot()).getType();

            /***
             *
             * All Armor Trims Will Be In This Case Until Next Comment
             *
             */

            if (item.equals(Material.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE)) {
                checkItem(p, Material.TRIDENT, 1, Material.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE,
                        1);
            } else if (item.equals(Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE)) {
                checkItem(p, Material.CONDUIT, 1, Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE,
                        1);
            } else if (item.equals(Material.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)) {
                checkItem(p, Material.BAMBOO_DOOR, 57, Material.COAST_ARMOR_TRIM_SMITHING_TEMPLATE,
                        1);
            } else if (item.equals(Material.VEX_ARMOR_TRIM_SMITHING_TEMPLATE)) {
                checkItem(p, Material.TOTEM_OF_UNDYING, 1, Material.VEX_ARMOR_TRIM_SMITHING_TEMPLATE,
                        1);
            } else if (item.equals(Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE)) {
                checkItem(p, Material.TNT, 16, Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE,
                        1);
            } else if (item.equals(Material.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE)) {
                checkItem(p, Material.NETHERITE_SCRAP, 2, Material.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE,
                        1);
            } else if (item.equals(Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE)) {
                checkItem(p, Material.ENDER_EYE, 16, Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE,
                        1);
            } else if (item.equals(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE)) {
                checkItem(p, Material.SCULK_CATALYST, 30, Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE,
                        1);
            } else if (item.equals(Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE)) {
                checkItem(p, Material.STRING, 64, Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE,
                        1);

                /***
                 *
                 * All Minecraft Items Will Be Included Now, Below.
                 *
                 */

            } else if (item.equals(Material.ROTTEN_FLESH)) {
                checkItem(p, Material.EMERALD, 8, Material.ROTTEN_FLESH,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.POISONOUS_POTATO)) {
                checkItem(p, Material.CARROT, 14, Material.POISONOUS_POTATO,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.COBWEB)) {
                checkItem(p, Material.STRING, 5, Material.COBWEB,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.DEAD_BUSH)) {
                checkItem(p, Material.STICK, 9, Material.DEAD_BUSH,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.EMERALD_ORE)) {
                checkItem(p, Material.COOKED_CHICKEN, 8, Material.EMERALD_ORE,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.FEATHER)) {
                checkItem(p, Material.EGG, 3, Material.FEATHER,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.PUFFERFISH)) {
                checkItem(p, Material.COOKED_SALMON, 3, Material.PUFFERFISH,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.SNOW_GOLEM_SPAWN_EGG)) {
                checkItem(p, Material.SNOW_BLOCK, 4, Material.SNOW_GOLEM_SPAWN_EGG,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.RED_MUSHROOM)) {
                checkItem(p, Material.BROWN_MUSHROOM, 3, Material.RED_MUSHROOM,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.LECTERN)) {
                checkItem(p, Material.BOOK, 3, Material.LECTERN,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.INK_SAC)) {
                checkItem(p, Material.STRING, 2, Material.INK_SAC,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.GOLDEN_APPLE)) {
                checkItem(p, Material.GOLD_INGOT, 10, Material.GOLDEN_APPLE,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.GOLDEN_CARROT)) {
                checkItem(p, Material.GOLD_NUGGET, 7, Material.GOLDEN_CARROT,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.ENDER_EYE)) {
                checkItem(p, Material.ENDER_PEARL, 3, Material.ENDER_EYE,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.ENCHANTING_TABLE)) {
                checkItem(p, Material.OBSIDIAN, 2, Material.ENCHANTING_TABLE,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.BOW)) {
                checkItem(p, Material.STRING, 3, Material.BOW,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.ARROW)) {
                checkItem(p, Material.FLINT, 8, Material.ARROW,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.DIAMOND_AXE)) {
                checkItem(p, Material.DIAMOND, 1, Material.DIAMOND_AXE,
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.NETHER_WART)) {
                checkItem(p, Material.BLAZE_POWDER, 3, Material.NETHER_WART,
                        e.getCurrentItem().getAmount());

                /***
                 *
                 * Custom Item "checkItem" functions are below.
                 *
                 */

            } else if (item.equals(Material.PUMPKIN_PIE)) {
                checkCustomItem(p, Material.WHEAT, 5, bagel.getThatBagel(),
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.FISHING_ROD)) {
                checkForGrapplingHook(p, Material.STRING, 128, Material.FISHING_ROD, 1, Material.TRIPWIRE_HOOK, 1,
                        grapplingHook.getHook(), e.getCurrentItem().getAmount());
            } else if (item.equals(Material.NETHERITE_PICKAXE)) {
                checkCustomItem(p, Material.BLUE_WOOL, 41, pickaxes.giveHandyToolPickaxe(),
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.EMERALD)) {
                checkCustomItem(p, Material.EMERALD_BLOCK, 16, swords.getTheEmeraldDagger(),
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.LEATHER_HELMET)) {
                checkCustomItem(p, Material.NETHERITE_INGOT, 1, peacesSymphony.getPeaceHelmet(),
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.LEATHER_CHESTPLATE)) {
                checkCustomItem(p, Material.NETHERITE_INGOT, 1, peacesSymphony.getPeaceChestplate(),
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.LEATHER_LEGGINGS)) {
                checkCustomItem(p, Material.NETHERITE_INGOT, 1, peacesSymphony.getPeaceLeggings(),
                        e.getCurrentItem().getAmount());
            } else if (item.equals(Material.LEATHER_BOOTS)) {
                checkCustomItem(p, Material.NETHERITE_INGOT, 1, peacesSymphony.getPeaceBoots(),
                        e.getCurrentItem().getAmount());
            }
        }
    }

    public void checkItem(Player player, Material materialGiving, int amountGiving,
                          Material materialReceiving, int amountGetting) {

        if (player.getInventory().containsAtLeast(new ItemStack(materialGiving), amountGiving)) {
            player.getInventory().addItem(new ItemStack(materialReceiving, amountGetting));
            player.getInventory().removeItem(new ItemStack(materialGiving, amountGiving));
        } else {
            sendErrorMessage(player);
        }
    }


    public void checkCustomItem(Player player, Material materialGiving1, int amountGiving1,
                                ItemStack itemStackGiving, int amountGetting) {

        if (player.getInventory().containsAtLeast(new ItemStack(materialGiving1), amountGiving1)) {
            player.getInventory().removeItem(new ItemStack(materialGiving1, amountGiving1));
            player.getInventory().addItem(itemStackGiving);

        } else {
            sendErrorMessage(player);
        }
    }

    public void checkForGrapplingHook(Player player, Material materialGiving1, int amountGiving1, Material materialGiving2,
                                      int amountGiving2, Material materialGiving3, int amountGiving3,
                                      ItemStack itemStackGiving, int amountGetting) {
        if (player.getInventory().containsAtLeast(new ItemStack(materialGiving1), amountGiving1) &&
                player.getInventory().containsAtLeast(new ItemStack(materialGiving2), amountGiving2) &&
                player.getInventory().containsAtLeast(new ItemStack(materialGiving3), amountGiving3)) {
            player.getInventory().removeItem(new ItemStack(materialGiving1, amountGiving1));
            player.getInventory().removeItem(new ItemStack(materialGiving2, amountGiving2));
            player.getInventory().removeItem(new ItemStack(materialGiving3, amountGiving3));
            player.getInventory().addItem(itemStackGiving);

        } else {
            sendErrorMessage(player);
        }
    }

    private void sendErrorMessage(Player player) {
        player.sendMessage(ChatColor.RED + "There was an error while attempting to purchase this!");
        player.sendMessage(ChatColor.RED + "[-] You do not have enough resources.");
        player.sendMessage(ChatColor.RED + "[-] The item you have attempted to purchase is null. (Contact Developers)");
    }

}