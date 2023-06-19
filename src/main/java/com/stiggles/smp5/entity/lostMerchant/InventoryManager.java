package com.stiggles.smp5.entity.lostMerchant;

import com.stiggles.smp5.items.BAGEL;
import com.stiggles.smp5.items.GrapplingHook;
import com.stiggles.smp5.items.Pickaxes;
import com.stiggles.smp5.items.Swords;
import com.stiggles.smp5.items.armor.AnarchysWardrobe;
import com.stiggles.smp5.items.armor.LunarArmor;
import com.stiggles.smp5.items.armor.PeacesSymphony;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.logging.Level;

public class InventoryManager {
    Map<UUID, Inventory> merchantList = new HashMap<UUID, Inventory>();

    AnarchysWardrobe anarchysWardrobe = new AnarchysWardrobe();
    PeacesSymphony peacesSymphony = new PeacesSymphony();
    LunarArmor lunarArmor = new LunarArmor();
    GrapplingHook grapplingHook = new GrapplingHook();
    BAGEL bagel = new BAGEL();
    Pickaxes pickaxes = new Pickaxes();
    Swords swords = new Swords();

    public Inventory makeInventory(Player player, int size, String title, Inventory inventory){
        inventory = Bukkit.createInventory(player, size, title);

        return inventory;
    }

    public Inventory addItem(Inventory inventory, ItemStack item, int slot){
        inventory.setItem(slot, item);
        return inventory;
    }

        /*

      ALL FUNCTIONS BELOW ARE FOR HASHMAP OF MERCHANT INVENTORIES , NOT INVENTORIES THEMSELVES

     */

    public void setInvMap(UUID uuid, Inventory inv){
        Random rand = new Random();
        int itemNum = rand.nextInt(6) + 1;
        addItem(inv, getCustomTradeItems(itemNum), 10);
        itemNum = rand.nextInt(10) + 1;
        addItem(inv, getMinecraftTradeItems(itemNum), 12);
        itemNum = rand.nextInt(10) + 1;
        addItem(inv, getMinecraftTradeItems(itemNum), 14);
        itemNum = rand.nextInt(10) + 1;
        addItem(inv, getMinecraftTradeItems(itemNum), 16);
        itemNum = rand.nextInt(10) + 1;
        addItem(inv, getMinecraftTradeItems(itemNum), 28);
        itemNum = rand.nextInt(10) + 1;
        addItem(inv, getMinecraftTradeItemsWithTrims(itemNum), 30);
        itemNum = rand.nextInt(10) + 1;
        addItem(inv, getMinecraftTradeItems(itemNum), 32);
        itemNum = rand.nextInt(10) + 1;
        addItem(inv, getMinecraftTradeItems(itemNum), 34);
        addFrame(inv);
        addCloseButton(inv, 49);

        merchantList.put(uuid, inv);
    }

    private void addCloseButton(Inventory inv, int i) {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(false);
        meta.setDisplayName(ChatColor.RED + "Close Shop");
        item.setItemMeta(meta);
        addItem(inv, item, 49);
    }

    public Map<UUID, Inventory> getMerchantUUIDMap(){
        return merchantList;
    }

    private void addFrame(Inventory inv) {
        ItemStack frame = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta frameMeta = frame.getItemMeta();
        frameMeta.setDisplayName(ChatColor.DARK_GRAY+ " ");
        frame.setItemMeta(frameMeta);

        for(int i : new int[] {0,1,2,3,4,5,6,7,8,9,11,13,15,17,18,19,20,21,22,23,24,25,26,27,29,
                31,33,35,36,37,38,39,40,41,42,43,44,45,46,47,48,50,51,52,53}){
            inv.setItem(i, frame);
        }

    }

    public Inventory getInventoryFromMap(UUID uuid){
        return merchantList.get(uuid);
    }

    /*

      ALL FUNCTIONS BELOW ARE FOR ITEMS BEING ADDED TO THE INVENTORIES, NOT INVENTORIES THEMSELVES

     */
    public ItemStack getCustomTradeItems(int roll){
        if (roll == 1) {
            //Grappling hook
            return grapplingHook.getShopHook();

        } else if (roll == 2) {
            //Emerald Blade
            return swords.getTheShopsEmeraldDagger();
        } else if (roll == 3){
            //Moon Shards
            return lunarArmor.getShoppingMoonShards(SMP5.rollNumber(1,8));
        } else if (roll == 4){

            return new ItemStack(Material.BARRIER);
        } else if (roll == 5){
            //Bagel
            return bagel.getDaShopperBagel();
        } else if (roll == 6){
            //Smurf Handy Tool
            return pickaxes.giveShopTool();
        } else {
            Bukkit.getLogger().log(Level.WARNING,"Please give a value 1<=x<=6!, Value being given: " + roll);
        }
        return lunarArmor.getMoonShards(SMP5.rollNumber(1,5));
    }

    public ItemStack getMinecraftTradeItemsWithTrims(int roll){
        ItemStack item;
        ItemMeta meta;
        switch (roll) {
            case 1:
                item = new ItemStack(Material.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "1 Trident"));
                item.setItemMeta(meta);
                return item;
            case 2:
                item = new ItemStack(Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "1 Conduit"));
                item.setItemMeta(meta);
                return item;
            case 3:
                item = new ItemStack(Material.COAST_ARMOR_TRIM_SMITHING_TEMPLATE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "57 Bamboo Doors"));
                item.setItemMeta(meta);
                return item;
            case 4:
                item = new ItemStack(Material.VEX_ARMOR_TRIM_SMITHING_TEMPLATE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "1 Totem Of Undying"));
                item.setItemMeta(meta);
                return item;
            case 5:
                item = new ItemStack(Material.EMERALD_ORE, SMP5.rollNumber(2,10));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "7 Cooked Chicken"));
                item.setItemMeta(meta);
                return item;
            case 6:
                item = new ItemStack(Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "16 TNT"));
                item.setItemMeta(meta);
                return item;
            case 7:
                item = new ItemStack(Material.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "2 Netherite Scrap"));
                item.setItemMeta(meta);
                return item;
            case 8:
                item = new ItemStack(Material.SNOW_GOLEM_SPAWN_EGG);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "4 Snow blocks"));
                item.setItemMeta(meta);
                return item;
            case 9:
                item = new ItemStack(Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "16 Eyes Of Ender"));
                item.setItemMeta(meta);
                return item;
            case 10:
                item = new ItemStack(Material.LECTERN, SMP5.rollNumber(1,5));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "3 Books"));
                item.setItemMeta(meta);
                return item;
            case 11:
                item = new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "20 Sculk Catalysts"));
                item.setItemMeta(meta);
                return item;
            case 12:
                item = new ItemStack(Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "64 String"));
                item.setItemMeta(meta);
                return item;
            case 13:
                item = new ItemStack(Material.GOLDEN_APPLE, SMP5.rollNumber(5,10));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "10 Golden Ingots"));
                item.setItemMeta(meta);
                return item;
            case 14:
                item = new ItemStack(Material.GOLDEN_CARROT, SMP5.rollNumber(5,10));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "7 Golden Nuggets"));
                item.setItemMeta(meta);
                return item;
            case 15:
                item = new ItemStack(Material.ENDER_EYE, SMP5.rollNumber(1,5));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "6 Ender Pearl"));
                item.setItemMeta(meta);
                return item;
            case 16:
                item = new ItemStack(Material.ENCHANTING_TABLE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "2 Obsidian"));
                item.setItemMeta(meta);
                return item;
            case 17:
                item = new ItemStack(Material.BOW);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "3 String"));
                item.setItemMeta(meta);
                return item;
            case 18:
                item = new ItemStack(Material.ARROW, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "8 Flint"));
                item.setItemMeta(meta);
                return item;
            case 19:
                item = new ItemStack(Material.IRON_AXE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "2 Iron Ingots"));
                item.setItemMeta(meta);
                return item;
            case 20:
                item = new ItemStack(Material.NETHER_WART, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "3 Blaze Powder"));
                item.setItemMeta(meta);
                return item;
            default:
                Bukkit.getLogger().log(Level.WARNING, "Please give a value 1<=x<=20!");
                break;
        }
        item = new ItemStack(Material.EMERALD_ORE, SMP5.rollNumber(2,10));
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "To buy this, it costs",
                ChatColor.AQUA + "7 Cooked Chicken"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getMinecraftTradeItems(int roll){
        ItemStack item;
        ItemMeta meta;
        switch (roll) {
            case 1:
                item = new ItemStack(Material.ROTTEN_FLESH, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "8 Emeralds"));
                item.setItemMeta(meta);
                return item;
            case 2:
                item = new ItemStack(Material.POISONOUS_POTATO, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "14 Carrots"));
                item.setItemMeta(meta);
                return item;
            case 3:
                item = new ItemStack(Material.COBWEB, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "5 String"));
                item.setItemMeta(meta);
                return item;
            case 4:
                item = new ItemStack(Material.DEAD_BUSH, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "9 Sticks"));
                item.setItemMeta(meta);
                return item;
            case 5:
                item = new ItemStack(Material.EMERALD_ORE, SMP5.rollNumber(2,10));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "7 Cooked Chicken"));
                item.setItemMeta(meta);
                return item;
            case 6:
                item = new ItemStack(Material.FEATHER, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "3 Eggs"));
                item.setItemMeta(meta);
                return item;
            case 7:
                item = new ItemStack(Material.PUFFERFISH, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "2 Cooked Salmon"));
                item.setItemMeta(meta);
                return item;
            case 8:
                item = new ItemStack(Material.SNOW_GOLEM_SPAWN_EGG);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "4 Snow blocks"));
                item.setItemMeta(meta);
                return item;
            case 9:
                item = new ItemStack(Material.RED_MUSHROOM, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "6 Brown Mushrooms"));
                item.setItemMeta(meta);
                return item;
            case 10:
                item = new ItemStack(Material.LECTERN, SMP5.rollNumber(1,5));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "3 Books"));
                item.setItemMeta(meta);
                return item;
            case 11:
                item = new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "1 Sculk Catalyst"));
                item.setItemMeta(meta);
                return item;
            case 12:
                item = new ItemStack(Material.INK_SAC, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "2 String"));
                item.setItemMeta(meta);
                return item;
            case 13:
                item = new ItemStack(Material.GOLDEN_APPLE, SMP5.rollNumber(5,10));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "10 Golden Ingots"));
                item.setItemMeta(meta);
                return item;
            case 14:
                item = new ItemStack(Material.GOLDEN_CARROT, SMP5.rollNumber(5,10));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "7 Golden Nuggets"));
                item.setItemMeta(meta);
                return item;
            case 15:
                item = new ItemStack(Material.ENDER_EYE, SMP5.rollNumber(1,5));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "6 Ender Pearl"));
                item.setItemMeta(meta);
                return item;
            case 16:
                item = new ItemStack(Material.ENCHANTING_TABLE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "2 Obsidian"));
                item.setItemMeta(meta);
                return item;
            case 17:
                item = new ItemStack(Material.BOW);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "3 String"));
                item.setItemMeta(meta);
                return item;
            case 18:
                item = new ItemStack(Material.ARROW, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "8 Flint"));
                item.setItemMeta(meta);
                return item;
            case 19:
                item = new ItemStack(Material.IRON_AXE);
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "2 Iron Ingots"));
                item.setItemMeta(meta);
                return item;
            case 20:
                item = new ItemStack(Material.NETHER_WART, SMP5.rollNumber(5,20));
                meta = item.getItemMeta();
                item.setItemMeta(meta);
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY +  "To buy this, it costs",
                        ChatColor.AQUA + "3 Blaze Powder"));
                item.setItemMeta(meta);
                return item;
            default:
                Bukkit.getLogger().log(Level.WARNING, "Please give a value 1<=x<=20!");
                break;
        }
        item = new ItemStack(Material.EMERALD_ORE, SMP5.rollNumber(2,10));
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "To buy this, it costs",
                ChatColor.AQUA + "7 Cooked Chicken"));
        item.setItemMeta(meta);
        return item;
    }
}