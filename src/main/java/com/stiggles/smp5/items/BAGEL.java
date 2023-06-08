package com.stiggles.smp5.items;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class BAGEL {

    public ItemStack getThatBagel() {
        ItemStack item = new ItemStack(Material.PUMPKIN_PIE);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(false);
        meta.setDisplayName(ChatColor.WHITE + "Bagel");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.YELLOW +  "-- SPECIAL ITEM --",
                ChatColor.GRAY + "You eat bagel- yummy",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "Chomp-Chew-Crunch-Chomp-Crunch",
                ChatColor.GRAY + "MMMM- Yummy yummy in my tummy"));
        meta.setLocalizedName("b_a_g_e_l");
        item.setItemMeta(meta);
        return item;
    }
    //BLUEBERRY BAGEL YUM
    public ItemStack getDaShopperBagel() {
        ItemStack item = new ItemStack(Material.PUMPKIN_PIE);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(false);
        meta.setDisplayName(ChatColor.WHITE + "Bagel");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.YELLOW +  "-- SPECIAL ITEM --",
                ChatColor.GRAY + "You eat bagel- yummy",
                ChatColor.GRAY.toString() + ChatColor.ITALIC + "Chomp-Chew-Crunch-Chomp-Crunch",
                ChatColor.AQUA + "Costs: 5 Wheat"));
        meta.setLocalizedName("b_a_g_e_l");
        item.setItemMeta(meta);
        return item;
    }

}
