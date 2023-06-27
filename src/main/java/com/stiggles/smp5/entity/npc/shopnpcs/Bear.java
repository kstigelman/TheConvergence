package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
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


public class Bear extends ShopNPC {
    public Bear(SMP5 main, String name) {
        this(main, name, new Location(Bukkit.getWorld("world"), -0.5, 73, 10));
    }

    public Bear(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY4MjU0NTExMTE1NSwKICAicHJvZmlsZUlkIiA6ICJhMDEyZDkwYWZjMGI0OGE4OTk5ODAwNzE5NDA5NzM2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJFbmVyZ3lpbnRha2UiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzk5NjA0YzU0NTEyNTRhZTE2OTZhZGFlZDdmZjExNmM4MTM2MzgwYTUzNTQwNWU1ZTMyMmI4ZTA4MjMzNzhmYyIKICAgIH0KICB9Cn0=",
                "DQ9djFsksTopdywLAm9xnUB145sciJgI+lRjwJDO0BcsFO071cSnDPjqax8q/sCvYuOD0Kt10VzkEJ+m+3JjCgtsECAfVlck3RJMI4PtEafCjSZ0UB5tdIFXTO68LFJ1eTi9Fia+eeepUFiHuUOCW4+Ahmp0X9XubNdyBUsAP43in6PCNfBpavV3Fb9tvS8ES9C+zmcOlprjIfvRviC/8R1oy5r9GwhstQ4562Wt6ZUh7sR2dd3W+Q/yYvW1vKcHJ1zQXGBqv5TVZ7WgFJotQZzDF+uqXBaHBR1LCw5agIl9MXLmsuXgEzg0myL/MmINXYwyFhNXn/vVtR61n1VjrCXysjX6SNKCunQneqgehcQH27o+pa/oEAFHPWqRXKBFX/JVYY328L8c+irURy/98GU1e80zSsNvn2n+NeKxMjVvqCd0LbdSsbg4M+wzDaKcM/M/l06WCgoTiZ4nIrKgi7BbXpS3KaaS5cEpqTkZmvpzOPzYQxKs9Jc25kXATlzE7qplrG2eIVZhSqh8Wb0gOBkQqK8Nw8ygqzJRr/m8vm2eD+BaRqeu+4NGLl31fO+LujqRNTtrJJVlUDI96Nc7CKfA4cZ/kqdg5s1oT5HDOC9hkqEHuzBI5USKeaJ2+5qUKx6OHSKsAfBkyzUMiiORb2g2IFh2BuymJsW6mrom5GQ="
        );
    }

    @Override
    public boolean handleTrade(Player player, StigglesBaseItem item) {
        if (super.handleTrade(player, item)) {
            sendMessage(player, "Thanks.");
            return true;
        }
        sendMessage(player, "I can't give that to you right now.");
        return false;
    }

    @Override
    public void interactDialogue(Player player) {
        if (player.getName().contains("BearSharken")) {
            sendMessage(player, "Hmmm. Strange. It appears there are two of us.");
            return;
        }

        int n = main.getRandom() % 2;
        if (n == 0)
            sendMessage(player, "Hello.");
        else
            sendMessage(player, "Hmmmmm.");
    }

    @Override
    public void createGUI(Player player) {
        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('a', new Veggie(1))
                .addIngredient('b', new Seeds(1))
                .addIngredient('c', new Leather(3))
                .addIngredient('d', new Logs(2))
                .addIngredient('e', new Podzol(5))
                .addIngredient('f', new Wool(2))
                .addIngredient('g', new Bones(9))
                .build();
    }

    private class Veggie extends StigglesBaseItem {
        public Veggie(int price) {
            super(price);
            if (ri % 2 == 0)
                item = new ItemStack(Material.CARROT);
            else
                item = new ItemStack(Material.POTATO);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Seeds extends StigglesBaseItem {
        public Seeds(int price) {
            super(price);
            int n = ri % 3;
            if (ri == 0)
                item = new ItemStack(Material.MELON_SEEDS);
            else if (ri == 1)
                item = new ItemStack(Material.PUMPKIN_SEEDS);
            else
                item = new ItemStack(Material.BEETROOT_SEEDS);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Bones extends StigglesBaseItem {
        public Bones(int price) {
            super(price);
            item = new ItemStack(Material.BONE_BLOCK);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.BONE_BLOCK).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Leather extends StigglesBaseItem {
        public Leather(int price) {
            super(price);
            item = new ItemStack(Material.LEATHER);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.LEATHER).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Logs extends StigglesBaseItem {
        public Logs(int price) {
            super(price);
            int n = (ri % 7);

            item = new ItemStack(Material.OAK_LOG);

        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Podzol extends StigglesBaseItem {
        public Podzol(int price) {
            super(price);
            item = new ItemStack(Material.PODZOL);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.PODZOL).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Wool extends StigglesBaseItem {
        public Wool(int price) {
            super(price);
            item = new ItemStack(Material.WHITE_WOOL);
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
