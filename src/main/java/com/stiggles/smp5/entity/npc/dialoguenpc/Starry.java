package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
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
            if (im == null || !im.hasLocalizedName())
                return false;

            if (im.getLocalizedName().equals("nats_breath")) {
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
            if (im.getLocalizedName().equals("trog_letter")) {
                player.getInventory().getItemInMainHand().setAmount(0);
                sendMessage(player, "Oh! What's this?");
                sendMessageLater(player, "...a scientist? From EGO Labs?", 80);
                sendMessageLater(player, "He's asking me to rally for help to take a stand against Nouveau. Am I really fit for this role?", 160);
                sendMessageLater(player, "This is a lot to process... but we do need to stop Nouveau. And if Dr. Trog is asking for my help, I will do it.", 240);
                sendMessageLater(player, "I'm going to need a team.", 240);
                sendMessageLater(player, "I need someone who will be loyal. Someone who won't back out. If only my friend Phil was here... he would be perfect.", 360);
                sendMessageLater(player, "Next, I need someone creative and smart. I need someone who can invent new ideas and strategies to give us the upper hand in this battle.", 420);
                sendMessageLater(player, "I also need warriors, people who are passionate to fight.", 480);
                sendMessageLater(player, "Anarcho would be a good warrior to help us, but I don't know where he is.. He, like Nouveau, brought devastation to my homeworld... but he recognized his wrongdoings. ", 540);
                sendMessageLater (player, "I wish Drem and Anarcho were here. They both have faced Nouveau before, and they would be eager to fight", 300);
                sendMessageLater(player, "Captain Beast will be a perfect match for this fight. Thank you for finding him.", 600);
                sendMessageLater(player, "Spiffy and I need to go consult two friends of ours in Community City on this matter.", 760);
                sendMessageLater(player, "If you come across anyone who you think would be a good candidate for my team, please, give them this letter. Thank you.", 660);
                Bukkit.getScheduler().runTaskLater(main, () -> Quest.questComplete(player, Quest.QuestName.RECRUIT_STARRY, "Taking a Stand", 50), 300);
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
    public ItemStack getLetter() {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.YELLOW + "Starry's Request");
        im.setLocalizedName("starry_letter");
        im.setLore(Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + "Signed by Starry",
                ChatColor.GRAY + "Starry needs to recruit some teammates to help",
                ChatColor.GRAY + "her and Dr. Trog in the battle against Nouveau.",
                ChatColor.GRAY + "Show this letter to people who may be a good candidate:",
                ChatColor.GRAY + "1. Someone as loyal as Starry's old friend " + ChatColor.RED + "Phil",
                ChatColor.GRAY + "2. Someone who is " + ChatColor.RED + "inventive",
                ChatColor.GRAY + "3. Someone with the passion to fight Nouveau, like " + ChatColor.RED + "Anarcho or Drem",
                ChatColor.GRAY + "Report back to Starry once you have found these people."
        ));

        im.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        return item;
    }
}
