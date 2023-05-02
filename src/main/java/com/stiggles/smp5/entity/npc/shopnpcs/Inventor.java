package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.main.SMP5;
import de.studiocode.invui.gui.builder.GUIBuilder;
import de.studiocode.invui.gui.builder.guitype.GUIType;
import de.studiocode.invui.item.ItemProvider;
import de.studiocode.invui.item.builder.ItemBuilder;
import de.studiocode.invui.item.impl.BaseItem;
import de.studiocode.invui.item.impl.SimpleItem;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.boss.DragonBattle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class Inventor extends ShopNPC {
    private class Stone extends StigglesBaseItem {
        public Stone (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.STONE);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Redstone extends StigglesBaseItem {
        public Redstone (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.REDSTONE);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class ChorusFruit extends StigglesBaseItem {
        public ChorusFruit (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.CHORUS_FLOWER);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class PurpurBlock extends StigglesBaseItem {
        public PurpurBlock (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.PURPUR_BLOCK);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class EndRod extends StigglesBaseItem {
        public EndRod (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.END_ROD);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class DragonHead extends StigglesBaseItem {
        public DragonHead (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.DRAGON_HEAD);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Elytra extends StigglesBaseItem {
        public Elytra (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.ELYTRA)
                    .setDisplayName(ChatColor.LIGHT_PURPLE + "Wingsuit")
                    .addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Locked extends BaseItem {
        String lore;
        public Locked (String description) {
            lore = description;
        }
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                    .setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED")
                    .addLoreLines(ChatColor.RED + lore);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
        }
    }

    public Inventor (SMP5 main, String name) {
        this (main, name, new Location(Bukkit.getWorld("world"), -0.5, 73, 10));
    }
    public Inventor (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4MTcwMTk0MzM3MSwKICAicHJvZmlsZUlkIiA6ICIxYjcyNzc5ZTdlYzk0Mzk2YTgzNmNkYjNjM2E3MzEwYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJGbGV1cnk4NyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hMjU2OTQ5NTBkZDIxN2RlNTkxNTRkYzkxMGNkMDMyMThmMTMzZWQzNGFmZGYyYTNmMDg1MjVlMjQxMzJmM2M5IgogICAgfSwKICAgICJDQVBFIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzQwYzBlMDNkZDI0YTExYjE1YThiMzNjMmE3ZTllMzJhYmIyMDUxYjI0ODFkMGJhN2RlZmQ2MzVjYTdhOTMzIgogICAgfQogIH0KfQ==",
                "YWfITqMCGO8OMQfhUdn83WHoVwng0qRK9T8ptd8ey7lfR1NTK5Ux41XOHY6viB0orDxs7fmDBs5KM//acWsmyLWuWuPSpf2qdkYWaO3Z5IMrxcO7gEcaUwm7gnYB6kArrtmJn2eZj6PEcNynDMAsZqxKVww0v7nK09ps8w7/ke1B5DmmsaYjuBXhCYB2+c2G+Qmb9RJAiNniDz/N77A8/p3k/7N0BlGQhNxpopbnXDK5PqfSIXuvJrIB/UW4RqNV+1nf7KZYQnlgiQtTlFmBkwosgrbN8YBab5wLbsI7bT2NmO51xYCAnXdt8NYCuoSgQjIeVdRRfja7iqTWYsrIDwhbqh/XmjKfxdq5U8vPVaifuCC/ZsTw8nhBA2ZtL+i6LydUvHHhwPGHl6fxwAZS0jVwFibOgmkwv58VMGHYtQv1NCSAQIW7RNUu00j1O8cmeBh2+BPAG+pekLkk+vUh8hmuWpl+eK/n1rjHRZ3Isv25WC9vfBgi8MFsyzcXXjYKAJLrZNueFBnDti8SzyRfK2sTUx2s0ByG3Yr9OCxKNt/G1aevn9MpguvxVMdClya99X/digOayUoO+lBzxbWe6edSdf5UmMuix8izpfBQkMX82ojN9WHX6VdJqZpjJTOe+FBdmusFWu4beLIbIH1nKj2l8X6RZFlsPqG+hdZXvFs="
        );
    }

    @Override
    public boolean handleTrade (Player player, StigglesBaseItem item) {
        if (super.handleTrade(player, item)) {
            sendMessage(player, "Pleasure doing business.");
            return true;
        }
        sendMessage(player, "It seems you have enough coins for that.");
        return false;
    }

    @Override
    public void interactDialogue(Player player) {
        int n = main.getRandom() % 2;
        if (n == 0)
            sendMessage(player, "Hi there.");
        else
            sendMessage(player, "Welcome to my workshop.");
        talk (player);
    }

    @Override
    public void createGUI(Player player) {
        BaseItem lockedSlot = new Locked ("Kill the Ender Dragon");
        //Also check if player has visited all locations

        //Advancement a = Bukkit.getAdvancement(new NamespacedKey(main, "minecraft:end/kill_dragon"));
        /*if (a != null) {
            if (player.getAdvancementProgress(a).isDone())
                lockedSlot = new Elytra(6464);
        }*/

        gui = new GUIBuilder<>(GUIType.NORMAL)
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Stone (3))
                .addIngredient( 'b', new Redstone(10))
                .addIngredient( 'c', new ChorusFruit(85))
                .addIngredient( 'd', new Elytra (6464))
                .addIngredient( 'e', new EndRod(75))
                .addIngredient( 'f', new DragonHead(2000))
                .addIngredient( 'g', lockedSlot)
                .build ();
    }
}
