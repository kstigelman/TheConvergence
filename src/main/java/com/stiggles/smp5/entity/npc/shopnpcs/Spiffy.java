package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import de.studiocode.invui.gui.builder.GUIBuilder;
import de.studiocode.invui.gui.builder.guitype.GUIType;
import de.studiocode.invui.item.ItemProvider;
import de.studiocode.invui.item.builder.ItemBuilder;
import de.studiocode.invui.item.builder.PotionBuilder;
import de.studiocode.invui.item.impl.BaseItem;
import de.studiocode.invui.item.impl.SimpleItem;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Spiffy extends ShopNPC {

    private class Moonshine extends StigglesBaseItem {
        public Moonshine (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new PotionBuilder(PotionBuilder.PotionType.NORMAL)
                    .setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Moonshine")
                    .addLoreLines ("World famous Moonshine from the Spectral Saloon!")
                    .addLoreLines(this.getCost())
                    .addEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 1))
                    .addEffect(new PotionEffect(PotionEffectType.POISON, 200, 1))
                    .addEffect(new PotionEffect(PotionEffectType.LUCK, 60, 1))
                    .addEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 1));
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Sand extends StigglesBaseItem {
        public Sand (int price) {
            super (price);
            Material material;
            int n = ri % 8;
            if (n <= 4)
                material = Material.SAND;
            else
                material = Material.RED_SAND;

            item = new ItemStack(material);
        }
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(this.getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }

    private class Clay extends StigglesBaseItem {
        public Clay (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.TERRACOTTA)
                    .addLoreLines(this.getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class ColoredClay extends StigglesBaseItem {
        public ColoredClay (int price) {
            super (price);
            Material material;
            int n = (ri % 16) + 389;

            material = Material.values()[n];
            item = new ItemStack(material);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item)
                    .addLoreLines(this.getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class GlazedTerracotta extends StigglesBaseItem {
        public GlazedTerracotta (int price) {
            super (price);
            Material material;
            int n = (ri % 16) + 501;
            material = Material.values()[n];
            item = new ItemStack(material);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item)
                    .addLoreLines(this.getCost());
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

    public Spiffy (SMP5 main, String name, Location location) {
        super (main, name, location);
        setSkin (
                "ewogICJ0aW1lc3RhbXAiIDogMTY4MDgwNDc2MDQ5MSwKICAicHJvZmlsZUlkIiA6ICI3ZDJhY2YzOGQ3YTQ0YjU0YTliMGNkYTZhNzk1YmNmYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb3VuY2luZXNzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2EyNWZmMmEwOTU2YWMzYmYzNTBmNDU4YThlNjQyZDkyZGEyMDI3ZDNkMTA4MTYxODE1MjE3NzFiOGRkZWZhNTAiCiAgICB9CiAgfQp9",
                "PfIcTJoUTa0EvNemdy9V4ymTa6hEjQmHwUfnjml8l4XGBJq1JwRTf+pd7Yomc6mGLtlcjzzrQBFtcDs6IBVLqpx4eN3ZOuxobcLCJ2DibSN0sPsOwwMJ2KVXcu4jyVZ1cyC5Rba8aS8DJyWzExw/oO1Gsq+CWKgkSs8cootdrrmdrg2MvIT5BicHOIdHO7sj6lClfOuSFPsVrN/NYbP//91IGNkxFsPzAeB5gljnIOH44X2yjZqv7BqdJ6we9okrCiTDuByQg1I8eHl1D22tdh2Gt8lCvTBwNnQvECsLxTbcGaB3nF/Uno089vvH+VT09daGZiom2Q25bhtnRfg/U13fz5r1etJpHctwsE2vhNewtR7kCL8Vjgp2eJ/QFpk3KIong1q63867tjPEsSXgqS2l4+JJBVb7W91hIzOoV785zoRlBkXs16n2/P/pfw3V+zDinPAeecGJRLgrkB1K/pwffzFL5ACsUUfYP9HSnqAVQ/FZWdBmAV3sHOzPMgZOj8nCjw1BYzd1S7fGt2w6WwQrts+l8z3l2fIzuKnEmlDgQ5nkhVgBpbJJTMKx9mI+n099OT4LgwFE439YM0tYCOn2MuxiVxhEHxFHzyJQ/VSkhYIJxtbc1Fw2METCCihnWnqAdoJvQc2L1YdR33/x+TzEExZaiDMXaJPrH1a+tnc="
        );
    }

    @Override
    public boolean handleTrade (Player player, StigglesBaseItem item) {
        if (super.handleTrade(player, item)) {
            sendMessage(player, "Enjoy.");
            return true;
        }
        sendMessage(player, "Sorry, you don't have enough money for that.");
        return false;
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Welcome! How can I help you?");
    }

    @Override
    public void createGUI(Player player) {
        BaseItem lockedSlot = new Locked("Find the Morabito's hideout.");
        /* if (player has visited morabito hideout)
            lockedSlot = new Dagger (1000);*/

        gui = new GUIBuilder<>(GUIType.NORMAL)
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Moonshine(50))
                .addIngredient( 'b', new Sand (10))
                .addIngredient( 'c', new Clay (10))
                .addIngredient( 'd', new ColoredClay(15))
                .addIngredient( 'e', new GlazedTerracotta(30))
                .addIngredient( 'f', new Locked ("To be added"))
                .addIngredient( 'g', lockedSlot)
                .build ();
    }
}
