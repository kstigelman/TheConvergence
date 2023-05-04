package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;

public class Mister8Bit extends ShopNPC {
    private static final int FISH_AMOUNT = 500;

    private class FishBucket extends StigglesBaseItem {
        public FishBucket (int price) {
            super (price);
        }

        @Override
        public ItemProvider getItemProvider() {
            if (ri % 7 == 0)
                return new ItemBuilder(Material.PUFFERFISH_BUCKET).addLoreLines(this.getCost());
            return new ItemBuilder(Material.TROPICAL_FISH_BUCKET).addLoreLines(this.getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }
    private class Coral extends StigglesBaseItem {
        public Coral (int price) {
            super (price);

            Material material;
            int n = ri % 10;
            if (n == 0)
                material = Material.BRAIN_CORAL_FAN;
            else if (n == 1)
                material = Material.BUBBLE_CORAL_FAN;
            else if (n == 2)
                material = Material.HORN_CORAL_FAN;
            else if (n == 3)
                material = Material.FIRE_CORAL_FAN;
            else
                material = Material.TUBE_CORAL_FAN;

            item = new ItemStack(material);
        }
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder (item).addLoreLines(ChatColor.BLUE + "Cost: " + ChatColor.GOLD + cost + " Gold");
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }
    private class Cram extends StigglesBaseItem {
        public Cram (int price) {
            super (price);
            item = new ItemStack(Material.SWEET_BERRIES);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GOLD + "Cram");
            item.setItemMeta(meta);
        }
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }
    private class CoralBlock extends StigglesBaseItem {
        public CoralBlock (int price) {
            super (price);

            Material material;
            int n = ri % 10;
            if (n == 0)
                material = Material.BRAIN_CORAL_BLOCK;
            else if (n == 1)
                material = Material.BUBBLE_CORAL_BLOCK;
            else if (n == 2)
                material = Material.HORN_CORAL_BLOCK;
            else if (n == 3)
                material = Material.FIRE_CORAL_BLOCK;
            else
                material = Material.TUBE_CORAL_BLOCK;

            item = new ItemStack(material);
        }
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }
    private class Sponge extends StigglesBaseItem {
        public Sponge (int price) { super (price); }

        @Override
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.SPONGE).addLoreLines(getCost());
        }
        @Override
        public void handleClick (@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade (player, this);
        }
    }
    private class PrismarineCrystal extends StigglesBaseItem {
        public PrismarineCrystal (int price) {
            super (price);
        }
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.PRISMARINE_CRYSTALS).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }
    private class PrismarineShard extends StigglesBaseItem {

        public PrismarineShard () {
            super ();
        }
        public PrismarineShard (int price) {
            super (price);
        }
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.PRISMARINE_SHARD).addLoreLines(ChatColor.BLUE + "Cost: " + ChatColor.GOLD + cost + " Gold");
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }

    private class FishingRod extends StigglesBaseItem {
        public FishingRod () {
            cost = 5000;
            item = getItemProvider().get();
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.FISHING_ROD)
                    .setDisplayName(ChatColor.BLUE + "The Mage's Fishing Rod")
                    .addLoreLines(ChatColor.BLUE + "Cost: " + ChatColor.GOLD + cost + " Gold")
                    .addEnchantment(Enchantment.LURE, 5, true)
                    .addEnchantment(Enchantment.LUCK, 5, true)
                    .addEnchantment(Enchantment.DAMAGE_ALL, 10, true)
                    .addEnchantment(Enchantment.DURABILITY, 5, true);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Locked extends AbstractItem {
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

    public Mister8Bit (SMP5 main, String name, Location location) {
        super (main, name, location);
        setSkin (
                "ewogICJ0aW1lc3RhbXAiIDogMTY3OTk2NjA5NzUyMywKICAicHJvZmlsZUlkIiA6ICJhYTA3ZjM2Mjk0NTM0YzYwODQzMjI4NzAzZTBlMjE3OCIsCiAgInByb2ZpbGVOYW1lIiA6ICJfU2FrdXlhX0l6YXlvaV8iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQ1OTZhM2ZkOGFhZTU5ZmQzMjgzMWM1MzcyOWZkNmMyZmY2ZTU5MDA4MDAzMThkMzA4YmI4ZTAwZWEyNzIyMyIKICAgIH0KICB9Cn0=",
                "woayLucHc6lwWXiGWla9GUjLcq/nhuiyARkk4dNqO+P99L3dc7f5peX1co2rahVOqQ1YyFD0uHMyfE3zVSURa+3QgG9g5hwABl+jQgmy9cgCGM4IGL4mwrjQBBrnbPTAc3NtoFSQRmsJEiFcrH4bjsZvXaxDu56/3cm/0qMEeuE2+1Hw18ti/d8KK/AxIfY5Hg66vMAoQ+n/oJs9JUQfs+rhUqcwR7opUppKnSpqsZQDNd7JgKvyUMDdAKdGyHYUAh0/PN82EbXcVgH6fRevjLYMvB9/qcKi4oku0wHpAQAWnrvzEEPlCVeU9ockT7PCnRx8a0S6/+q5dodHycrXy+U9Y9lPRL2wa9gHzU4UmuzUIftFCnALR2ElhlYycm0Xj4epn818uss4B72nbRK64zT1KafPfI16l+0MobhWTlJTPM9oz10g6KQp+Z3dyTlXFYYWIBWYjgz0vBi6j12R7wMWtyCEHVr9h+bl0gt5/87NgBvJ5MPvGDJcPTgu5oRkjH+N2IJ+MM0zCsjUWhkOmJKbOrYe0aJqyuSrfZZuuno6jamd5arsWhS/msdSzWCcjOBIoFcN4wFmbvJJvJcQfdQjDW4bXiLRqrzj2mZQwVtlgnlILmC1NKX7PXoRIVijozmW3dvjyHmMF18B0Hcfy5FZcQ/+Eq7P2hQFYAco5No="
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
            sendMessage(player, "Hello.");
        else
            sendMessage(player, "Great day for fishing, isn't it?");
    }

    @Override
    public void createGUI(Player player) {
        AbstractItem lockedSlot = new Locked ("Catch " + FISH_AMOUNT + " fish");
        if (player.getStatistic(Statistic.FISH_CAUGHT) >= FISH_AMOUNT)
            lockedSlot = new FishingRod();

        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new FishBucket (100))
                .addIngredient( 'b', new CoralBlock (80))
                .addIngredient( 'c', new Cram (5))
                .addIngredient( 'd', new Sponge (700))
                .addIngredient( 'e', new PrismarineCrystal(40))
                .addIngredient( 'f', new PrismarineShard(40))
                .addIngredient( 'g', lockedSlot)
                .build ();
    }
}
