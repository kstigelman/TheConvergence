package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.PotionBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

import java.util.Arrays;

public class Starry extends StigglesNPC {
    public Starry(SMP5 main) {
        super(main, "Starry");

        setSkin(
                "ewogICJ0aW1lc3RhbXAiIDogMTY3MTY0NTIwOTYxMCwKICAicHJvZmlsZUlkIiA6ICJjZDc1OTJmNzBlYmE0MGIyODFiNjRhNDk3YzAzYTdhMSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdGFycnlfUGhvZW5peCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lMDFkNGE0ZmE3NzRmMTY2NjljYmFkYmRmMjFiZjNlOGFkNDgxMGRiZThmZmFhZDU3YWE1MmM5NzA4MTlkMDciCiAgICB9LAogICAgIkNBUEUiIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIzNDBjMGUwM2RkMjRhMTFiMTVhOGIzM2MyYTdlOWUzMmFiYjIwNTFiMjQ4MWQwYmE3ZGVmZDYzNWNhN2E5MzMiCiAgICB9CiAgfQp9",
                "p8OLoYFlwB1OOERA6obVq+km/EfyEV/qTEpc6yayvRxXdN4eE9j45aBmap5W0ulJoL0By4yd5AhySLJHCBFCUt9fCuBZQkxT7foI2ZRSMRk3U/f4Pm6oH10Gi8OqOtJc+aH3A7L3HdCZXijjZtIUsN2/fF4Jvs01JQuN+e0PHP10KcwBygdR93U3euDD+xheURYx7DNb3wbSIpPELj6Wp4TpGHL83LmPcKBbSJag9dIA80jiScjNq74whrDmzyObsDR1Umo77pkiCjqvGV9pIZIMIwNzU112DHENPJFcFhTyuNO7W+dpjYh+pSpBAXa06xBwzoXsMeqEVwUqpFLFy+BV/mgL4wnXTbPZfDxXNVneUs+2y+/nY3Fgbk9sKwf952xff9ProEXI9tjAMAbA3/ZUHmFv9b5quHJ9YhnfqIf48Fw4GX/pfw0eMhRyN8LwyKB97Y7arjOWm7h4BNoYJIwvZvSP+nGDiNKl2FqbWAFGpLPRf145pD0vIQ/CewAg/tV2tfoEC01d8HvpnnotWoRmCgIWVq0j9OcBVFxme8c9u4+b50XEpbiRLPGpBPkfk4EW4oF6tLw6SuhdcOTfKx9yjY4tOZeWiBDp1yX3/ZByzclIdfbhIqbmsoAu7zojRX0rfRrLVxaWLhrO225eC1SHSfGnZ7iLIcn5h4g2D40="
        );
    }

    public Starry(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin(
                "ewogICJ0aW1lc3RhbXAiIDogMTY3MTY0NTIwOTYxMCwKICAicHJvZmlsZUlkIiA6ICJjZDc1OTJmNzBlYmE0MGIyODFiNjRhNDk3YzAzYTdhMSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdGFycnlfUGhvZW5peCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lMDFkNGE0ZmE3NzRmMTY2NjljYmFkYmRmMjFiZjNlOGFkNDgxMGRiZThmZmFhZDU3YWE1MmM5NzA4MTlkMDciCiAgICB9LAogICAgIkNBUEUiIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIzNDBjMGUwM2RkMjRhMTFiMTVhOGIzM2MyYTdlOWUzMmFiYjIwNTFiMjQ4MWQwYmE3ZGVmZDYzNWNhN2E5MzMiCiAgICB9CiAgfQp9",
                "p8OLoYFlwB1OOERA6obVq+km/EfyEV/qTEpc6yayvRxXdN4eE9j45aBmap5W0ulJoL0By4yd5AhySLJHCBFCUt9fCuBZQkxT7foI2ZRSMRk3U/f4Pm6oH10Gi8OqOtJc+aH3A7L3HdCZXijjZtIUsN2/fF4Jvs01JQuN+e0PHP10KcwBygdR93U3euDD+xheURYx7DNb3wbSIpPELj6Wp4TpGHL83LmPcKBbSJag9dIA80jiScjNq74whrDmzyObsDR1Umo77pkiCjqvGV9pIZIMIwNzU112DHENPJFcFhTyuNO7W+dpjYh+pSpBAXa06xBwzoXsMeqEVwUqpFLFy+BV/mgL4wnXTbPZfDxXNVneUs+2y+/nY3Fgbk9sKwf952xff9ProEXI9tjAMAbA3/ZUHmFv9b5quHJ9YhnfqIf48Fw4GX/pfw0eMhRyN8LwyKB97Y7arjOWm7h4BNoYJIwvZvSP+nGDiNKl2FqbWAFGpLPRf145pD0vIQ/CewAg/tV2tfoEC01d8HvpnnotWoRmCgIWVq0j9OcBVFxme8c9u4+b50XEpbiRLPGpBPkfk4EW4oF6tLw6SuhdcOTfKx9yjY4tOZeWiBDp1yX3/ZByzclIdfbhIqbmsoAu7zojRX0rfRrLVxaWLhrO225eC1SHSfGnZ7iLIcn5h4g2D40="
        );
    }

    /*
        @Override
        public void onInteract (Player player) {
            if (checkQuestItems(player))
                return;
           interactDialogue (player);
        }
    */
    public void interactDialogue(Player player) {
        String msg = "Welcome to the Spectral Saloon!";


        if (player.getName().contains("Starry_Phoenix")) {
            msg += "Wait-- you're me!?!? How?";
            sendMessage(player, msg);
            return;
        }
        int ni = main.getRandom() % 9;

        if (ni <= 2)
            msg += " Stay as long as you'd like";
        else if (ni <= 5)
            msg += " Be sure to get some of our famous Moonshine!";
        else
            msg += " We're happy to have you!";

        sendMessage(player, msg);
    }

    public boolean checkQuestItems(Player player) {
        if (player.getInventory().getItemInMainHand().hasItemMeta()) {
            ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
            if (im != null && im.hasDisplayName() && im.getLocalizedName().equals("nats_breath")) {
                player.getInventory().getItemInMainHand().setAmount(0);
                sendMessage(player, "...");
                Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "Where did you find that?"), 40);
                Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "That sword belonged to an old hero from my home. His name was Drem."), 80);
                Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "People around here say they think they've seen him living alone in the mountains up north. At least, someone that looks like him."), 160);
                Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "Please, take this journal entry of mine. If you happen to see Drem, please give this to him. Thank you."), 240);
                ItemStack book = new ItemStack(Material.BOOK);
                ItemMeta bookMeta = book.getItemMeta();
                bookMeta.setDisplayName(ChatColor.YELLOW + "Starry's Journal: Drem vs Nouveau");
                bookMeta.setLocalizedName("drem_book");
                bookMeta.setLore(Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + ChatColor.ITALIC.toString() + "Authored by Starry. It details the battle between Drem and Nouveau.",
                        ChatColor.GRAY + ChatColor.ITALIC.toString() + "People claim to have seen a man that looks ",
                        ChatColor.GRAY + ChatColor.ITALIC.toString() + "like " + ChatColor.RED + "Drem" + ChatColor.GRAY + ChatColor.ITALIC + " somewhere in the snowy mountains."));
                book.setItemMeta(bookMeta);
                player.getInventory().addItem(book);
                return true;
            }
        }
        return false;
    }

    private class Moonshine extends AbstractItem {

        /*public Moonshine(ItemProvider itemProvider, String command) {
            super(itemProvider, command);
        }*/

        public ItemProvider getItemProvider() {
            return new PotionBuilder(PotionBuilder.PotionType.NORMAL)
                    .setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Moonshine")
                    .addLoreLines("World famous Moonshine from the Spectral Saloon!")
                    .addEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 1))
                    .addEffect(new PotionEffect(PotionEffectType.POISON, 200, 1))
                    .addEffect(new PotionEffect(PotionEffectType.LUCK, 60, 1))
                    .addEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 1));
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            player.getInventory().addItem(getItemProvider().get());
        }
    }
}
