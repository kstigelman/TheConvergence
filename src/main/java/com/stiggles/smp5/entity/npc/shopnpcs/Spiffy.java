package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import de.studiocode.invui.gui.builder.GUIBuilder;
import de.studiocode.invui.gui.builder.guitype.GUIType;
import de.studiocode.invui.item.ItemProvider;
import de.studiocode.invui.item.builder.ItemBuilder;
import de.studiocode.invui.item.builder.PotionBuilder;
import de.studiocode.invui.item.impl.BaseItem;
import de.studiocode.invui.item.impl.SimpleItem;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Spiffy extends ShopNPC {
    /* Set a new random integer "seed" on every instance.
     * Server is planned to restart daily, so the NPC's
     * shop will update daily.
     */
    Random random = new Random();
    private class Moonshine extends StigglesBaseItem {
        public Moonshine (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new PotionBuilder(PotionBuilder.PotionType.NORMAL)
                    .setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Moonshine")
                    .addLoreLines ("World famous Moonshine from the Spectral Saloon!")
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

        public Sand () {
            Material material;
            int n = random.nextInt() % 8;
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
    private class Dagger extends StigglesBaseItem {
        public Dagger (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.GOLDEN_SWORD)
                    .setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Gilded Dagger")
                    .addLoreLines ("Passed down through generations of the Morabito clan.")
                    .addEnchantment(Enchantment.DURABILITY, 10, false)
                    .addEnchantment(Enchantment.DAMAGE_ALL, 5, false)
                    .addEnchantment(Enchantment.SWEEPING_EDGE, 7, false);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Locked extends BaseItem {
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                    .setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED")
                    .addLoreLines(ChatColor.RED + "Find the Morabito's hideout");
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
        }
    }

    public Spiffy (SMP5 main, String name) {
        super (main, name, new Location(Bukkit.getWorld("world"), -0.5, 73, 10));
        setSkin (
                "ewogICJ0aW1lc3RhbXAiIDogMTY4MDgwNDc2MDQ5MSwKICAicHJvZmlsZUlkIiA6ICI3ZDJhY2YzOGQ3YTQ0YjU0YTliMGNkYTZhNzk1YmNmYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb3VuY2luZXNzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2EyNWZmMmEwOTU2YWMzYmYzNTBmNDU4YThlNjQyZDkyZGEyMDI3ZDNkMTA4MTYxODE1MjE3NzFiOGRkZWZhNTAiCiAgICB9CiAgfQp9",
                "PfIcTJoUTa0EvNemdy9V4ymTa6hEjQmHwUfnjml8l4XGBJq1JwRTf+pd7Yomc6mGLtlcjzzrQBFtcDs6IBVLqpx4eN3ZOuxobcLCJ2DibSN0sPsOwwMJ2KVXcu4jyVZ1cyC5Rba8aS8DJyWzExw/oO1Gsq+CWKgkSs8cootdrrmdrg2MvIT5BicHOIdHO7sj6lClfOuSFPsVrN/NYbP//91IGNkxFsPzAeB5gljnIOH44X2yjZqv7BqdJ6we9okrCiTDuByQg1I8eHl1D22tdh2Gt8lCvTBwNnQvECsLxTbcGaB3nF/Uno089vvH+VT09daGZiom2Q25bhtnRfg/U13fz5r1etJpHctwsE2vhNewtR7kCL8Vjgp2eJ/QFpk3KIong1q63867tjPEsSXgqS2l4+JJBVb7W91hIzOoV785zoRlBkXs16n2/P/pfw3V+zDinPAeecGJRLgrkB1K/pwffzFL5ACsUUfYP9HSnqAVQ/FZWdBmAV3sHOzPMgZOj8nCjw1BYzd1S7fGt2w6WwQrts+l8z3l2fIzuKnEmlDgQ5nkhVgBpbJJTMKx9mI+n099OT4LgwFE439YM0tYCOn2MuxiVxhEHxFHzyJQ/VSkhYIJxtbc1Fw2METCCihnWnqAdoJvQc2L1YdR33/x+TzEExZaiDMXaJPrH1a+tnc="
        );
        setPos (-0.5, -59, 2);

    }

    public void handleTrade (Player player, StigglesBaseItem item) {
        if (BankManager.withdraw(player, item.cost)) {
            player.getInventory().addItem(item.getItemProvider().get());
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 1.f, 1.f);
            sendMessage(player, "Enjoy.");
            return;
        }
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
        sendMessage(player, "Sorry, you don't have enough money for that.");
    }
    @Override
    public void onInteract(Player player) {
        interactDialogue(player);
        createGUI(player);
        showGUI(player);
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Welcome! How can I help you?");
    }

    @Override
    public void createGUI(Player player) {
        BaseItem lockedSlot = new Locked();
        if (player.getStatistic(Statistic.FISH_CAUGHT) >= 1000)
            lockedSlot = new Dagger (1000);

        gui = new GUIBuilder<>(GUIType.NORMAL)
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Moonshine(50))
                /*.addIngredient( 'b', new Mister8Bit.CoralBlock(80))
                .addIngredient( 'c', new Mister8Bit.Coral(80))
                .addIngredient( 'd', new Mister8Bit.CoralFan(80))
                .addIngredient( 'e', new Mister8Bit.PrismarineCrystal(40))
                .addIngredient( 'f', new Mister8Bit.PrismarineShard(40))*/
                .addIngredient( 'g', lockedSlot)
                .build ();
    }
}
