package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import de.studiocode.invui.gui.builder.GUIBuilder;
import de.studiocode.invui.gui.builder.guitype.GUIType;
import de.studiocode.invui.item.ItemProvider;
import de.studiocode.invui.item.builder.ItemBuilder;
import de.studiocode.invui.item.impl.BaseItem;
import de.studiocode.invui.item.impl.SimpleItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

public class Mister8Bit extends ShopNPC {
    /* Set a new random integer "seed" on every instance.
     * Server is planned to restart daily, so the NPC's
     * shop will update daily.
     */
    Random random = new Random();
    int randInt = random.nextInt();

    private class FishBucket extends StigglesBaseItem {
        public FishBucket () {
            this (0);
        }
        public FishBucket (int price) {
            super (0);
        }

        @Override
        public ItemProvider getItemProvider() {
            if (randInt % 7 == 0)
                return new ItemBuilder(Material.PUFFERFISH_BUCKET);
            return new ItemBuilder(Material.TROPICAL_FISH_BUCKET);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }
    private class Coral extends StigglesBaseItem {
        public Coral () {
            this (0);
        }
        public Coral (int price) {
            super (0);

            Material material;
            int n = randInt % 10;
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
            return new ItemBuilder (item);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }
    private class CoralFan extends StigglesBaseItem {
        public CoralFan () {
            this (0);
        }
        public CoralFan (int price) {
            super (0);

            Material material;
            int n = randInt % 10;
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
            return new ItemBuilder(item);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }
    private class CoralBlock extends StigglesBaseItem {
        public CoralBlock () {
            this (0);
        }
        public CoralBlock (int price) {
            super (price);

            Material material;
            int n = randInt % 10;
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
            return new ItemBuilder(item);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }
    private class PrismarineCrystal extends StigglesBaseItem {

        public PrismarineCrystal () {
            super ();
        }
        public PrismarineCrystal (int price) {
            super (price);
        }
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.PRISMARINE_CRYSTALS);
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
            return new ItemBuilder(Material.PRISMARINE_SHARD);
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
                    .addLoreLines ("")
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
    private class Locked extends BaseItem {
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                    .setDisplayName(Arrays.asList(ChatColor.RED, ChatColor.BOLD) + "LOCKED")
                    .addLoreLines(ChatColor.RED + "Catch 1000 fish");
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
        }
    }

    public Mister8Bit (SMP5 main, String name) {
        super (main, name);
        setSkin (
                "ewogICJ0aW1lc3RhbXAiIDogMTY3OTk2NjA5NzUyMywKICAicHJvZmlsZUlkIiA6ICJhYTA3ZjM2Mjk0NTM0YzYwODQzMjI4NzAzZTBlMjE3OCIsCiAgInByb2ZpbGVOYW1lIiA6ICJfU2FrdXlhX0l6YXlvaV8iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQ1OTZhM2ZkOGFhZTU5ZmQzMjgzMWM1MzcyOWZkNmMyZmY2ZTU5MDA4MDAzMThkMzA4YmI4ZTAwZWEyNzIyMyIKICAgIH0KICB9Cn0=",
                "woayLucHc6lwWXiGWla9GUjLcq/nhuiyARkk4dNqO+P99L3dc7f5peX1co2rahVOqQ1YyFD0uHMyfE3zVSURa+3QgG9g5hwABl+jQgmy9cgCGM4IGL4mwrjQBBrnbPTAc3NtoFSQRmsJEiFcrH4bjsZvXaxDu56/3cm/0qMEeuE2+1Hw18ti/d8KK/AxIfY5Hg66vMAoQ+n/oJs9JUQfs+rhUqcwR7opUppKnSpqsZQDNd7JgKvyUMDdAKdGyHYUAh0/PN82EbXcVgH6fRevjLYMvB9/qcKi4oku0wHpAQAWnrvzEEPlCVeU9ockT7PCnRx8a0S6/+q5dodHycrXy+U9Y9lPRL2wa9gHzU4UmuzUIftFCnALR2ElhlYycm0Xj4epn818uss4B72nbRK64zT1KafPfI16l+0MobhWTlJTPM9oz10g6KQp+Z3dyTlXFYYWIBWYjgz0vBi6j12R7wMWtyCEHVr9h+bl0gt5/87NgBvJ5MPvGDJcPTgu5oRkjH+N2IJ+MM0zCsjUWhkOmJKbOrYe0aJqyuSrfZZuuno6jamd5arsWhS/msdSzWCcjOBIoFcN4wFmbvJJvJcQfdQjDW4bXiLRqrzj2mZQwVtlgnlILmC1NKX7PXoRIVijozmW3dvjyHmMF18B0Hcfy5FZcQ/+Eq7P2hQFYAco5No="
        );
    }

    public void handleTrade (Player player, StigglesBaseItem item) {
        if (BankManager.withdraw(player, item.cost)) {
            player.getInventory().addItem(item.getItemProvider().get());
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 1.f, 1.f);
            //sendMessage(player, "Pleasure doing business.");
            return;
        }
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
        //sendMessage(player, "It seems you have enough coins for that.");
    }
    @Override
    public void onInteract(Player player) {
        interactDialogue(player);
        createGUI(player);
        showGUI(player);
    }

    @Override
    public void interactDialogue(Player player) {
        int n = new Random().nextInt();
        if (n % 2 == 0)
            sendMessage(player, "Hello.");
        else
            sendMessage(player, "Great day for fishing, isn't it?");
    }

    @Override
    public void createGUI(Player player) {
        BaseItem lockedSlot = new Locked ();
        if (player.getStatistic(Statistic.FISH_CAUGHT) >= 1000)
            lockedSlot = new FishingRod();

        gui = new GUIBuilder<>(GUIType.NORMAL)
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new FishBucket (100))
                .addIngredient( 'b', new CoralBlock (80))
                .addIngredient( 'c', new Coral (80))
                .addIngredient( 'd', new CoralFan (80))
                .addIngredient( 'e', new PrismarineCrystal(40))
                .addIngredient( 'f', new PrismarineShard(40))
                .addIngredient( 'g', lockedSlot)
                .build ();
    }
}
