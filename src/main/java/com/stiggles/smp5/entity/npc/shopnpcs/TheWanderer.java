package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.StigglesPlayer;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.Arrays;

/**
 * Accent, if you are reading this, don't. This is a secret.
 *
 */
public class TheWanderer extends ShopNPC {

    private class Health extends StigglesBaseItem {
        public Health (int price) {
            super (price);

            item = new ItemStack(Material.FERMENTED_SPIDER_EYE);
            ItemMeta im = item.getItemMeta();
            im.setDisplayName(ChatColor.RED + "Hearts");
            im.setLore(Arrays.asList(ChatColor.GRAY + "Gain hearts back"));
            item.setItemMeta(im);
        }

        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(item);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {

        }
    }
    private class Perks extends StigglesBaseItem {
        public Perks (int price) {
            super (price);

            item = new ItemStack(Material.DRAGON_BREATH);
            ItemMeta im = item.getItemMeta();
            im.setDisplayName(ChatColor.DARK_BLUE + "Perk Upgrades");
            im.setLore(Arrays.asList(ChatColor.GRAY + "Gain stronger perks on low health"));
            item.setItemMeta(im);
        }

        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(item);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {

        }
    }
    private class Convergence extends StigglesBaseItem {
        public Convergence (int price) {
            super (price);
            item = new ItemStack (Material.AMETHYST_SHARD);
            ItemMeta im = item.getItemMeta();
            if (im == null)
                return;
            im.setDisplayName(ChatColor.BOLD + ChatColor.DARK_PURPLE.toString() + "The Curse of Clato");
            im.addEnchant(Enchantment.BINDING_CURSE, 1, true);

            im.setLore(Arrays.asList(
                    ChatColor.GRAY + "Lose half of your hearts" +  ChatColor.RED + ChatColor.UNDERLINE + "BUT",
                   ChatColor.GRAY + "gain strength when low on health.",
                    "",
                    ChatColor.RED + "Will you accept?"
                    )
            );
            item.setItemMeta (im);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            //handleTrade(player, this);
            onCursed(player);
        }
    }
    public TheWanderer (SMP5 main, String name, Location location) {
        super (main, name, location);

        setVoice (0.8f);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4NzAzMzI0MTEzMCwKICAicHJvZmlsZUlkIiA6ICJhNWVmNzE3YWI0MjA0MTQ4ODlhOTI5ZDA5OTA0MzcwMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJkZXBsZXRpbmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E2ZmQxZGMzYjNiNGY5NjcxYWUwYTc5NzVhZmZkYjNkYmE5ZWFlOTMxMzQ1YTk1ZmMyZDJmZTdjZDUwZDFiYyIKICAgIH0KICB9Cn0=",
                "V5qTnPP9VCkR/rYDB/1IM785aNboFtvBcTUkZlzYbWtOT5Oh00M8FT5HvQ4EWQLob+tKCg4tVY7/fpVdBE77buvKOgQtTWID5chPcCbGXuTsT/xdnIQm8Mqg+iMkm5CtjXSm2+KgvrQ1neibWejBvSHFU0rfL8MX+msZGwOdMd6WdvKIgZ/YitXEdipKA8Bytywiwd5LnqYiSzYADq66Cs0mjA4qy4xQiaLd8UJmU6BMs3Ai5mt711dx5FE3Ygk8PJuZfRMMM5/VAgGGODdh5epiYh/YTMFhCSpXYfqb3wnJuOg1LaeXgdAmd0wpspW6jBhGLR1gvrcovksL6q0jcr8k37hNX3AH+CXOHC9wQsxsDvq0rroUQY0HOpieAtyQfxbvh4NvTEUm1KGzeFlu/RizRtT5CUjaLdbOHtgOg679FlyedSigenarHW2l821FKTQPj7yglgADVze6V1+sOFwFntZ9HKbgamqQ9w/JjzR8GsU6ySIjct3JsYny1+IEDpAQBPCfEo3zqAAlmzNz0auHAzvsN/woqx70uIjyBFy1ehu936C+/a3YH6lYMnsTa2K7oBYD9u4T1mi1p/EmxKbj3NMnO1hKd36M7GL4zTLEmq33a8+0PLQ57NP1+yfduIRl3sKDK7/dGR95hYqMnsgryFs9BXE4XFz44dGjxwY="
        );
    }

    @Override
    public void interactDialogue(Player player) {
        speak(player, "I'm The Wanderer. I am here to pick up the pieces of this shattered world.", Sound.ENTITY_EVOKER_AMBIENT);
        speakLater(player, "Let me share my burden with you.",Sound. ENTITY_EVOKER_AMBIENT, 60);
        speakLater(player, "Nouveau did not gain control of this world without any casualties.", Sound.ENTITY_EVOKER_AMBIENT, 120);
        speakLater(player, "He was terrified-- terrified of a man named Clato. In act of fear and insanity, he killed Clato and escaped to the Convergence.",Sound.ENTITY_EVOKER_AMBIENT,  180);
        speakLater(player, "The opening of the Convergence consumed what was left of Clato. He seeks justice, but he needs your strength to do so.",Sound.ENTITY_EVOKER_AMBIENT,  270);
        speakLater(player, "Bear this curse and you will be rewarded. Beware, it comes with difficult trials. The choice is yours.", Sound.ENTITY_EVOKER_AMBIENT, 360);
        Bukkit.getScheduler().runTaskLater (main, () -> createAndShowCurse(player), 480);
    }
    @Override
    public void onInteract (Player player) {
        StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(player.getUniqueId());

        if (checkQuestItems(player))
            return;

        if (sp.isCursed()) {
            speak (player, "See that strange purple orb over there? That is a Convergence Crystal. It gives Clato power, and it will give you power.", Sound.ENTITY_EVOKER_AMBIENT);
            speakLater (player, "Go find some and come back to visit me later. I'll give you some rewards in return.", Sound.ENTITY_EVOKER_AMBIENT, 80);
            //createGUI (player);
            //showGUI (player);
        }
        else {
            interactDialogue(player);
            talk(player);
            createAndShowCurse(player);
        }
    }

    public void createGUI(Player player) {
        //AbstractItem lockedSlot = new Locked ("? ? ?");
        /* if (player has visited ruins)*/
        //lockedSlot = new Pendant(4000);

        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# # a # b # c # #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Convergence(0))
                .build ();
    }

    public void createAndShowCurse (Player player) {
        Gui curseGui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# # # # a # # # #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Convergence(0))
                .build ();

        new BukkitRunnable() {
            @Override
            public void run () {
                Window window = Window.single().setViewer (player).setGui (curseGui).setTitle ("The Curse of Clato").build ();
                window.open ();
            }
        }.runTaskLater(SMP5.getPlugin (), 4);
    }

    public void onCursed (Player player) {
        StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(player.getUniqueId());
        sp.setCursed(true);

        player.playSound(player, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 100, 0);
        player.playSound(player, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 100, 1);
        player.sendMessage(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "You have taken the Curse of Clato!");

        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0, true));

        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) == null)
            return;
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);

        player.setDisplayName(ChatColor.DARK_PURPLE + "Cursed " + ChatColor.WHITE + player.getName());

    }

}
