package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;

public class Baggins extends ShopNPC {
    private class Iron extends StigglesBaseItem {
        public Iron (int price) {
            super (price);
            item = new ItemStack (Material.IRON_INGOT);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Gold extends StigglesBaseItem {
        public Gold (int price) {
            super (price);
            item = new ItemStack(Material.GOLD_INGOT);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Lapis extends StigglesBaseItem {
        public Lapis (int price) {
            super (price);
            item = new ItemStack(Material.LAPIS_LAZULI);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Diamond extends StigglesBaseItem {
        public Diamond (int price) {
            super (price);
            item = new ItemStack(Material.DIAMOND);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Amethyst extends StigglesBaseItem {
        public Amethyst (int price) {
            super (price);
            item = new ItemStack(Material.AMETHYST_SHARD);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Copper extends StigglesBaseItem {
        public Copper (int price) {
            super (price);
            item = new ItemStack(Material.COPPER_INGOT);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Bell extends StigglesBaseItem {
        public Bell (int price) {
            super (price);
            item = new ItemStack(Material.BELL);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    //Other possible items: Concrete, Candles, Frog lights (UNLOCKABLE)
    public Baggins (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4Mjk5NjY4ODcyOCwKICAicHJvZmlsZUlkIiA6ICIwMDc4NzVkOTI0ZWI0ZGMxODUxZGY5MWFiYTdmZjg3MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZXphMkIyVCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yNTY0NGZiOTZlNWEyNDVkOWU5NzEyMWU3ZTlkZDE2OGI2MmM2Y2M5Yzg1MDRiODJmN2RmOTliNmZlMWQxMDZjIgogICAgfQogIH0KfQ==",
                "BATG6yKQJGTgG4Dh8SW291twDyxkSlrNVlzimxKmZ+5enF6+kbXUMzTJoaZ7yEnA48gXJNxWkvyEOmAI6oNeABK1EHHY8IQDcUWDIITNDNAOpkoUdyjsHxXI3ZKVlErcrfN+/sn0v7LumyHT3Ekxi8utiFrJ9lTNT+ZExzQyUdbRUZHXq9q27S2WXzIVuXOEA0Sj1q/hx9JzwE2dOEz6j4azfZFzAVFGjQairbDE4OabhRYmTsgMARBLTFZnw4mYPOmA4HdTV57Kvpe2A75PdPqaDUex8+Ozyb3jd2h0bhDwDEBB1EFlJ7BzkLfZwlWaHgJUkqczfBKCtfC6JpW5Wriu301YefeBrB3SF0GVCByejb4OUZ4tNz/7s/A6+LThHwSvKmeZpKYYLo60udxi5+nrHX3e9MNFwRXaTqHUHTKa4KkDtXBLyZ1CD+Jbv4hMJ7N0SKBAWOXk++2xP301nHq6h1OGu25J4kU8xjfS2vMnAnxPvpXLHJz6FQuJCepaCeDa76/KjLj892cMQWwt2/RgqpoeIHp7S21V5t4mX/CBROrUGdigAzO5LOkPS29hspR7U/CTm0Xxdyu5sN01n5H7SRhw/lKo4t0KvebU8sLf/xYipuSfzoY2y0iF50fJ25PBA5ku1OgCzIugBhGTKqjc3x6qy5TscF6rFfI/SP4="
        );
    }

    @Override
    public boolean handleTrade (Player player, StigglesBaseItem item) {
        if (super.handleTrade(player, item)) {
            sendMessage(player, "Thank you sir.");
            return true;
        }
        sendMessage(player, "Hey, you don't have enough money!");
        return false;
    }

    @Override
    public void interactDialogue(Player player) {
        int n = main.getRandom() % 2;
        if (n == 0)
            sendMessage(player, "Hello!");
        else
            sendMessage(player, "I'm selling some valuables, if you wish!");

        if (player.getName().contains ("Baggins2003")) {
            sendMessage(player, "Wait, if I'm you, and you're me... huh?? Wow, the multiverse is real!");
        }
    }

    @Override
    public void createGUI(Player player) {
        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Copper (7))
                .addIngredient( 'b', new Amethyst (7))
                .addIngredient( 'c', new Iron (30))
                .addIngredient( 'd', new Gold (40))
                .addIngredient( 'e', new Diamond (80))
                .addIngredient( 'f', new Lapis(12))
                .addIngredient( 'g', new Bell (100))
                .build ();
    }
}
