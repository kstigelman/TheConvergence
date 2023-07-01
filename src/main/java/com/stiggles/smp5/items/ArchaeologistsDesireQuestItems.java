package com.stiggles.smp5.items;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.util.Arrays;

public class ArchaeologistsDesireQuestItems {

    public static ItemStack getArchaeologistsHeadlamp() {
        ItemStack item = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(71, 71, 71));
        meta.setDisplayName(ChatColor.WHITE + "Archaeologists Headlamp");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.GRAY + "---- QUEST  ARMOR ----",
                ChatColor.GRAY + "The headlamp used by Archaeologists",
                ChatColor.GRAY + "When wearing, it will glow the area around you."));
        meta.setLocalizedName("archeologists_headlamp");
        ((ArmorMeta) meta).setTrim(new ArmorTrim(TrimMaterial.GOLD, TrimPattern.WARD));
        meta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack getArchaeologistsChestplate() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(48, 38, 28));
        meta.setDisplayName(ChatColor.WHITE + "Archaeologists Chestplate");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.GRAY + "---- QUEST  ARMOR ----",
                ChatColor.GRAY + "The Chestplate used by Archaeologists",
                ChatColor.GRAY + "When wearing with the rest of the set,",
                ChatColor.GRAY + "It will give you Luck 255 while wearing the set."));
        meta.setLocalizedName("archeologists_chestplate");
        ((ArmorMeta) meta).setTrim(new ArmorTrim(TrimMaterial.NETHERITE, TrimPattern.SNOUT));
        meta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getArchaeologistsLeggings() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(48, 38, 28));
        meta.setDisplayName(ChatColor.WHITE + "Archaeologists Pants");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.GRAY + "---- QUEST  ARMOR ----",
                ChatColor.GRAY + "The Pants used by Archaeologists",
                ChatColor.GRAY + "When wearing with the rest of the set,",
                ChatColor.GRAY + "It will give you Luck 255 while wearing the set."));
        meta.setLocalizedName("archeologists_chestplate");
        ((ArmorMeta) meta).setTrim(new ArmorTrim(TrimMaterial.NETHERITE, TrimPattern.SENTRY));
        meta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack getArchaeologistsBoots() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(48, 38, 28));
        meta.setDisplayName(ChatColor.WHITE + "Archaeologists Boots");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.GRAY + "---- QUEST  ARMOR ----",
                ChatColor.GRAY + "The Boots used by Archaeologists",
                ChatColor.GRAY + "When wearing with the rest of the set,",
                ChatColor.GRAY + "It will give you Luck 255 while wearing the set."));
        meta.setLocalizedName("archeologists_chestplate");
        ((ArmorMeta) meta).setTrim(new ArmorTrim(TrimMaterial.NETHERITE, TrimPattern.SNOUT));
        meta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        item.setItemMeta(meta);
        return item;
    }


}
