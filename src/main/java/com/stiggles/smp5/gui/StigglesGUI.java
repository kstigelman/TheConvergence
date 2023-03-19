package com.stiggles.smp5.gui;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class StigglesGUI {

    SMP5 main;
    StigglesNPC owner;

    public StigglesGUI (SMP5 main, StigglesNPC owner) {
        this.main = main;
        this.owner = owner;
    }

    public void CreateInventory (Player player) {
        Inventory inv = Bukkit.createInventory(player, 45, ChatColor.RED.toString () + owner.getName());

        // THE BOAT
        ItemStack boat = new ItemStack(Material.ACACIA_BOAT);
        ItemMeta boatMeta = boat.getItemMeta();
        boatMeta.setLore (Arrays.asList("THIS COSTS 20 GOLD"));
        boatMeta.setDisplayName ("The Boat");
        boat.setItemMeta(boatMeta);
        inv.setItem(22, boat);


        player.openInventory(inv);
    }




}
