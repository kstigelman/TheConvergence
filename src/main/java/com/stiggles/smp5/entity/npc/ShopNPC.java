package com.stiggles.smp5.entity.npc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import org.bukkit.scheduler.BukkitRunnable;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.window.Window;

public abstract class ShopNPC extends StigglesNPC {

    public abstract class StigglesBaseItem extends AbstractItem {
        public int cost;
        public ItemStack item;

        public StigglesBaseItem() {
            this(0);
        }

        public StigglesBaseItem(int price) {
            super();
            this.cost = price;
        }

        public StigglesBaseItem(int price, String localName) {
            this (price);

         }
        public String getCost () {
            return ChatColor.BLUE + "Cost: " + ChatColor.GOLD + cost + " Gold";
        }
    }
    protected Gui gui;

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
        talk (player);
    }

    public abstract void createGUI (Player player);
    public boolean handleTrade (Player player, StigglesBaseItem item) {
        if (BankManager.withdraw(player, item.cost)) {
            player.getInventory().addItem(item.getItemProvider().get());
            playSound (player, Sound.ENTITY_VILLAGER_TRADE);
            return true;
        }
        playSound (player, Sound.ENTITY_VILLAGER_NO);
        return false;
    }
    public void showGUI (Player player) {
        new BukkitRunnable() {
            @Override
            public void run () {
                Window window = Window.single().setViewer (player).setGui (gui).setTitle (getName()).build ();
                window.open ();
            }

        }.runTaskLater(SMP5.getPlugin (), 4);
    }

    public void click (Player p, AbstractItem item) {

    }


}
