package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
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
            super (price);
            ItemMeta meta = item.getItemMeta();
            if (meta == null)
                return;
            meta.setLocalizedName("vlad");
            item.setItemMeta(meta);
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
            super (price);
            ItemMeta meta = item.getItemMeta();
            if (meta == null)
                return;
            meta.setLocalizedName("boom_bow");
            item.setItemMeta(meta);
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
            item = new ItemStack(Material.CHARCOAL);
            ItemMeta meta = item.getItemMeta();
            if (meta == null)
                return;
            meta.setLocalizedName("pendant");
            item.setItemMeta(meta);
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
    public Drem (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4MjE5NTQ5NDI0OCwKICAicHJvZmlsZUlkIiA6ICIzNzdmYzE2NzE2ZDY0NDM4YjNkYjQzYzYyOWExYTkyOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJTd2VkZW5zdHlsZTM0IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2MxZjYxODRmMDQyZjc1NTdlMjA1OGYzZDllOWVjMmFjY2UyNzU0YzRhYzFjMTFkZGI5MWZmMmU2YjAyNjhhZGEiCiAgICB9CiAgfQp9",
                "N083dFQnybPFkzN+Za1SOk4WSM387hIIGKOklJ/PP3qq3JLOFKewvbPNCQqWI65sbVmVkZn6vrBDcnfa0Gsf49G2ihi5T5n15Wn8VCeU45xg4/cpmTulzEnLq1dX1aKcJ7WTWp0XWdmb60CmaSk7lllECAqHkmNLNz6wq32kge2jG0pHkK2+Hym7UCib2CctSM92gkqqG48zn3hLmVME6c1T+SPsGZS/V1CioAJd83Mv2+Egn5hygNoGvwHnatkSKIkK8WkZdmV2eSegLvX+V+im9ZnFFfILow5Sz9XisQwp8+X6Ew5iXco5eOuzcKflntdkGdva2jpox6BcgfHIxswwWKrPcMdrs6tgb8wFSyQ9r0Wt5W8OppdBsKkE/DAWBSSxg0YmXpG6SeBUJ0K4j2DB/l6AiJWN1AcMqSejehxMeyLrWqSYZczMcncxZ7i+/eal8LhtZTVye9fD8yEuClsqLqY/Fb9mgJSmaNhDkcJaQ19+SyAvUK0tWfQjmYUws1hfyA7U6TBvNvNPsZSjd8e6zpaNeqGLxu72CIyFFTJc4SiCTGFUd2Ly1JEwr7H2ZC3ziMa0gJ0QkVhC2Hr8vXvUci8dScd8nVKScgPMk/eoIWecgxj1ERTkaSzJPV/JiTYTIDGwzT3YyR2rncTa4KDEKpeG2k2GpfO0rzFikao="
        );
        //setRotation (110, 0);
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

        sendMessage (player, msg);
        interactCounter++;
        talk (player);
    }

    @Override
    public void createGUI(Player player) {
        AbstractItem lockedSlot = new Locked ("Find the Captain's logbook");
        /* if (player has visited ruins)
             lockedSlot = new Pendant(4000); */

        gui = Gui.normal()
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
