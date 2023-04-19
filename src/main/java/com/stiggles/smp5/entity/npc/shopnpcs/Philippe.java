package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.main.SMP5;
import de.studiocode.invui.gui.builder.GUIBuilder;
import de.studiocode.invui.gui.builder.guitype.GUIType;
import de.studiocode.invui.item.ItemProvider;
import de.studiocode.invui.item.builder.ItemBuilder;
import de.studiocode.invui.item.impl.SimpleItem;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Philippe extends ShopNPC {
    private class Veggie extends StigglesBaseItem {
        public Veggie (int price) {
            super (price);
            if (ri % 2 == 0)
                item = new ItemStack(Material.CARROT);
            else
                item = new ItemStack(Material.POTATO);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Seeds extends StigglesBaseItem {
        public Seeds (int price) {
            super (price);
            int n = ri % 3;
            if (ri == 0)
                item = new ItemStack(Material.MELON_SEEDS);
            else if (ri == 1)
                item = new ItemStack(Material.PUMPKIN_SEEDS);
            else
                item = new ItemStack(Material.BEETROOT_SEEDS);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Bones extends StigglesBaseItem {
        public Bones (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.BONE_BLOCK);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Leather extends StigglesBaseItem {
        public Leather (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.LEATHER);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Logs extends StigglesBaseItem {
        public Logs (int price) {
            super (price);
            int n = (ri % 7);

            item = new ItemStack(Material.OAK_LOG);

        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Podzol extends StigglesBaseItem {
        public Podzol (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.PODZOL);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Wool extends StigglesBaseItem {
        public Wool (int price) {
            super (price);
            item = new ItemStack(Material.WHITE_WOOL);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    public Philippe (SMP5 main, String name) {
        this (main, name, new Location(Bukkit.getWorld("world"), -0.5, 73, 10));
    }
    public Philippe (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4MTg3Njc5NDc2MCwKICAicHJvZmlsZUlkIiA6ICIxNDA5ZDAyZjM4ZTU0OGU3YTU1MDFiMTAzMTIxN2Q4OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJQaGlsaXBwZUFsZnJlZCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hMjk3N2RkYjE2ZWQ3OTk1ZDY1NTc5ZTdkMDhhZWZjODQ5YjYxOTE1YzhmZjY4NjRiNmU0NDEyNGM3MjU5NDk4IgogICAgfQogIH0KfQ==",
                "uahiDAf3OOsmNjMGOvayKDP/MkpleXAb9KhiUDxpDvgOO0GI/S5cwIEj6HLw2wK3tOI9N9Zobt4/YCtdJzRJ5Q6jrIuvRwEcHQumxYy55WrqXYkquFFatX5jIr6GfsblXfiw1teRUCC1k+6pP6A9uPlFb9/Vn0VkrcVXk6+PDgqsoRtcLxAIO/FJazFx6xhIWWgEsgi4apwCcALIwDPr7H9tiwkWhUbVEkJVrk+a6dAnv/TjiibVL9vpou4AFTOO3ODeetj8KmFdIfnQpoLv3kgCNqT7VpVwgYWw1MjnS8wg+kQ2IKWYW8icSuYrXxiMd5jNerN4A87ehK/2oLSKXygn3KOyOnHn7cgFiXI6invn1GQhyHJQk4Qso0PCRHURfz2wLY43h4NSva2PfoEUutvJtc3GlWByYPUKzGVDXlhJ1XpNIJRqpYJkMjmTJlCSBUWDocfz7tyupO2NGKVOupwVmGMRXNfoQNAUB9DBUenOpRPJanzPmhgWuAL/tj2Cie9qvWOqBQzw3k3vhJFmF3sQ1l9mLDmnZZAYbHbqNgh0gmvp+ESOuP9zZ1JmTslEHGD239EIuAnGSgLQRsi0MKx39qraDKhlaMUHuyc8+dOmHKEvkoR4zwnna3uiMPAT3cDyGttR35+MiHMmFhgenaV28I9/DX+eSbUVeKqJmDI="
        );
    }

    @Override
    public boolean handleTrade (Player player, StigglesBaseItem item) {
        if (super.handleTrade(player, item)) {
            sendMessage(player, "Ah, merci.");
            return true;
        }
        sendMessage(player, "Désolé. You don't have money for that.");
        return false;
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
            sendMessage(player, "Bonjour");
        else
            sendMessage(player, "Welcome to Holland!");
    }

    @Override
    public void createGUI(Player player) {
        gui = new GUIBuilder<>(GUIType.NORMAL)
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Veggie(2))
                .addIngredient( 'b', new Seeds (2))
                .addIngredient( 'c', new Leather(9))
                .addIngredient( 'd', new Logs (5))
                .addIngredient( 'e', new Podzol(5))
                .addIngredient( 'f', new Wool(9))
                .addIngredient( 'g', new Bones (12))
                .build ();
    }
}
