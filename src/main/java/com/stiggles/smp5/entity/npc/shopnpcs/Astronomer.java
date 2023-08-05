package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;

import java.util.Arrays;

public class Astronomer extends ShopNPC {
    public Astronomer(SMP5 main, String name, Location location) {
        super(main, name, location);
        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY4MTcwMDMxODkyOSwKICAicHJvZmlsZUlkIiA6ICJkYmE4OTUzOThiYTc0MzZlOTQ2YzVkZTk4N2UzZGVkNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTb21lQ29tbW9uIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNhNjIxN2I0ZTQ2NDg3YmRhYThkMmRjMGJlMTE4OGUyYzc0ZDU0ZmIzMWI2MDY1ZGRhZGVmMzBiMDhjODZkZDIiCiAgICB9CiAgfQp9",
                "XUng2ec0iJbjuHQOaz0387BcooM5eImlo4pE2hdwNA86ZToBrdMK3usS1d6hjuxIgixA/w0zz7++S/fxZ38yh9ZGhsz+7Ya29LtCUxwOT6o+AlZIg0B5cgtgEOEqkgCL6mHWCj3WP0/thZIxOfC1p5wlAGDfF+YOV9nbY1UTia0r2PDzf5fM0E5QZf0+/PLLdHRU+T3jdXwP8gz64hQVC+j4O9dxPRsEjPNpSL+GEXyQhIeLpeDFrQQfzvgnuH+4aqaIPvCMIRR9zLjsYrRm12tfr8ByESsV4OZxTFbzJZs10i0vIdwK2gxJObkxYM90lbdUGhDy85FFoO0DdIWZvCTneNCHwnZ8SqTArbsd6Tpy85xucTcy+dDjIkzWaFz6t/G7+/V7uGnVoGJYkMmkedK9JC3qiHkAlXvzcGiFhwgfE0pFWh/Fit9SC7+9vn2w5knM7f+AT5Coo0o0PQ3A8HZihgxOMiaJmXMtvufmlalvOjelxVszM2gr/hQwC0QcAdSatGBU3qF94W/OAw/OpttolYm2wHRCWbjN8MQtq3+pcICjL0BkN+dL6kLrRcvKG0Kqm33d0s2YR70Vrblp8WPOn8+AcZw6AgW4rA5GdhtDRnmz/HyooEQq8EUn7Cmi2qXeoY1ZPyWHapMPye9UXuANcsoT29WKkj7RmBmNOD0="
        );
        //setPos (-0.5, -59, 10);

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
            sendMessage(player, "Isn't the night sky just so fascinating?");
        else
            sendMessage(player, "Welcome to my observatory.");
    }

    @Override
    public void createGUI(Player player) {
        //AbstractItem lockedSlot = new Locked("Trade will be unlocked once the Ender Dragon is killed");
        AbstractItem lockedSlot = new Locked("? ? ?");

        if (Quest.isQuestComplete(player, Quest.QuestName.SMALL_STEP))
            lockedSlot = new MoonShard(20);

        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('a', new Endstone(5))
                .addIngredient('b', new PurpurBlock(5))
                .addIngredient('c', new ChorusFruit(45))
                .addIngredient('d', new ShulkerShell(800))
                .addIngredient('e', new EndRod(45))
                .addIngredient('f', new DragonHead(300))
                .addIngredient('g', lockedSlot)
                .build();
    }

    public boolean checkQuestItems(Player player) {
        if (player.getInventory().getItemInMainHand().hasItemMeta()) {
            ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
            if (im != null && im.hasDisplayName() && im.getLocalizedName().equals("meteor_core")) {
                player.getInventory().getItemInMainHand().setAmount(0);
                sendMessage(player, "Ah, how interesting... this meteor core reveals so much. Let me process a sample of it quick...");
                sendMessageLater(player, "Hmmm, just as I predicted. That meteor had traces of Convergence within it, and it seems to have anti-gravity properties. Did you happen to notice that the impact site had lighter gravity?", 80);
                sendMessageLater(player, "It appears that Convergence may different effects based on what other elements it is mixed with.", 160);
                sendMessageLater(player, "Here, I will give you this processed sample. Can you take it to Dr. Trog of EGO Labs? He may find it useful for his research...", 200);
                Bukkit.getScheduler().runTaskLater(main, () -> player.getInventory().addItem(getDust()), 200);
                return true;
            }
        }
        return false;
    }

    public ItemStack getDust() {
        ItemStack i = new ItemStack(Material.GLOWSTONE);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ChatColor.YELLOW + "Meteor Dust");
        im.setLore(Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + ChatColor.ITALIC.toString() + "Processed Meteor Debris",
                ChatColor.GRAY + ChatColor.ITALIC.toString() + "The Astronomer asked you to deliver this to Dr. Trog."));
        im.setLocalizedName("moon_dust");
        i.setItemMeta(im);
        return i;
    }

    private class ShulkerShell extends StigglesBaseItem {
        public ShulkerShell(int price) {
            super(price);
            item = new ItemStack(Material.SHULKER_SHELL);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.SHULKER_SHELL).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Endstone extends StigglesBaseItem {
        public Endstone(int price) {
            super(price);
            item = new ItemStack(Material.END_STONE);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.END_STONE).addLoreLines(getCost());
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
            item = new ItemStack(Material.PURPUR_BLOCK);
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
            item = new ItemStack(Material.END_ROD);
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
            item = new ItemStack(Material.DRAGON_HEAD);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.DRAGON_HEAD).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class LunarBoots extends StigglesBaseItem {
        public LunarBoots(int price, String localName) {
            super(price, localName);
            item = new ItemStack(Material.IRON_BOOTS);
            ItemMeta im = item.getItemMeta();
            im.setDisplayName(ChatColor.AQUA + "Lunar Boots");
            im.addEnchant(Enchantment.PROTECTION_FALL, 13, true);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.IRON_BOOTS)
                    .setDisplayName(ChatColor.AQUA + "Lunar Boots")
                    .addLoreLines(getCost())
                    .addEnchantment(Enchantment.PROTECTION_FALL, 13, true);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class MoonShard extends StigglesBaseItem {
        public MoonShard(int price) {
            super(price);
            item = new ItemStack(Material.PRISMARINE_SHARD);
            ItemMeta meta = item.getItemMeta();
            meta.setUnbreakable(false);
            meta.setDisplayName(ChatColor.AQUA + "Moon Shards");
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.setLore(Arrays.asList(
                    String.valueOf(ChatColor.GRAY),
                    ChatColor.AQUA + "-- SPECIAL ITEM --",
                    ChatColor.GRAY + "This is pointless by itself,",
                    ChatColor.GRAY + "but can be used to craft",
                    ChatColor.GRAY + "the Lunar Armor Set."));
            meta.setLocalizedName("moon_shard");
            item.setItemMeta(meta);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item)
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
