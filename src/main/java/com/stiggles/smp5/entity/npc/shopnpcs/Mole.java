package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.Colors;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.builder.PotionBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;

public class Mole extends ShopNPC {
    private static final int STAINED_GLASS_ID = 95;
    private class EnergyDrink extends StigglesBaseItem {
        public EnergyDrink (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new PotionBuilder(PotionBuilder.PotionType.NORMAL)
                    .setDisplayName(ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "Kelprosoft Energy Drink")
                    .addLoreLines (ChatColor.GREEN + "World-famous Kelprosoft Energy Drink! A favorite of Ed Shearan!")
                    .addLoreLines(this.getCost())
                    .addEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 1))
                    .addEffect(new PotionEffect(PotionEffectType.SPEED, 400, 5));
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Kelp extends StigglesBaseItem {
        public Kelp (int price) {
            super (price);
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


    private class Slimeball extends StigglesBaseItem {
        public Slimeball (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.SLIME_BALL)
                    .addLoreLines(this.getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Quartz extends StigglesBaseItem {
        public Quartz (int price) {
            super (price);
            item = new ItemStack(Material.QUARTZ);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item)
                    .addLoreLines(this.getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class StainedGlass extends StigglesBaseItem {
        public StainedGlass (int price) {
            super (price);
            Material material;
            int n = (ri % 16);

            material = Material.getMaterial((Colors.getColor (n) + "_STAINED_GLASS").toLowerCase());
            item = new ItemStack(material);
        }
        public ItemProvider getItemProvider () {
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

    public Mole (SMP5 main, String name, Location location) {
        super (main, name, location);
        setSkin (
                "ewogICJ0aW1lc3RhbXAiIDogMTY4MzE0MTU1OTQxMiwKICAicHJvZmlsZUlkIiA6ICIyMDhhMWJkNzAxMzg0ZDE0YTA1NjU4MTkyNzhmMGExNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJab3JndWl0byIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yYTBiOGQyOGJiNGM5YWIyMTEwM2Y4YjBjZmIxNWYyYzZlYTc3NWVhOWJmMTZkZDNlYjdmYTM2ZGI3ODMzYTUyIgogICAgfQogIH0KfQ==",
                "PZEalqAbp0hiCIYRNnC0NW2S7+6i5t3qJU/2eekbfKLbBqdGZ+VCiUxsT/yWUj7sK/Nc6EwHabz7AJhwCZm+xGGJgoXoEsm10Q4ahFa4MtNM/JQ8p/hyFuKJAPk8Ea5XlH1M8453tAKTmrRLTPCPn3vdcgruOtRfWLOhN+sMNiqyP4MWJ/oOrVMdLMjxUx4I36hnyg600Dne3Hstoqx1bmYFTYmhbCM7UIvc8yxicgSB/urIEZyvcvtNlom4ef8sxwY1S8AIY4iWsy5mEqF5h1/p0tSiD6ROjbM/kZObjiL94mZ8eehNsrTm2WLYZzgPJa4ZA/MT9tH9pkK5Kxoo72AYrTgONTtJmm+lw/lhDLRoEgx/+ThFv3vSaC0CXZwQoSmv9v3nXB1xWuAiZeAP1p6lcLRe3ykmPNTrsLnU7um/FEU6LWKTTzGGo6FWzzLf+F4gaMJM/Fzya/fQBW97pm7HUrvdurnCpgYsbLBD3KW2GUaxnB+1BiCjhsyyMg9YYrXT9WrvUTHSQz0twfQpePSl7CZ488q57zXe6+0Nr/4maLW1Tf4vUJr7DqC9YzjtItYBVxjmBHK6NsiVCOfg6ZNV8FvEhAOiCuqMKkfHn5zb83Zt4eMefNavxrRTb6rJX9DC+rAEhZdTqHwrzp4dON+dLvJIzw2XMHWtL8V+tc0="
        );
    }

    @Override
    public boolean handleTrade (Player player, StigglesBaseItem item) {
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
    }

    @Override
    public void createGUI(Player player) {
        AbstractItem lockedSlot = new Locked("Find the Morabito's hideout.");
        /* if (player has visited morabito hideout)
            lockedSlot = new Dagger (1000);*/

        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new EnergyDrink (80))
                .addIngredient( 'b', new Slimeball (10))
                .addIngredient( 'c', new Kelp (10))
                .addIngredient( 'd', new Quartz (15))
                .addIngredient( 'e', new StainedGlass (9))
                .addIngredient( 'f', new Locked ("To be added"))
                .addIngredient( 'g', lockedSlot)
                .build ();
    }
}
