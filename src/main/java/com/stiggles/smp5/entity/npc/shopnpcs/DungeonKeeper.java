package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;

import java.util.ArrayList;

public class DungeonKeeper extends ShopNPC {

    private class Moonshine extends AbstractItem {

        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.ENDER_CHEST)
                    .setDisplayName(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Item Collection")
                    .addLoreLines ("Get back your lost items... for a price...");
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            if (!items.isEmpty()) {
                if (BankManager.hasSufficientFunds(player, 50)) {
                    CreateInventoryChest(new Location(Bukkit.getWorld(getWorldName()), -5, -60, 25));
                    BankManager.withdraw(player, 50);
                    sendMessage(player, "Heh.");
                }
                else {
                    sendMessage(player, "That price isn't gonna cut it.");
                }
            }
        }
    }
    ArrayList<ItemStack> items = new ArrayList<>();

    public DungeonKeeper (SMP5 main, String name, Location location) {

        super (main, name, location);

        setNameColor(ChatColor.AQUA);


        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTYxNDI2NjQ1MzI3MSwKICAicHJvZmlsZUlkIiA6ICI5MThhMDI5NTU5ZGQ0Y2U2YjE2ZjdhNWQ1M2VmYjQxMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJCZWV2ZWxvcGVyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxZTU2N2Y5YmRjMjM3OGM0ZDg5ZTRlMzViZjFmMDIzZjczYTQ0ODZmOGJhZGViYzA5NTlmNzA5YmE2MGYwZWIiCiAgICB9CiAgfQp9",
                "ajL5e3ePRfYgqZCwXAAfup8aSC4CJr2oi1JqDKZ1v6W4mB801djE37Vn6fT7KFa+foWEd/QXgT1OWGcoSVYILHSah9Q5yXjE5oPqERI4oO17VdFL35hDMUHx8HefVFlOgaYq4h7yxBsL9ygbJRM9mpTu5Rum3FBVurhCfnA6AzYly20gEfSmMkyM2wFpoSBAXRxJXVEa+qRdWtepkKbudCPbQJZf1GIEpJGZqv/fQBooFeL/E4qkX5o5ryBYpDSidq4LAiXfMMv75BEXTW9JsSShA0BhcMf+THzGPOOhhYubowG6VIVPfVdpH3j310uX20vl9zubNip3v/uZLUuMX5pRZKIb/AUbDaPjGHJ53uEoqd23qL+fd9D9n2aUCsESDh/QnExcskkO0pyaMy1zM7LJoBaTewFfO2MncGY1V62spbHQ49qjQFgem1xaOGO1WiLI4s+Y5dfAOsBXe7rlD7aDeBsFC0RItDqHvUt1zwwLtCalrhrXOcz3IxMX+Sf5wO1XmMyZDmPefM9GiWwI+kBN2ZO8ZjO1diTJepyS5FhCzLc4zCfrJ3z15tP6Rcmw5p2p1PaFmOGyexhDPVANVMkiQB0vqiJUqURzYXgel6AqEqkTQo3byUqV6qdIi7faf4giB0+s/qPC32PYJxxvJUyiHVnWj8olTLX0W7L/nrw="
        );
    }

    @Override
    public void onInteract(Player p) {
            if (!items.isEmpty()) {
                if (p.getInventory().getItemInMainHand().getType().equals(Material.GOLD_INGOT)) {
                    CreateInventoryChest(new Location(Bukkit.getWorlds().get(0), -5, -60, 25));
                    ItemStack handItem = p.getInventory().getItem(EquipmentSlot.HAND);
                    handItem.setAmount (handItem.getAmount() - 1);
                    p.updateInventory();
                    BankManager.withdraw(p, 50);
                    this.sendMessage(p, "Heh.");
                }
                else {
                    this.sendMessage(p,"That price isn't gonna cut it.");
                }
            }
            else {
                this.sendMessage(p,"Welcome to the dungeon. Good luck...");
            }
    }

    @Override
    public void interactDialogue(Player p) {

    }
    @Override
    public void createGUI (Player player) {
        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# . a . b . c . #",
                        "# . . . . . . . #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('a', new SimpleItem(new ItemBuilder(Material.ENDER_CHEST)))
                .addIngredient('b', new SimpleItem(new ItemBuilder(Material.DIAMOND_SWORD)))
                .addIngredient('c', new SimpleItem(new ItemBuilder(Material.IRON_PICKAXE)))
                .build ();

    }
    public void createPlayerInventory () {
        
    }

    public void GiveInventory (Inventory i) {
        for (ItemStack is : i) {
            if (is != null)
                items.add (is);
        }

    }

    public void CreateInventoryChest (Location loc) {

        new BukkitRunnable() {
            @Override
            public void run () {
                loc.getBlock().setType (Material.CHEST);
                Block b = loc.getBlock();
                Chest c = (Chest) b.getState();

                for (ItemStack i : items)
                    if (i != null) {
                        c.getBlockInventory().addItem(i);
                    }

                items.clear ();
            }

        }.runTaskLater(SMP5.getPlugin (), 1);
    }
}
