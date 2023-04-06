package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import de.studiocode.invui.gui.builder.GUIBuilder;
import de.studiocode.invui.gui.builder.guitype.GUIType;
import de.studiocode.invui.gui.impl.SimpleGUI;
import de.studiocode.invui.item.builder.ItemBuilder;
import de.studiocode.invui.item.impl.BaseItem;
import de.studiocode.invui.item.impl.SimpleItem;
import de.studiocode.invui.window.impl.single.SimpleWindow;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class ShopNPC extends StigglesNPC {

    public abstract class StigglesBaseItem extends BaseItem {
        public int cost;
        public ItemStack item;

        public StigglesBaseItem () {
            this (0);
        }
        public StigglesBaseItem (int price) {
            super ();
            this.cost = price;
        }
        public String getCost () {
            return ChatColor.BLUE + "Cost: " + ChatColor.GOLD + cost + " Gold";
        }
    }
    protected SimpleGUI gui;

    public ShopNPC (SMP5 main, String name) {
        super (main, name);
    }
    public ShopNPC (SMP5 main, String name, Location location) {
        super (main, name, location);
    }

    @Override
    public void onInteract (Player player) {
        interactDialogue (player);
        createGUI (player);
        showGUI (player);
    }

    public abstract void createGUI (Player player);

    public void showGUI (Player player) {
        new BukkitRunnable() {
            @Override
            public void run () {
                new SimpleWindow (player, getName(), gui).show();
            }

        }.runTaskLater(SMP5.getPlugin (), 4);
    }

    public void click (Player p, BaseItem item) {

    }


}
