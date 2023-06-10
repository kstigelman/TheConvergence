package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;

import java.util.Arrays;

public class Philippe extends ShopNPC {
    private class Helmet extends StigglesBaseItem {
        public Helmet(int price) {
            super(price);
            int n = ri % 10;
            if (n <= 4)
                item = new ItemStack(Material.IRON_HELMET);
            else if (n <= 6) {
                item = new ItemStack(Material.CHAINMAIL_HELMET);
                cost /= 2;
            } else if (n <= 8) {
                item = new ItemStack(Material.DIAMOND_HELMET);
                cost *= 1.8;
            } else {
                item = new ItemStack(Material.NETHERITE_HELMET);
                cost *= 2.5;
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
    private class Chestplate extends StigglesBaseItem {
        public Chestplate(int price) {
            super(price);
            int n = ri % 10;
            if (n <= 4)
                item = new ItemStack(Material.IRON_CHESTPLATE);
            else if (n <= 6) {
                item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
                cost /= 2;
            } else if (n <= 8) {
                item = new ItemStack(Material.DIAMOND_CHESTPLATE);
                cost *= 1.8;
            } else {
                item = new ItemStack(Material.NETHERITE_CHESTPLATE);
                cost *= 2.5;
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

    private class Leggings extends StigglesBaseItem {
        public Leggings(int price) {
            super(price);
            int n = ri % 10;
            if (n <= 4)
                item = new ItemStack(Material.IRON_LEGGINGS);
            else if (n <= 6) {
                item = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                cost /= 2;
            } else if (n <= 8) {
                item = new ItemStack(Material.DIAMOND_LEGGINGS);
                cost *= 1.8;
            } else {
                item = new ItemStack(Material.NETHERITE_LEGGINGS);
                cost *= 2.5;
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

    private class Boots extends StigglesBaseItem {
        public Boots(int price) {
            super(price);
            int n = ri % 10;
            if (n <= 4)
                item = new ItemStack(Material.IRON_BOOTS);
            else if (n <= 6) {
                item = new ItemStack(Material.CHAINMAIL_BOOTS);
                cost /= 2;
            } else if (n <= 8) {
                item = new ItemStack(Material.DIAMOND_BOOTS);
                cost *= 1.8;
            } else {
                item = new ItemStack(Material.NETHERITE_BOOTS);
                cost *= 2.5;
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

    private class Sword extends StigglesBaseItem {
        public Sword(int price) {
            super(price);
            if (ri % 15 <= 8)
                item = new ItemStack(Material.IRON_SWORD);
            else if (ri % 15 <= 12) {
                item = new ItemStack(Material.DIAMOND_SWORD);
                cost *= 1.8;
            } else {
                item = new ItemStack(Material.NETHERITE_SWORD);
                cost *= 2.5;
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

    private class Shield extends StigglesBaseItem {
        public Shield(int price) {
            super(price);
            item = new ItemStack(Material.SHIELD);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Arrow extends StigglesBaseItem {
        public Arrow(int price) {
            super(price);
            item = new ItemStack(Material.ARROW, 64);
        }

        public ItemProvider getItemProvider() {
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
            playSound (player, Sound.ENTITY_VILLAGER_NO);
        }
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
    public void interactDialogue(Player player) {
        int n = main.getRandom() % 3;
        if (n == 0)
            sendMessage(player, "Bonjour");
        else if (n == 1)
            sendMessage(player, "Welcome to Holland!");
        else
            sendMessage(player, "Bienvenue en Hollande!");

        if (player.getName().contains ("PhilippeAlfred")) {
            sendMessage(player, "Quoi? Are you me? I didn't expect this today.");
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
                .addIngredient( 'a', new Sword (80))
                .addIngredient( 'b', new Arrow (75))
                .addIngredient( 'c', new Helmet (180))
                .addIngredient( 'd', new Chestplate(300))
                .addIngredient( 'e', new Leggings (260))
                .addIngredient( 'f', new Boots (140))
                .addIngredient( 'g', new Locked ("To be added"))
                .build ();
    }

    public boolean checkQuestItems (Player player) {
        if (player.getInventory().getItemInMainHand().hasItemMeta()) {
            ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
            if (im != null && im.hasDisplayName() && im.getLocalizedName().equals("nats_breath")) {
                sendMessage(player, "A sword named Natalie's Breath? Quoi? Where I am from, there is the legend of a creature, a pegasus, named Natalie. Legend says that even a drop this creature's breath has the power to heal any disease. No one I know has ever seen her, though.");
                return true;
            }
        }
        return false;
    }
}
