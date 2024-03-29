package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.StigglesPlayer;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.*;
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


public class Inventor extends ShopNPC {
    public Inventor(SMP5 main, String name) {
        this(main, name, new Location(Bukkit.getWorld("world"), -0.5, 73, 10));
    }

    public Inventor(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY4MTcwMTk0MzM3MSwKICAicHJvZmlsZUlkIiA6ICIxYjcyNzc5ZTdlYzk0Mzk2YTgzNmNkYjNjM2E3MzEwYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJGbGV1cnk4NyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hMjU2OTQ5NTBkZDIxN2RlNTkxNTRkYzkxMGNkMDMyMThmMTMzZWQzNGFmZGYyYTNmMDg1MjVlMjQxMzJmM2M5IgogICAgfSwKICAgICJDQVBFIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzQwYzBlMDNkZDI0YTExYjE1YThiMzNjMmE3ZTllMzJhYmIyMDUxYjI0ODFkMGJhN2RlZmQ2MzVjYTdhOTMzIgogICAgfQogIH0KfQ==",
                "YWfITqMCGO8OMQfhUdn83WHoVwng0qRK9T8ptd8ey7lfR1NTK5Ux41XOHY6viB0orDxs7fmDBs5KM//acWsmyLWuWuPSpf2qdkYWaO3Z5IMrxcO7gEcaUwm7gnYB6kArrtmJn2eZj6PEcNynDMAsZqxKVww0v7nK09ps8w7/ke1B5DmmsaYjuBXhCYB2+c2G+Qmb9RJAiNniDz/N77A8/p3k/7N0BlGQhNxpopbnXDK5PqfSIXuvJrIB/UW4RqNV+1nf7KZYQnlgiQtTlFmBkwosgrbN8YBab5wLbsI7bT2NmO51xYCAnXdt8NYCuoSgQjIeVdRRfja7iqTWYsrIDwhbqh/XmjKfxdq5U8vPVaifuCC/ZsTw8nhBA2ZtL+i6LydUvHHhwPGHl6fxwAZS0jVwFibOgmkwv58VMGHYtQv1NCSAQIW7RNUu00j1O8cmeBh2+BPAG+pekLkk+vUh8hmuWpl+eK/n1rjHRZ3Isv25WC9vfBgi8MFsyzcXXjYKAJLrZNueFBnDti8SzyRfK2sTUx2s0ByG3Yr9OCxKNt/G1aevn9MpguvxVMdClya99X/digOayUoO+lBzxbWe6edSdf5UmMuix8izpfBQkMX82ojN9WHX6VdJqZpjJTOe+FBdmusFWu4beLIbIH1nKj2l8X6RZFlsPqG+hdZXvFs="
        );
    }

    @Override
    public boolean handleTrade(Player player, StigglesBaseItem item) {
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
            sendMessage(player, "Hi there.");
        else
            sendMessage(player, "Welcome to my workshop.");

        if (player.getName().contains("Fleury87")) {
            sendMessage(player, "Interesting... I knew it. I knew this world was part of some multiversal anomaly.");
        }
    }

    public boolean checkQuestItems(Player player) {
        if (player.getInventory().getItemInMainHand().hasItemMeta()) {
            ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
            if (im == null || !im.hasLocalizedName())
                return true;
            if (im.getLocalizedName().equals("starry_letter")) {
                sendMessage(player, "Hmmm. How peculiar. A letter from Phoenix Victor-- or Starry, as you may know her.");
                sendMessageLater(player, "Interestingly enough, I suspect this Starry is very parallel with the one I know...", 60);
                sendMessageLater(player, "Well, enough of this tangent. It looks like she needs help.", 120);
                sendMessageLater(player, "I've actually been thinking about ways to defeat Nouveau. I hope my inventions and ideas will be valuable to her.", 180);
                sendMessageLater(player, "Let her know she has my support.", 260);
                if (!Quest.isQuestComplete(player, Quest.QuestName.RECRUIT_INVENTOR))
                    Bukkit.getScheduler().runTaskLater(main, () -> Quest.questComplete(player, Quest.QuestName.RECRUIT_INVENTOR, "The Strategist", 100), 260);
                return true;
            }
        }

        return false;
    }

    @Override
    public void createGUI(Player player) {
        AbstractItem lockedSlot = new Locked("? ? ?");
        //Also check if player has visited all locations
        StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(player.getUniqueId());
        if (sp.hasQuestCompleted(Quest.QuestName.RECRUIT_INVENTOR))
            lockedSlot = new Elytra(900);
        //Advancement a = Bukkit.getAdvancement(new NamespacedKey(main, "minecraft:end/kill_dragon"));
        /*if (a != null) {
            if (player.getAdvancementProgress(a).isDone())
                lockedSlot = new Elytra(6464);
        }*/

        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# # b c d e f # #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('b', new Stone(2))
                .addIngredient('c', new Redstone(10))
                .addIngredient('d', new Quartz(15))
                .addIngredient('e', new Piston(20))
                .addIngredient('f', lockedSlot)
                //.addIngredient( 'f', new DragonHead(2000))
                //.addIngredient( 'g', lockedSlot)
                .build();
    }

    private class Stone extends StigglesBaseItem {
        public Stone(int price) {
            super(price);
            item = new ItemStack(Material.STONE);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.STONE).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Redstone extends StigglesBaseItem {
        public Redstone(int price) {
            super(price);
            item = new ItemStack(Material.REDSTONE);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.REDSTONE).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class ChorusFruit extends StigglesBaseItem {
        public ChorusFruit(int price) {
            super(price);
            item = new ItemStack(Material.CHORUS_FRUIT);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.CHORUS_FLOWER).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class PurpurBlock extends StigglesBaseItem {
        public PurpurBlock(int price) {
            super(price);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.PURPUR_BLOCK).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class EndRod extends StigglesBaseItem {
        public EndRod(int price) {
            super(price);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.END_ROD).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class DragonHead extends StigglesBaseItem {
        public DragonHead(int price) {
            super(price);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.DRAGON_HEAD).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Quartz extends StigglesBaseItem {
        public Quartz(int price) {
            super(price);
            item = new ItemStack(Material.QUARTZ);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item)
                    .addLoreLines(this.getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Piston extends StigglesBaseItem {
        public Piston(int price) {
            super(price);
            int n = ri % 2;
            if (ri == 0)
                item = new ItemStack(Material.PISTON);
            else {
                item = new ItemStack(Material.STICKY_PISTON);
                price = price + 5;
            }
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item)
                    .addLoreLines(this.getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Elytra extends StigglesBaseItem {
        public Elytra(int price) {
            super(price);
            item = new ItemStack(Material.ELYTRA);
            ItemMeta im = item.getItemMeta();
            im.setDisplayName(ChatColor.LIGHT_PURPLE + "Alas Wingsuit");
            item.setItemMeta(im);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.ELYTRA)
                    .setDisplayName(ChatColor.LIGHT_PURPLE + "Alas Wingsuit")
                    .addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Locked extends AbstractItem {
        String lore;

        public Locked(String description) {
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
            playSound(player, Sound.ENTITY_VILLAGER_NO);
        }
    }
}
