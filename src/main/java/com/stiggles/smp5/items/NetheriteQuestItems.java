package com.stiggles.smp5.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class NetheriteQuestItems {

    public static ItemStack reinforcedAncientDebris() {
        ItemStack item = new ItemStack(Material.ANCIENT_DEBRIS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Reinforced Ancient Debris");
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.BLUE + "Quest Item",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "A rare stone found in the depths",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "of the Mines of Carmesi, an ancient",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "Netherite mine used by the piglins.",
                String.valueOf(ChatColor.GRAY)));
        meta.setLocalizedName("reinforced_ancient_debris");

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack theEightDebris() {
        ItemStack item = new ItemStack(Material.ANCIENT_DEBRIS, 8);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Reinforced Ancient Debris");
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.BLUE + "Quest Item",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "A rare stone found in the depths",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "of the Mines of Carmesi, an ancient",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "Netherite mine used by the piglins.",
                String.valueOf(ChatColor.GRAY)));
        meta.setLocalizedName("reinforced_ancient_debris");

        item.setItemMeta(meta);

        return item;
    }

    /***
     * This is all things relating to the Hardened Gold from
     * the Netherite Template Quest
     */
    public static ItemStack hardenedGold() {
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Hardened Gold");
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.BLUE + "Quest Item",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "A rare gemstone, mined up from the",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "Mines of Carmesi, that can be used",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "to craft netheite upgrade templates.",
                String.valueOf(ChatColor.GRAY)));
        meta.setLocalizedName("hardened_gold");

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack theEightGold() {
        ItemStack item = new ItemStack(Material.GOLD_INGOT, 8);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Hardened Gold");
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.BLUE + "Quest Item",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "A rare gemstone, mined up from the",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "Mines of Carmesi, that can be used",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "to craft netheite upgrade templates.",
                String.valueOf(ChatColor.GRAY)));
        meta.setLocalizedName("hardened_gold");

        item.setItemMeta(meta);

        return item;
    }

    /***
     * This is all things relating to the Hardened Diamond from
     * the Netherite Template Quest
     */
    public static ItemStack hardenedDiamond() {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Hardened Diamond");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.BLUE + "Quest Item",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "A rare jewel, found from the Mines",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "of Carmesi, that can be used to craft",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "a rare netheite upgrade template.",
                String.valueOf(ChatColor.GRAY)));
        meta.setLocalizedName("hardened_diamond");

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack theFourDiamond() {
        ItemStack item = new ItemStack(Material.DIAMOND, 4);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Hardened Diamond");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.BLUE + "Quest Item",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "A rare jewel, found from the Mines",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "of Carmesi, that can be used to craft",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "a rare netheite upgrade template.",
                String.valueOf(ChatColor.GRAY)));
        meta.setLocalizedName("hardened_diamond");

        item.setItemMeta(meta);

        return item;
    }

    /***
     * This is all things relating to the Toughened Obsidian from
     * the Netherite Template Quest
     */
    public static ItemStack toughenedObsidian() {
        ItemStack item = new ItemStack(Material.OBSIDIAN);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Toughened Obsidian");
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.BLUE + "Quest Item",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "A rare stone, dug up from the",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "Mines of Carmesi, it can be used to craft",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "a rare netherite upgrade template.",
                String.valueOf(ChatColor.GRAY)));
        meta.setLocalizedName("tough_obsidian");

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack theTwoOby() {
        ItemStack item = new ItemStack(Material.OBSIDIAN, 2);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Toughened Obsidian");
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.BLUE + "Quest Item",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "A rare stone, dug up from the",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "Mines of Carmesi, it can be used to craft",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "a rare netherite upgrade template.",
                String.valueOf(ChatColor.GRAY)));
        meta.setLocalizedName("tough_obsidian");

        item.setItemMeta(meta);

        return item;
    }

    /***
     * This is the one thing that relates to the Crystallized Upgrade Template from
     * the Netherite Template Quest
     */
    public static ItemStack questTemplate() {
        ItemStack item = new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Crystallized Upgrade Template");
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.GOLD + "-- SPECIAL ITEM --",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "A template forged from the minerals of",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "the Mines of Carmesi, that can be used",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "to forge Netherite Items",
                String.valueOf(ChatColor.GRAY)));
        meta.setLocalizedName("netherite_quest_upgrade_template");

        item.setItemMeta(meta);

        return item;

    }
}
