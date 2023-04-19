package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.main.SMP5;
import de.studiocode.invui.gui.builder.GUIBuilder;
import de.studiocode.invui.gui.builder.guitype.GUIType;
import de.studiocode.invui.item.ItemProvider;
import de.studiocode.invui.item.builder.ItemBuilder;
import de.studiocode.invui.item.impl.SimpleItem;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Baggins extends ShopNPC {
    private class Iron extends StigglesBaseItem {
        public Iron (int price) {
            super (price);
            item = new ItemStack (Material.IRON_INGOT);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Gold extends StigglesBaseItem {
        public Gold (int price) {
            super (price);
            item = new ItemStack(Material.GOLD_INGOT);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Lapis extends StigglesBaseItem {
        public Lapis (int price) {
            super (price);
            item = new ItemStack(Material.LAPIS_LAZULI);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Diamond extends StigglesBaseItem {
        public Diamond (int price) {
            super (price);
            item = new ItemStack(Material.DIAMOND);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Amethyst extends StigglesBaseItem {
        public Amethyst (int price) {
            super (price);
            item = new ItemStack(Material.AMETHYST_SHARD);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Copper extends StigglesBaseItem {
        public Copper (int price) {
            super (price);
            item = new ItemStack(Material.COPPER_INGOT);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Bell extends StigglesBaseItem {
        public Bell (int price) {
            super (price);
            item = new ItemStack(Material.BELL);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    //Other possible items: Concrete, Candles, Frog lights (UNLOCKABLE)

    public Baggins (SMP5 main, String name) {
        this (main, name, new Location(Bukkit.getWorld("world"), -0.5, 73, 10));
    }
    public Baggins (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4MTk0MzkwNjg3OSwKICAicHJvZmlsZUlkIiA6ICJmNWYyNDcyZGVhNDY0ODJhYWUwNDllYjM2ODE5ODU0NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJEZXRhaWxlcnMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg2MzNhMmNmNGIyZTY2ODc0YzdhYmQ5YzlkYmQwYTljNWRiOTEyMDVhOTM4Y2JjOGM1MzQ3MjY0MmYzNjMwMiIKICAgIH0KICB9Cn0=",
                "iaxpVo6iKYZJQqSfnVdp1bclWSxkEsjwGGZ4wthwr4PupLePWEEUgb5oE7j24bgdLE+iD4zze2S9fRptVBoBJGiYrgBwhxLlSMVYNARHETNUYhPY34Nw8Wrplzg+zbtoX1LWR5tAAF/A8E9NII9i/AIVJBGqbtjh0sqIKtMkTKRmo2Ot/Pn8RFLUuAbErbhBtJpYjRSle1q4Ub3mmu2PU31RScIkeZ7Rk331JPsEmNkdCynIVLC0lWkLLIr64AmIjMMOyiBUBuIE1vrFtxvgAmDST6ZFVZoR9LldYnej2PIMxtbYgoIqpzxwSk5CHIyGTX5pyaAHU0Y13WaDqoOfzYFmG4DO52msHvgnwjBKIOzugJHvUSjWp2HIzi1Yx94tCRtSrZDv7KLzkrNGgEoQ2zIpMAtdQflnboXJp88yR3tqZzPiW0IAuBy8/0gCVR+Y04NAInPTh1QhF4iCqOFsV4zs2ub5Xc5YOMZQqWsIhekoxtvv6WnsrkQ8VEkZ/DECe02hg52A4zSXLr2kDg0fCkRIjrT4DDiXuSoZeue4ucEg+t1W7LlP448CwDrghSnJTvDNg6Iez1/+NgbzYt1NdapixHSN4B5kBNU8r70vWkCxjYyMMMF4EdQe9fFNlGkQM8HAIMKxjXgkaLPikK+ESkMRea+qUHUjNp+yD9iffc8="
        );
    }

    @Override
    public boolean handleTrade (Player player, StigglesBaseItem item) {
        if (super.handleTrade(player, item)) {
            sendMessage(player, "Thank you sir.");
            return true;
        }
        sendMessage(player, "Hey, you don't have enough money!");
        return false;
    }
    @Override
    public void onInteract(Player player) {
        interactDialogue(player);
        createGUI(player);
        showGUI(player);
    }

    @Override
    public void interactDialogue(Player player) {
        int n = new Random().nextInt();
        if (n % 2 == 0)
            sendMessage(player, "");
        else
            sendMessage(player, "");
    }

    @Override
    public void createGUI(Player player) {
        gui = new GUIBuilder<>(GUIType.NORMAL)
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Copper (20))
                .addIngredient( 'b', new Amethyst (8))
                .addIngredient( 'c', new Iron (40))
                .addIngredient( 'd', new Gold (50))
                .addIngredient( 'e', new Diamond (100))
                .addIngredient( 'f', new Lapis(30))
                .addIngredient( 'g', new Bell (100))
                .build ();
    }
}
