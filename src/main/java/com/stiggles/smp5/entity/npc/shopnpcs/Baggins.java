package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;

public class  Baggins extends ShopNPC {
    //Other possible items: Concrete, Candles, Frog lights (UNLOCKABLE)
    public Baggins(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY4Mjk5NjY4ODcyOCwKICAicHJvZmlsZUlkIiA6ICIwMDc4NzVkOTI0ZWI0ZGMxODUxZGY5MWFiYTdmZjg3MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZXphMkIyVCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yNTY0NGZiOTZlNWEyNDVkOWU5NzEyMWU3ZTlkZDE2OGI2MmM2Y2M5Yzg1MDRiODJmN2RmOTliNmZlMWQxMDZjIgogICAgfQogIH0KfQ==",
                "BATG6yKQJGTgG4Dh8SW291twDyxkSlrNVlzimxKmZ+5enF6+kbXUMzTJoaZ7yEnA48gXJNxWkvyEOmAI6oNeABK1EHHY8IQDcUWDIITNDNAOpkoUdyjsHxXI3ZKVlErcrfN+/sn0v7LumyHT3Ekxi8utiFrJ9lTNT+ZExzQyUdbRUZHXq9q27S2WXzIVuXOEA0Sj1q/hx9JzwE2dOEz6j4azfZFzAVFGjQairbDE4OabhRYmTsgMARBLTFZnw4mYPOmA4HdTV57Kvpe2A75PdPqaDUex8+Ozyb3jd2h0bhDwDEBB1EFlJ7BzkLfZwlWaHgJUkqczfBKCtfC6JpW5Wriu301YefeBrB3SF0GVCByejb4OUZ4tNz/7s/A6+LThHwSvKmeZpKYYLo60udxi5+nrHX3e9MNFwRXaTqHUHTKa4KkDtXBLyZ1CD+Jbv4hMJ7N0SKBAWOXk++2xP301nHq6h1OGu25J4kU8xjfS2vMnAnxPvpXLHJz6FQuJCepaCeDa76/KjLj892cMQWwt2/RgqpoeIHp7S21V5t4mX/CBROrUGdigAzO5LOkPS29hspR7U/CTm0Xxdyu5sN01n5H7SRhw/lKo4t0KvebU8sLf/xYipuSfzoY2y0iF50fJ25PBA5ku1OgCzIugBhGTKqjc3x6qy5TscF6rFfI/SP4="
        );
    }

    @Override
    public boolean handleTrade(Player player, StigglesBaseItem item) {
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

        if (player.getName().contains("Baggins2003")) {
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
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('a', new Copper(5))
                .addIngredient('b', new Amethyst(5))
                .addIngredient('c', new Iron(14))
                .addIngredient('d', new Gold(20))
                .addIngredient('e', new Diamond(40))
                .addIngredient('f', new Lapis(7))
                .addIngredient('g', new Bell(70))
                .build();
    }

    public boolean checkQuestItems(Player player) {
        if (Quest.isQuestComplete(player, Quest.QuestName.APPLE_A_DAY))
            return false;

        if (player.getInventory().getItemInMainHand() != null) {
            ItemStack item = player.getInventory().getItemInMainHand();

            if (item.getType().equals(Material.APPLE) && item.getAmount() == 64) {
                item.setAmount(0);
                sendMessage(player, "Oh, thank you so much!");
                Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "Next time those pesky doctors show up at my door, they won't know what hit them... quite literally."), 40);
                Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "Here's the reward I promise you."), 100);
                Bukkit.getScheduler().runTaskLater(main, () -> player.getInventory().addItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE)), 100);
                Bukkit.getScheduler().runTaskLater(main, () -> Quest.questComplete(player, Quest.QuestName.APPLE_A_DAY, "Doctoral Defense", 30), 100);
                return true;
            }
        }
        return false;
    }

    private class Iron extends StigglesBaseItem {
        public Iron(int price) {
            super(price);
            item = new ItemStack(Material.IRON_INGOT);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Gold extends StigglesBaseItem {
        public Gold(int price) {
            super(price);
            item = new ItemStack(Material.GOLD_INGOT);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Lapis extends StigglesBaseItem {
        public Lapis(int price) {
            super(price);
            item = new ItemStack(Material.LAPIS_LAZULI);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Diamond extends StigglesBaseItem {
        public Diamond(int price) {
            super(price);
            item = new ItemStack(Material.DIAMOND);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Amethyst extends StigglesBaseItem {
        public Amethyst(int price) {
            super(price);
            item = new ItemStack(Material.AMETHYST_SHARD);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Copper extends StigglesBaseItem {
        public Copper(int price) {
            super(price);
            item = new ItemStack(Material.COPPER_INGOT);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Bell extends StigglesBaseItem {
        public Bell(int price) {
            super(price);
            item = new ItemStack(Material.BELL);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
}
