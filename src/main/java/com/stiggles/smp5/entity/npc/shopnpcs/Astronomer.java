package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.main.SMP5;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;

public class Astronomer extends ShopNPC {
    private class ShulkerShell extends StigglesBaseItem {
        public ShulkerShell (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.SHULKER_SHELL);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Endstone extends StigglesBaseItem {
        public Endstone (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.END_STONE);
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
    private class LunarBoots extends StigglesBaseItem {
        public LunarBoots (int price, String localName) {
            super (price, localName);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.IRON_BOOTS)
                    .setDisplayName(ChatColor.AQUA + "Lunar Boots")
                    .addLoreLines(getCost())
                    .addEnchantment(Enchantment.PROTECTION_FALL, 13, true);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Locked extends AbstractItem {
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                    .setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED")
                    .addLoreLines(ChatColor.RED + "Investigate the crater in the mesa");
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
        }
    }

    public Astronomer (SMP5 main, String name, Location location) {
        super (main, name, location);
        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4MTcwMDMxODkyOSwKICAicHJvZmlsZUlkIiA6ICJkYmE4OTUzOThiYTc0MzZlOTQ2YzVkZTk4N2UzZGVkNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTb21lQ29tbW9uIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNhNjIxN2I0ZTQ2NDg3YmRhYThkMmRjMGJlMTE4OGUyYzc0ZDU0ZmIzMWI2MDY1ZGRhZGVmMzBiMDhjODZkZDIiCiAgICB9CiAgfQp9",
                "XUng2ec0iJbjuHQOaz0387BcooM5eImlo4pE2hdwNA86ZToBrdMK3usS1d6hjuxIgixA/w0zz7++S/fxZ38yh9ZGhsz+7Ya29LtCUxwOT6o+AlZIg0B5cgtgEOEqkgCL6mHWCj3WP0/thZIxOfC1p5wlAGDfF+YOV9nbY1UTia0r2PDzf5fM0E5QZf0+/PLLdHRU+T3jdXwP8gz64hQVC+j4O9dxPRsEjPNpSL+GEXyQhIeLpeDFrQQfzvgnuH+4aqaIPvCMIRR9zLjsYrRm12tfr8ByESsV4OZxTFbzJZs10i0vIdwK2gxJObkxYM90lbdUGhDy85FFoO0DdIWZvCTneNCHwnZ8SqTArbsd6Tpy85xucTcy+dDjIkzWaFz6t/G7+/V7uGnVoGJYkMmkedK9JC3qiHkAlXvzcGiFhwgfE0pFWh/Fit9SC7+9vn2w5knM7f+AT5Coo0o0PQ3A8HZihgxOMiaJmXMtvufmlalvOjelxVszM2gr/hQwC0QcAdSatGBU3qF94W/OAw/OpttolYm2wHRCWbjN8MQtq3+pcICjL0BkN+dL6kLrRcvKG0Kqm33d0s2YR70Vrblp8WPOn8+AcZw6AgW4rA5GdhtDRnmz/HyooEQq8EUn7Cmi2qXeoY1ZPyWHapMPye9UXuANcsoT29WKkj7RmBmNOD0="
        );
        //setPos (-0.5, -59, 10);

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
            sendMessage(player, "Isn't the night sky just so fascinating?");
        else
            sendMessage(player, "Welcome to my observatory.");
        talk (player);
    }

    @Override
    public void createGUI(Player player) {
        AbstractItem lockedSlot = new Locked ();
        if (player.getStatistic(Statistic.FISH_CAUGHT) >= 1000)
            lockedSlot = new LunarBoots (3500, "lunar_boots");

        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Endstone (40))
                .addIngredient( 'b', new PurpurBlock(30))
                .addIngredient( 'c', new ChorusFruit(85))
                .addIngredient( 'd', new ShulkerShell(1000))
                .addIngredient( 'e', new EndRod(75))
                .addIngredient( 'f', new DragonHead(2000))
                .addIngredient( 'g', lockedSlot)
                .build ();
    }
}
