package com.stiggles.smp5.items;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GrapplingHook {

    public ItemStack getHook(){
        ItemStack item = new ItemStack(Material.FISHING_ROD);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(false);
        meta.setDisplayName(ChatColor.AQUA + "Grappling Hook");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.AQUA +  "-- SPECIAL ITEM --",
                ChatColor.GRAY + "When right-clicked, it launches you",
                ChatColor.GRAY + "in the direction that you are",
                ChatColor.GRAY + "facing."));
        meta.setLocalizedName("grappling_hook");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getShopHook(){
        ItemStack item = new ItemStack(Material.FISHING_ROD);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(false);
        meta.setDisplayName(ChatColor.AQUA + "Grappling Hook");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.AQUA +  "-- SPECIAL ITEM --",
                ChatColor.AQUA + "Costs: 64 String & Fishing Rod"));
        meta.setLocalizedName("grappling_hook");
        item.setItemMeta(meta);
        return item;
    }

}
