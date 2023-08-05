package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.misc.Colors;
import com.stiggles.smp5.entity.npc.ShopNPC;
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
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.builder.PotionBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;

import java.util.List;

public class Mole extends ShopNPC {
    private static final int STAINED_GLASS_ID = 95;

    public Mole(SMP5 main, String name, Location location) {
        super(main, name, location);
        setSkin(
                "ewogICJ0aW1lc3RhbXAiIDogMTY4MzE0MTU1OTQxMiwKICAicHJvZmlsZUlkIiA6ICIyMDhhMWJkNzAxMzg0ZDE0YTA1NjU4MTkyNzhmMGExNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJab3JndWl0byIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yYTBiOGQyOGJiNGM5YWIyMTEwM2Y4YjBjZmIxNWYyYzZlYTc3NWVhOWJmMTZkZDNlYjdmYTM2ZGI3ODMzYTUyIgogICAgfQogIH0KfQ==",
                "PZEalqAbp0hiCIYRNnC0NW2S7+6i5t3qJU/2eekbfKLbBqdGZ+VCiUxsT/yWUj7sK/Nc6EwHabz7AJhwCZm+xGGJgoXoEsm10Q4ahFa4MtNM/JQ8p/hyFuKJAPk8Ea5XlH1M8453tAKTmrRLTPCPn3vdcgruOtRfWLOhN+sMNiqyP4MWJ/oOrVMdLMjxUx4I36hnyg600Dne3Hstoqx1bmYFTYmhbCM7UIvc8yxicgSB/urIEZyvcvtNlom4ef8sxwY1S8AIY4iWsy5mEqF5h1/p0tSiD6ROjbM/kZObjiL94mZ8eehNsrTm2WLYZzgPJa4ZA/MT9tH9pkK5Kxoo72AYrTgONTtJmm+lw/lhDLRoEgx/+ThFv3vSaC0CXZwQoSmv9v3nXB1xWuAiZeAP1p6lcLRe3ykmPNTrsLnU7um/FEU6LWKTTzGGo6FWzzLf+F4gaMJM/Fzya/fQBW97pm7HUrvdurnCpgYsbLBD3KW2GUaxnB+1BiCjhsyyMg9YYrXT9WrvUTHSQz0twfQpePSl7CZ488q57zXe6+0Nr/4maLW1Tf4vUJr7DqC9YzjtItYBVxjmBHK6NsiVCOfg6ZNV8FvEhAOiCuqMKkfHn5zb83Zt4eMefNavxrRTb6rJX9DC+rAEhZdTqHwrzp4dON+dLvJIzw2XMHWtL8V+tc0="
        );
    }

    @Override
    public boolean handleTrade(Player player, StigglesBaseItem item) {
        if (super.handleTrade(player, item)) {
            sendMessage(player, "Thanks for shopping with us!");
            return true;
        }
        sendMessage(player, "Sorry, you don't have enough money for that.");
        return false;
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Welcome to Kelprosoft! How can I help you?");
        if (player.getName().contains("MoleAQuacks")) {
            sendMessage(player, "You so look familiar. Do I know you? I think we share a face.");
        }
    }
    public boolean checkQuestItems(Player player) {
        if (player.getInventory().getItemInMainHand().hasItemMeta()) {
            ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
            if (im == null || !im.hasLocalizedName())
                return true;
            if (im.getLocalizedName().equals("starry_letter")) {
                sendMessage(player, "Oh yeah, Starry stopped by here a bit ago to talk to me about that.");
                sendMessageLater(player, "I'm in, Nouveau has caused enough damage, and I think it's time to take action.", 60);
                sendMessageLater(player, "We've got to keep this on the down-low, however. We don't want Nouveau to catch on.", 120);
                sendMessageLater(player, "Are you still looking for people to recruit for her team? ", 180);
                sendMessageLater (player, "You should go to Holland, I think there might be a few people there that would be great.", 240);
                sendMessageLater(player, "Head northeast. The town is deep in the wooded taiga.", 280);
                return true;
            }
        }

        return false;
    }

    @Override
    public void createGUI(Player player) {
        //AbstractItem lockedSlot = new Locked("Find the Morabito's hideout.");
        /* if (player has visited morabito hideout)
            lockedSlot = new Dagger (1000);*/

        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('a', new EnergyDrink(80))
                .addIngredient('b', new Slimeball(6))
                .addIngredient('c', new Kelp(10))
                .addIngredient('d', new Pickle(15))
                .addIngredient('e', new StainedGlass(2))
                .addIngredient('f', new Coral(20))
                .addIngredient('g', new Locked("To be added"))
                .build();
    }

    private class EnergyDrink extends StigglesBaseItem {
        public EnergyDrink(int price) {
            super(price);
            item = new ItemStack(Material.POTION);
            PotionMeta meta = (PotionMeta) item.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "Kelprosoft Energy Drink");
            meta.setLore(List.of(ChatColor.GREEN + "World-famous Kelprosoft Energy Drink! A favorite of Ed Shearan!"));
            meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 240, 9), true);
            meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 200, 4), true);
            item.setItemMeta(meta);
        }

        public ItemProvider getItemProvider() {
            return new PotionBuilder(PotionBuilder.PotionType.NORMAL)
                    .setDisplayName(ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "Kelprosoft Energy Drink")
                    .addLoreLines(ChatColor.GREEN + "World-famous Kelprosoft Energy Drink! A favorite of Ed Shearan!")
                    .addLoreLines(this.getCost())
                    .addEffect(new PotionEffect(PotionEffectType.CONFUSION, 240, 9))
                    .addEffect(new PotionEffect(PotionEffectType.SPEED, 200, 4));
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Coral extends StigglesBaseItem {
        public Coral(int price) {
            super(price);

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
            return new ItemBuilder(item).addLoreLines(ChatColor.BLUE + "Cost: " + ChatColor.GOLD + cost + " Gold");
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
            handleTrade(player, this);
        }
    }

    private class Kelp extends StigglesBaseItem {
        public Kelp(int price) {
            super(price);
            item = new ItemStack(Material.DRIED_KELP_BLOCK);
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

    private class Pickle extends StigglesBaseItem {
        public Pickle(int price) {
            super(price);
            item = new ItemStack(Material.SEA_PICKLE);
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

    private class Slimeball extends StigglesBaseItem {
        public Slimeball(int price) {
            super(price);
            item = new ItemStack(Material.SLIME_BALL);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.SLIME_BALL)
                    .addLoreLines(this.getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class StainedGlass extends StigglesBaseItem {
        public StainedGlass(int price) {
            super(price);
            Material material;
            int n = (ri % 16);

            if (n < 0)
                n = 0;
            material = Material.valueOf((Colors.getColor(n) + "_STAINED_GLASS"));
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
