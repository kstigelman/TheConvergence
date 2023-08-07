package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.StigglesPlayer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.TabGui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.item.impl.controlitem.PageItem;
import xyz.xenondevs.invui.item.impl.controlitem.TabItem;

import java.util.*;

public class DungeonKeeper extends ShopNPC {

    ArrayList<ItemStack> items = new ArrayList<>();
    HashMap<UUID, PlayerInventory> inventories = new HashMap<>();
    HashMap<UUID, ArrayList<ItemStack>> drops = new HashMap<>();

    public DungeonKeeper(SMP5 main, String name, Location location) {

        super(main, name, location);

        setNameColor(ChatColor.AQUA);

        setVoice(0.5f);
        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTYxNDI2NjQ1MzI3MSwKICAicHJvZmlsZUlkIiA6ICI5MThhMDI5NTU5ZGQ0Y2U2YjE2ZjdhNWQ1M2VmYjQxMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJCZWV2ZWxvcGVyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxZTU2N2Y5YmRjMjM3OGM0ZDg5ZTRlMzViZjFmMDIzZjczYTQ0ODZmOGJhZGViYzA5NTlmNzA5YmE2MGYwZWIiCiAgICB9CiAgfQp9",
                "ajL5e3ePRfYgqZCwXAAfup8aSC4CJr2oi1JqDKZ1v6W4mB801djE37Vn6fT7KFa+foWEd/QXgT1OWGcoSVYILHSah9Q5yXjE5oPqERI4oO17VdFL35hDMUHx8HefVFlOgaYq4h7yxBsL9ygbJRM9mpTu5Rum3FBVurhCfnA6AzYly20gEfSmMkyM2wFpoSBAXRxJXVEa+qRdWtepkKbudCPbQJZf1GIEpJGZqv/fQBooFeL/E4qkX5o5ryBYpDSidq4LAiXfMMv75BEXTW9JsSShA0BhcMf+THzGPOOhhYubowG6VIVPfVdpH3j310uX20vl9zubNip3v/uZLUuMX5pRZKIb/AUbDaPjGHJ53uEoqd23qL+fd9D9n2aUCsESDh/QnExcskkO0pyaMy1zM7LJoBaTewFfO2MncGY1V62spbHQ49qjQFgem1xaOGO1WiLI4s+Y5dfAOsBXe7rlD7aDeBsFC0RItDqHvUt1zwwLtCalrhrXOcz3IxMX+Sf5wO1XmMyZDmPefM9GiWwI+kBN2ZO8ZjO1diTJepyS5FhCzLc4zCfrJ3z15tP6Rcmw5p2p1PaFmOGyexhDPVANVMkiQB0vqiJUqURzYXgel6AqEqkTQo3byUqV6qdIi7faf4giB0+s/qPC32PYJxxvJUyiHVnWj8olTLX0W7L/nrw="
        );
    }

    public boolean containsPlayer (UUID uuid) {
        return inventories.containsKey(uuid);
    }
    public PlayerInventory getPlayerInventory (UUID uuid) {
        return inventories.get(uuid);
    }
    /*
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
        }*/
    @Override
    public boolean handleTrade(Player player, StigglesBaseItem item) {
        StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(player.getUniqueId());


        if (item instanceof ReturnInventory) {
            if (!sp.withdraw(item.cost))
                return false;
            playSound(player, Sound.ENTITY_SKELETON_AMBIENT);
            sendMessage(player, "Pleasure doing business. Heh.");
            return true;
        }
        if (sp.withdraw(item.cost) && player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(item.item);
            playSound(player, Sound.ENTITY_SKELETON_AMBIENT);
            sendMessage(player, "Pleasure doing business. Heh.");
            return true;
        }
        playSound(player, Sound.ENTITY_SKELETON_STEP);
        return false;
    }

    @Override
    public void onInteract(Player player) {
        interactDialogue(player);
        createGUI(player);
        showGUI (player);
        talk(player);
    }

    @Override
    public void interactDialogue(Player p) {
        sendMessage(p, "Heh. How's it goin'?");
        playSound(p, Sound.ENTITY_SKELETON_AMBIENT);
    }

    @Override
    public void createGUI(Player player) {

        AbstractItem chestSlot = new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS));
        if (drops.containsKey(player.getUniqueId())) {
            chestSlot = new ReturnInventory(10);
        }


        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# x x x a x x x #",
                        "# # # # # # # # c")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('a', new BaseKey(0))
                .addIngredient('x', new SimpleItem(new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE)))
                .addIngredient('c', chestSlot)
                .build();

    }

    public void createPlayerInventory() {

    }

    public void giveInventory (Player player) {
        inventories.put (player.getUniqueId(), player.getInventory());
    }
    public void giveDrops (UUID uuid, ItemStack[] itemList) {
        ArrayList<ItemStack> inventory = new ArrayList<>(Arrays.asList(itemList));
        drops.put(uuid, inventory);
    }
    public void giveDrops (Player player) {

        ArrayList<ItemStack> i = new ArrayList<>();

        for (ItemStack is : player.getInventory()) {
            if (is != null)
                items.add (is);
        }
        drops.put (player.getUniqueId(), i);
    }
    public void GiveInventory(Inventory i) {

        for (ItemStack is : i) {
            if (is != null)
                items.add(is);
        }

    }

    public void createChest (UUID uuid) {
        ArrayList<ItemStack> items = drops.get(uuid);


    }
    public void CreateInventoryChest(Location loc) {

        new BukkitRunnable() {
            @Override
            public void run() {
                loc.getBlock().setType(Material.CHEST);
                Block b = loc.getBlock();
                Chest c = (Chest) b.getState();

                for (ItemStack i : items)
                    if (i != null) {
                        c.getBlockInventory().addItem(i);
                    }

                items.clear();
            }

        }.runTaskLater(SMP5.getPlugin(), 1);
    }

    private class BaseKey extends StigglesBaseItem {
        public BaseKey(int price) {
            super(price);
            item = new ItemStack(Material.TRIPWIRE_HOOK);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.BOLD + ChatColor.DARK_RED.toString() + "Cave Dungeon Key");
            meta.setLore(List.of(ChatColor.GRAY + "One-time access to a dungeon."));
            meta.setLocalizedName("cave_key");
            item.setItemMeta(meta);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class ReturnInventory extends StigglesBaseItem {
        public ReturnInventory (int price) {
            super(price);
            item = new ItemStack(Material.CHEST);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.BOLD + ChatColor.GOLD.toString() + "Salvaged Items");
            meta.setLore(List.of(ChatColor.GRAY + "Gain your lost items back from the dungeon.",
                    ChatColor.RED + "WARNING: WILL DROP ITEMS IF YOU HAVE A FULL INVENTORY"));
            item.setItemMeta(meta);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            if (!handleTrade(player, this))
                return;

            player.sendMessage(ChatColor.DARK_GRAY + "You have salvaged your items lost in the dungeon.");
            //player.getInventory().clear();

            List<ItemStack> inv = drops.get(player.getUniqueId());
            if (inv == null)
                return;
            for (ItemStack i : inv) {
                if (i == null)
                    continue;
                if (player.getInventory().firstEmpty() == -1) {
                    player.getWorld().dropItem(player.getLocation(), i);
                    continue;
                }
                player.getInventory().addItem(i);
            }
            drops.remove(player.getUniqueId());
        }
    }

    private class Locked extends AbstractItem {
        String lore;

        public Locked(String description) {
            lore = description;
        }

        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
                    .setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED")
                    .addLoreLines(ChatColor.RED + lore);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            playSound(player, Sound.ENTITY_VILLAGER_NO);
        }
    }
}
