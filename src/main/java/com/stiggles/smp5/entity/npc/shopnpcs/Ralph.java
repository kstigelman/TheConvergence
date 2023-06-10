package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.Colors;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.builder.PotionBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;

public class Ralph extends ShopNPC {

    private class Pickaxe extends StigglesBaseItem {
        public Pickaxe (int price) {
            super(price);
            if (ri % 15 <= 8)
                item = new ItemStack(Material.IRON_PICKAXE);
            else if (ri % 15 <= 12) {
                item = new ItemStack(Material.DIAMOND_PICKAXE);
                cost *= 1.8;
            } else {
                item = new ItemStack(Material.GOLDEN_PICKAXE);
                cost *= 0.7;
            }
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Shovel extends StigglesBaseItem {
        public Shovel (int price) {
            super(price);
            if (ri % 15 <= 8)
                item = new ItemStack(Material.IRON_SHOVEL);
            else if (ri % 15 <= 12) {
                item = new ItemStack(Material.DIAMOND_SHOVEL);
                cost *= 1.8;
            } else {
                item = new ItemStack(Material.GOLDEN_SHOVEL);
                cost *= 0.7;
            }
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class Wool extends StigglesBaseItem {
        public Wool (int price) {
            super (price);
            Material material;
            int n = (ri % 16);

            if (n < 0)
                n = 0;
            material = Material.valueOf((Colors.getColor (n) + "_WOOL"));
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

    private class Scaffold extends StigglesBaseItem {
        public Scaffold (int price) {
            super (price);
            item = new ItemStack(Material.SCAFFOLDING, 64);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.SCAFFOLDING)
                    .setAmount(64)
                    .addLoreLines(this.getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Froglight extends StigglesBaseItem {
        public Froglight (int price) {
            super (price);

            int n = ri % 3;
            if (n == 0)
                item = new ItemStack(Material.OCHRE_FROGLIGHT);
            else if (n == 1)
                item = new ItemStack(Material.VERDANT_FROGLIGHT);
            else
                item = new ItemStack(Material.PEARLESCENT_FROGLIGHT);
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
    private class Concrete extends StigglesBaseItem {
        public Concrete (int price) {
            super (price);
            Material material;
            int n = (ri % 16);

            if (n < 0)
                n = 0;
            material = Material.valueOf((Colors.getColor (n) + "_CONCRETE"));
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
            playSound(player, Sound.ENTITY_VILLAGER_NO);
        }
    }

    public Ralph (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4NTk4NDEyNDYxMSwKICAicHJvZmlsZUlkIiA6ICJhYWQzYmVlNGNhYzI0ZGQyODJlZTQxOWRjNzQ1ZmE0MiIsCiAgInByb2ZpbGVOYW1lIiA6ICJPc2hpXzAzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNlMjk2YjNjZWM0ZmY0MmNkMGU2OTQxZjA4NWUwMTcxZWYxYWY5NjI2MzE1MDMyNzU0Y2M2YjUzZTE0ZWZmYzAiCiAgICB9CiAgfQp9",
                "ageu9DbJhKhSHXDXG1HHXeLaukMF6tu5H5+IHj6kBYxveuOP3retZnYwvt2S1l6DyNNdZoDRs7Oy/L7Ypy+wvoCIzZDnNfCBADXUtjUXVdCBgIJyTebNkY+bCKcg1CaGWS78foBHL1h7iRHAWOlmws7dIIiW1pJB2oNkJDcWLk25wNnIKYyJBQNLgwE63zQYqSyjPD3ruwq8rWFFRuAJe7AbwKChI/0m/91GitoZfC1nIhjTW6XbW1QS8TQOxdJ81qrgJIOgXI2q4F8kdh5XaY+jsrkcv0WZ5bwNUYv0Upg7HdVVRUvG8ZsX/u5Ix/euarx5L96z+rUcbK92a7mAwL1+/aAWh3LKvQiNPmttPa2DNsXeBS27F+SI696h2OMud80VvmYTyK63ZS8yaB1RNiprnNWzYEfS9pJJLkFvxdlitlpOdDFPchFsPqFv8uV61MC5zTg/XDTaPVXlGsG8hKZxEu32LL/6bePyHQkrBFlY6GcYW+4GrnHnHVISwIoStHF51FOV+lRKUQHcD3DkmiDmPCCyh6LIzbbC2IdEY7uyN6QY51yAhIdKsa40l/Wf26CrfcX0/QQRvlDhQ2VltGMpVKMzm4rOhjoyhpSnFkxoiv9uZWAhl4oiRwSy+UV2dt0Ibj5MsJdRH8XxGvwAN1N58QwPonFGzySHMq2GkW8="
        );
    }

    @Override
    public boolean handleTrade (Player player, StigglesBaseItem item) {
        if (super.handleTrade(player, item)) {
            sendMessage(player, "Thanks for doing business!");
            return true;
        }
        sendMessage(player, "Sorry, you can't buy that.");
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
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Scaffold(20))
                .addIngredient( 'b', new Froglight(100))
                .addIngredient( 'c', new Concrete(4))
                .addIngredient( 'd', new Wool (10))
                .addIngredient( 'e', new Pickaxe(70))
                .addIngredient( 'f', new Shovel (30))
                .addIngredient( 'g', new Locked("? ? ?"))
                .build ();
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Hello. What can we help ya build?");
    }
}
