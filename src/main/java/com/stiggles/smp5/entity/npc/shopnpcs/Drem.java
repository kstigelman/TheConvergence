package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.entity.npc.shopnpcs.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import de.studiocode.invui.gui.builder.GUIBuilder;
import de.studiocode.invui.gui.builder.guitype.GUIType;
import de.studiocode.invui.item.ItemProvider;
import de.studiocode.invui.item.builder.ItemBuilder;
import de.studiocode.invui.item.impl.BaseItem;
import de.studiocode.invui.item.impl.SimpleItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Drem extends ShopNPC {

    int interactCounter = 0;


    private class Warhorn extends StigglesBaseItem {
        public Warhorn (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.GOAT_HORN)
                    .addLoreLines("Get a random goat horn.")
                    .addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Saddle extends StigglesBaseItem {
        public Saddle (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.SADDLE)
                    .addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class HorseArmor extends StigglesBaseItem {
        public HorseArmor (int price) {
            super (price);

            Material material;
            int n = ri % 10;
            if (n == 0)
                material = Material.DIAMOND_HORSE_ARMOR;
            else if (n <= 2) {
                material = Material.GOLDEN_HORSE_ARMOR;
                cost = price * 2 / 3;
            }
            else if (n <= 6) {
                material = Material.IRON_HORSE_ARMOR;
                cost = price / 2;
            }
            else {
                material = Material.LEATHER_HORSE_ARMOR;
                cost = price / 4;
            }

            item = new ItemStack(material);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.DIAMOND_HORSE_ARMOR)
                    .addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class DragonBreath extends StigglesBaseItem {
        public DragonBreath (int price) {
            super (price);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.DRAGON_BREATH)
                    .setDisplayName(ChatColor.DARK_PURPLE + "Natalie's Breath")
                    .addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Vlad extends StigglesBaseItem {
        public Vlad (int price) {
            super (price, "vlad");
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.CROSSBOW)
                    .setDisplayName(ChatColor.GRAY + "Vladmir")
                    .addLoreLines("Nouveau's impaling crossbow")
                    .addLoreLines(getCost())
                    .addEnchantment(Enchantment.PIERCING, 5, true);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class BoomBow extends StigglesBaseItem {
        public BoomBow (int price) {
            super (price, "boom_bow");
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.CROSSBOW)
                    .setDisplayName(ChatColor.RED + "Boom Bow")
                    .addLoreLines("This bow has an explosive personality")
                    .addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Pendant extends StigglesBaseItem {
        public Pendant (int price) {
            super (price, "pendant");
            /* We need to make sure that the player gets the horse armor back once horse is removed.
                or, what if the pendant is a 1 time use to spawn a horse?
             */
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.CHARCOAL)
                    .setDisplayName(ChatColor.DARK_PURPLE + "Natalie's Pendant")
                    .addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Locked extends BaseItem {
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
    public Drem (SMP5 main) {
        super (main, "Drem");

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY3MTgyMDMxMTk0MSwKICAicHJvZmlsZUlkIiA6ICI2MTJmZDAxMWY4YTQ0ZGIwOTU3ZTNjM2MyZTBkYmFlZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYWxlbnRhZG94MTIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjM4NGE2Yzc3MmExY2MzMjU0NzFmNjhmODE2ODQwZDIzMzMzYTdjOTE5YjQzYTk1Y2M4NjkxMzY5NmQ1NWE0OCIKICAgIH0KICB9Cn0=",
                 "VzSj1fXlwV+gCEAGoJVaHSVOj0hgP0RHReUPcXSSDxNovRZxEs1k1iFkcHEIpBKh+On5omNhLGqP2PzXA8rBJLu+TVnM5EhxT+mcEZK49vcGVSbz52X4mF7sobvFDHIIhEAIMXvjZMM81gYE//Xe38WTl7wN7ZYIA5BMDn+HUNdvgIEfIaKFbsBiPxQQpttdzbMyJPU+7yTKixuPhOHAo4yhKmyNUW3wKcbLEgpPTt8asoYaToLEXgXwOoU7AFvBHYNi1DVNfaXaDlC8IDUDNu+YwrCcbonHd8v2J2MPE9w1sizDWegnK0AdUsG303OQ8Ukjxm0Bh689NNb5H6X1D8fiTHTD44NQhrWlO7GEDJAPcuo5/w26+T1gMaXrZByJIgtRdOrFCUiGAr/4rSspVj86YYvgji49c7W8pA6lUk1WTufiNcEOFprrQbtIYBOvKf3HAlY9J1IdKHgHGj+n9+OFV4QBiYPgH8QSSCE7H7i8MxvcvF3T3IODuzumv7EdRF46ur20ztVOW+j/kNC0pWCTbUg2r2GzDbIfUuSrlVm5MWltqkvE6f64k8/qEaEu2EWwF3W2mjCxYWMHUjS6XbEGpwDlhPv0jIAIGRNlQGmIojiA0jQsTSQ++LTyTJCOCtELK6CoW3cwqhAMw+XIlpdrH2ZsWvSUVH/q+yh/xo0="
        );
        setPos (-0.5, -59, 3.5);
    }


    @Override
    public void interactDialogue(Player player) {
        String msg = "";
        if (interactCounter == 0)
            msg = "Hello?";
        if (interactCounter == 1)
            msg = "You should leave.";
        if (interactCounter == 2) {
            msg = "I'm warning you.";
            interactCounter = 0;
        }

        player.sendMessage ("<" + getName () + "> " + msg);
        interactCounter++;

    }
    @Override
    public void onInteract (Player player) {
        interactDialogue(player);
    }

    @Override
    public void createGUI(Player player) {
        BaseItem lockedSlot = new Locked ("Find the Captain's logbook");
        /* if (player has visited ruins)
             lockedSlot = new Pendant(4000); */

        gui = new GUIBuilder<>(GUIType.NORMAL)
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Saddle (150))
                .addIngredient( 'b', new HorseArmor(200))
                .addIngredient( 'c', new Warhorn(300))
                .addIngredient( 'd', new DragonBreath(400))
                .addIngredient( 'e', new Vlad (1800))
                .addIngredient( 'f', new BoomBow (3500))
                .addIngredient( 'g', lockedSlot)
                .build ();
    }
}
