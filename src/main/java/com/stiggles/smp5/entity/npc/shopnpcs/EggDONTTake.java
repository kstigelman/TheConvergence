package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
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
import java.util.Random;

public class EggDONTTake extends ShopNPC {
    private class RedMushroom extends StigglesBaseItem {
        public RedMushroom (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.RED_MUSHROOM).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class BrownMushroom extends StigglesBaseItem {
        public BrownMushroom (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.BROWN_MUSHROOM).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Mycelium extends StigglesBaseItem {
        public Mycelium  (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.MYCELIUM).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Stew extends StigglesBaseItem {
        public Stew (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.SUSPICIOUS_STEW).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Flower extends StigglesBaseItem {
        public Flower (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.POPPY).addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Bagel extends StigglesBaseItem {
        public Bagel  (int price, String localName) {
            super (price);
            item = new ItemStack(Material.PUMPKIN_PIE);
            ItemMeta m = item.getItemMeta();
            m.setDisplayName(ChatColor.GOLD + "Bagel");
            item.setItemMeta(m);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item).addLoreLines(getCost());
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

    public EggDONTTake (SMP5 main, String name) {
        super (main, name);

        setSkin (
                "ewogICJ0aW1lc3RhbXAiIDogMTY3MTY2NjczODk0NCwKICAicHJvZmlsZUlkIiA6ICI5NWRmN2I5ZDVhM2M0OWM3OTNlY2VmMzZiNDNmZjQ1NSIsCiAgInByb2ZpbGVOYW1lIiA6ICJFZ2dEb250VGFrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84YTAwNDEyZDM3YTU2N2NkMTE5NWU1ZTg5NzA5YzZhMjA5ZTdiYTdlM2E4Y2QxNDI4MjlkNTljNjgxZDQ1NDQxIgogICAgfQogIH0KfQ==",
                "ROJH9yFIr4Xoj/59T1tVF06q8oGHFblkNiI9w8jhs4sqFTBncJoUxssyqe50yNlg7yvYtetJp3fZ5cG22FiSJ1aGCxYLsten8xy8u9o2vobYOvg1M02xYn1da/LKzF4BtT7rn6sh7jV4brPoV/TtQaawj89Hk+2OzSYZZV8TYqrUSz4GKltinL7x0i/qH7vJHtsJzybTiAHZ19AdLCEkSRmDabR3bZ0tFUGAyi7UGlO9PSu7YbeN4llogmMJyuQq0iqRCJixsktUSx+53Iw1RmenkIOrodEf56G8pEZw6lo12T/GXJH0ZJmoTDLptxIaVxmRF58+Z+A02t7wvon8Aw9dSe7vO879FAFHZrALmgstHCzCRqI+s3so1isjZWe/HjrbYxto0yW4JcoS3+STwjrkY4IuHhULbTixl0awu/Jzj0bp6b8KCrva+0LPadxZcVHRY6PbFzGJ8Fo8H3y75N+9UbjwjYA+lVFq6EkU72HAEkEOYR+5Dl52FA4YepqnZ5hFSSqt8jj8gn9u3Av3cCPNINpM/hQCqvZ9qHYVDJN97k2XEuah8gFqRtYnJdNq6Gm8solyilE4QrgNwETBdaWHocot48+rOnqxQNCmevDY8YFSDRHFAGWvwx/gCc6D8B+j50AnLRIgBVEHsKCXL2AZ65q7RwIrt3RIQ3H1Svw="
        );

        setPos (-2.5, -59, 3.5);
    }
    public EggDONTTake (SMP5 main, String name, Location location) {
        super (main, name, location);
    }

    @Override
    public void createGUI(Player player) {
        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Bagel (15, "bagel"))
                .addIngredient( 'b', new RedMushroom(4))
                .addIngredient( 'c', new BrownMushroom(4))
                .addIngredient( 'd', new Mycelium(5))
                .addIngredient( 'e', new Stew (15))
                .addIngredient( 'f', new Flower (3))
                .addIngredient( 'g', new Locked ("TBA"))
                .build ();
    }

    @Override
    public void interactDialogue(Player player) {
        String msg = "";

        int ni = main.getRandom() % 99;

        if (ni <= 2)
            sendMessage (player, "Eggs among SUS!");
        else if (ni <= 54)
            sendMessage (player, "Don't take eggs when you are given the chance.");
        else
            sendMessage (player, "Eggs look neat.");

    }
}
