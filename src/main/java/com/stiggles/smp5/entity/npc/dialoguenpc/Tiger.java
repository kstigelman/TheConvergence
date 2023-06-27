package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
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

public class Tiger extends ShopNPC {

    public Tiger(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY4NTU0MDI3NzQ4MywKICAicHJvZmlsZUlkIiA6ICJjMDI0Y2M0YTQwMzc0YWFjYTk2ZTE2Y2MwODM1ZDE4MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJDb3JlVGVjaCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kZWIwNzI3ZmFkOGM2YTk0ZmZhZDIyOTdmMDVkZjdmY2RjZGRjYzRmMDQ1OGM4ZThmOTg1ODIyMjFhOGIxZDg3IgogICAgfQogIH0KfQ==",
                "VLIq/q4/FEosBwVQRL6JvXRPyvP4FdZ746P1FgEyPLoY/L3O0ts/3BYSl3l8VZY39DpYIVDw+3NH2ZfyorYFSNMvRFmXFOhK9Ju3ck78MBtw0gW58bIbUz5Z3vxenzMXgOUeZKhPuN3/aDaONLF2w5t/L8GDGf9hUmmCREdylO7AVDCtmfQKeyHFJcy01oI8v5BZmUmYQ7GF7GmkXHNBJiHBFYqaPKCzoQQjka2/xpE46Fk6MZ4iXqpcMlv7REC/IQBN5wy22lIa/tobLdsE9fZH/Du50W8vCaIl/btADJZM1SKqTS4olFUrdCGXsEiUUfWmJhw8AnTWLJDuyKQ5gUKMSeMag0KGANTLZRJWVj2d75WcIKfGxEK+/Agnpl3X+2C3dGUbCc+p8/xI0aNNZSVKiGUwTAZSfH8+VeQ7st8GVQhhEQ/XuX5SutCu9WzoREgRu3dtkrr5oYZ8RtqRYArjC0o7qid7ckk0y6gp5l6mAJrhxFyaTjOfbXdrBwTg6ho/0C41kpoixcChpknDf+UvymK0ZE9lhdgHFPeGtcGAJ4miw979Yjqp//lqK2crQphKwfDbP3W5KwAtroQQ6WektWsR6SXKtryLO/xnt0NjPOdHoIdqEHjPfGXRV/pGr7miK1EtkDPy21GBhElXql3MQBwuJkH86TX/RP3mn5Q="
        );
    }

    @Override
    public boolean handleTrade(Player player, StigglesBaseItem item) {
        if (super.handleTrade(player, item)) {
            sendMessage(player, "Thanks!");
            return true;
        }
        sendMessage(player, "You don't have enough money!");
        return false;
    }

    @Override
    public void createGUI(Player player) {
        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# # b c d e f # #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('b', new Logs(8))
                .addIngredient('c', new Cobblestone(2))
                .addIngredient('d', new Bricks(4))
                .addIngredient('e', new Glowstone(10))
                .addIngredient('f', new Coal(16))
                .build();
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Welcome to the Community Chest!");

        if (player.getName().contains("ItsTigerFist")) {
            sendMessage(player, "Hold up, WHAT??? ARE YOU ME???");
        }

    }

    private class Logs extends StigglesBaseItem {
        public Logs(int price) {
            super(price);
            int n = Math.abs(ri % 7);

            if (n == 0) {
                item = new ItemStack(Material.OAK_LOG, 4);
            } else if (n == 1) {
                item = new ItemStack(Material.BIRCH_LOG, 4);
            } else if (n == 2) {
                item = new ItemStack(Material.DARK_OAK_LOG, 4);
            } else if (n == 3) {
                item = new ItemStack(Material.JUNGLE_LOG, 4);
            } else if (n == 4) {
                item = new ItemStack(Material.ACACIA_LOG, 4);
            } else if (n == 5) {
                item = new ItemStack(Material.SPRUCE_LOG, 4);
            } else if (n == 6) {
                item = new ItemStack(Material.CHERRY_LOG, 4);
            }
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Cobblestone extends StigglesBaseItem {
        public Cobblestone(int price) {
            super(price);
            item = new ItemStack(Material.COBBLESTONE, 4);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Bricks extends StigglesBaseItem {
        public Bricks(int price) {
            super(price);
            item = new ItemStack(Material.BRICKS, 4);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Glowstone extends StigglesBaseItem {
        public Glowstone(int price) {
            super(price);
            item = new ItemStack(Material.GLOWSTONE, 4);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Coal extends StigglesBaseItem {
        public Coal(int price) {
            super(price);
            item = new ItemStack(Material.COAL, 4);
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
