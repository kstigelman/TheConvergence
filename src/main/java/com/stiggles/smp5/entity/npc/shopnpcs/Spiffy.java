package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.misc.Colors;
import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.builder.PotionBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;

public class Spiffy extends ShopNPC {

    public Spiffy(SMP5 main, String name, Location location) {
        super(main, name, location);
        setSkin(
                "ewogICJ0aW1lc3RhbXAiIDogMTY4MDgwNDc2MDQ5MSwKICAicHJvZmlsZUlkIiA6ICI3ZDJhY2YzOGQ3YTQ0YjU0YTliMGNkYTZhNzk1YmNmYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb3VuY2luZXNzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2EyNWZmMmEwOTU2YWMzYmYzNTBmNDU4YThlNjQyZDkyZGEyMDI3ZDNkMTA4MTYxODE1MjE3NzFiOGRkZWZhNTAiCiAgICB9CiAgfQp9",
                "PfIcTJoUTa0EvNemdy9V4ymTa6hEjQmHwUfnjml8l4XGBJq1JwRTf+pd7Yomc6mGLtlcjzzrQBFtcDs6IBVLqpx4eN3ZOuxobcLCJ2DibSN0sPsOwwMJ2KVXcu4jyVZ1cyC5Rba8aS8DJyWzExw/oO1Gsq+CWKgkSs8cootdrrmdrg2MvIT5BicHOIdHO7sj6lClfOuSFPsVrN/NYbP//91IGNkxFsPzAeB5gljnIOH44X2yjZqv7BqdJ6we9okrCiTDuByQg1I8eHl1D22tdh2Gt8lCvTBwNnQvECsLxTbcGaB3nF/Uno089vvH+VT09daGZiom2Q25bhtnRfg/U13fz5r1etJpHctwsE2vhNewtR7kCL8Vjgp2eJ/QFpk3KIong1q63867tjPEsSXgqS2l4+JJBVb7W91hIzOoV785zoRlBkXs16n2/P/pfw3V+zDinPAeecGJRLgrkB1K/pwffzFL5ACsUUfYP9HSnqAVQ/FZWdBmAV3sHOzPMgZOj8nCjw1BYzd1S7fGt2w6WwQrts+l8z3l2fIzuKnEmlDgQ5nkhVgBpbJJTMKx9mI+n099OT4LgwFE439YM0tYCOn2MuxiVxhEHxFHzyJQ/VSkhYIJxtbc1Fw2METCCihnWnqAdoJvQc2L1YdR33/x+TzEExZaiDMXaJPrH1a+tnc="
        );
    }

    @Override
    public boolean handleTrade(Player player, StigglesBaseItem item) {
        if (super.handleTrade(player, item)) {
            sendMessage(player, "Enjoy.");
            return true;
        }
        sendMessage(player, "Sorry, you don't have enough money for that.");
        return false;
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Welcome! How can I help you?");
        if (player.getName().contains("Shadowkatcher")) {
            sendMessage(player, "Are you me??? I can't believe it.");
        }
    }

    @Override
    public void onInteract(Player player) {
        if (checkQuestItem(player))
            return;
        interactDialogue(player);
        createGUI(player);
        showGUI(player);
        if (!Quest.isQuestComplete(player, Quest.QuestName.MORABITO_RECIPE))
            talk(player);
    }

    @Override
    public void createGUI(Player player) {
        AbstractItem lockedSlot = new Locked("Complete quest [That devious Morabito...]");
        /* if (player has visited morabito hideout)
            lockedSlot = new Dagger (1000);*/

        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('a', new Moonshine(40))
                .addIngredient('b', new Sand(1))
                .addIngredient('c', new Clay(1))
                .addIngredient('d', new ColoredClay(2))
                .addIngredient('e', new GlazedTerracotta(3))
                .addIngredient('f', new Locked("? ? ?"))
                .addIngredient('g', new Locked("? ? ?"))
                .build();
    }

    public boolean checkQuestItem(Player player) {
        if (player.getInventory().getItemInMainHand().hasItemMeta()) {
            ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
            if (im != null && im.hasDisplayName() && im.getLocalizedName().equals("recipe")) {
                if (!Quest.isQuestComplete(player, Quest.QuestName.MORABITO_RECIPE)) {
                    player.getInventory().getItemInMainHand().setAmount(0);
                    sendMessage(player, "You got it??? Thank you so much!");
                    Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "Now I can make the moonshine taste right. Here... I'll give you some money for helping me out, but here's some free moonshine also!"), 40);
                    Bukkit.getScheduler().runTaskLater(main, () -> player.getInventory().addItem(new Moonshine(50).getItemProvider().get()), 60);
                    Bukkit.getScheduler().runTaskLater(main, () -> Quest.questComplete(player, Quest.QuestName.MORABITO_RECIPE, "That devious Morabito...", 50), 60);
                    return true;
                }
            }
        }
        return false;
    }

    private class Moonshine extends StigglesBaseItem {
        public Moonshine(int price) {
            super(price);
            item = getItemProvider().get();
        }

        public ItemProvider getItemProvider() {
            return new PotionBuilder(PotionBuilder.PotionType.NORMAL)
                    .setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Moonshine")
                    .addLoreLines("World famous Moonshine from the Spectral Saloon!")
                    .addLoreLines(this.getCost())
                    .addEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 1))
                    .addEffect(new PotionEffect(PotionEffectType.POISON, 200, 1))
                    .addEffect(new PotionEffect(PotionEffectType.LUCK, 60, 1))
                    .addEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 1));
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Sand extends StigglesBaseItem {
        public Sand(int price) {
            super(price);
            Material material;
            int n = Math.abs(ri % 8);

            if (n <= 4)
                material = Material.SAND;
            else
                material = Material.RED_SAND;

            item = new ItemStack(material);
        }

        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(this.getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }

    private class Clay extends StigglesBaseItem {
        public Clay(int price) {
            super(price);
            item = new ItemStack(Material.TERRACOTTA);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.TERRACOTTA)
                    .addLoreLines(this.getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class ColoredClay extends StigglesBaseItem {
        public ColoredClay(int price) {
            super(price);
            Material material;
            //int n = (ri % 16) + 389;

            //material = Material.values()[n];
            int n = Math.abs(ri % 16);

            material = Material.valueOf((Colors.getColor(n) + "_TERRACOTTA"));

            item = new ItemStack(material);
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

    private class GlazedTerracotta extends StigglesBaseItem {
        public GlazedTerracotta(int price) {
            super(price);
            Material material;
            //int n = (ri % 16) + 501;
            //material = Material.values()[n];
            int n = Math.abs(ri % 16);

            material = Material.valueOf((Colors.getColor(n) + "_GLAZED_TERRACOTTA"));
            item = new ItemStack(material);
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
