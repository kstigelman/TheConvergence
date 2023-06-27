package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.StigglesPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;

import java.util.Arrays;

/**
 * Accent, if you are reading this, don't. This is a secret.
 */
public class TheWanderer extends ShopNPC {

    public TheWanderer(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY4NzAzMzI0MTEzMCwKICAicHJvZmlsZUlkIiA6ICJhNWVmNzE3YWI0MjA0MTQ4ODlhOTI5ZDA5OTA0MzcwMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJkZXBsZXRpbmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E2ZmQxZGMzYjNiNGY5NjcxYWUwYTc5NzVhZmZkYjNkYmE5ZWFlOTMxMzQ1YTk1ZmMyZDJmZTdjZDUwZDFiYyIKICAgIH0KICB9Cn0=",
                "V5qTnPP9VCkR/rYDB/1IM785aNboFtvBcTUkZlzYbWtOT5Oh00M8FT5HvQ4EWQLob+tKCg4tVY7/fpVdBE77buvKOgQtTWID5chPcCbGXuTsT/xdnIQm8Mqg+iMkm5CtjXSm2+KgvrQ1neibWejBvSHFU0rfL8MX+msZGwOdMd6WdvKIgZ/YitXEdipKA8Bytywiwd5LnqYiSzYADq66Cs0mjA4qy4xQiaLd8UJmU6BMs3Ai5mt711dx5FE3Ygk8PJuZfRMMM5/VAgGGODdh5epiYh/YTMFhCSpXYfqb3wnJuOg1LaeXgdAmd0wpspW6jBhGLR1gvrcovksL6q0jcr8k37hNX3AH+CXOHC9wQsxsDvq0rroUQY0HOpieAtyQfxbvh4NvTEUm1KGzeFlu/RizRtT5CUjaLdbOHtgOg679FlyedSigenarHW2l821FKTQPj7yglgADVze6V1+sOFwFntZ9HKbgamqQ9w/JjzR8GsU6ySIjct3JsYny1+IEDpAQBPCfEo3zqAAlmzNz0auHAzvsN/woqx70uIjyBFy1ehu936C+/a3YH6lYMnsTa2K7oBYD9u4T1mi1p/EmxKbj3NMnO1hKd36M7GL4zTLEmq33a8+0PLQ57NP1+yfduIRl3sKDK7/dGR95hYqMnsgryFs9BXE4XFz44dGjxwY="
        );
    }

    @Override
    public void interactDialogue(Player player) {

    }

    @Override
    public void onInteract(Player player) {
        StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(player.getUniqueId());
        if (sp.isCursed()) {

        }
        if (checkQuestItems(player))
            return;
        interactDialogue(player);
        createGUI(player);
        showGUI(player);
        talk(player);
    }

    public void createGUI(Player player) {
        //AbstractItem lockedSlot = new Locked ("? ? ?");
        /* if (player has visited ruins)*/
        //lockedSlot = new Pendant(4000);

        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# # # # a # # # #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('a', new Convergence(0))
                .build();
    }

    public void onCursed(Player player) {
        StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(player.getUniqueId());
        sp.setCursed(true);

        player.playSound(player, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 100, 0);
        player.playSound(player, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 100, 1);
        player.sendMessage(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "You have taken the Curse of Clato!");

        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0, true));

        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) == null)
            return;
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);

        player.setDisplayName(ChatColor.DARK_PURPLE + "Cursed " + player.getName());
    }

    private class Convergence extends StigglesBaseItem {
        public Convergence(int price) {
            super(price);
            item = new ItemStack(Material.AMETHYST_SHARD);
            ItemMeta im = item.getItemMeta();
            if (im == null)
                return;
            im.setDisplayName(ChatColor.BOLD + ChatColor.DARK_PURPLE.toString() + "The Curse of Clato");
            im.addEnchant(Enchantment.BINDING_CURSE, 1, true);
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            im.setLore(Arrays.asList(

                            ChatColor.GRAY + "Lose half of your hearts " + ChatColor.RED + ChatColor.UNDERLINE + "BUT",
                            ChatColor.GRAY + " gain strength when low on health.",
                            "",
                            ChatColor.RED + "Will you accept?",
                            ChatColor.UNDERLINE.toString() + ChatColor.BOLD + ChatColor.DARK_RED + "WARNING: Permanent Effect"
                    )
            );
            item.setItemMeta(im);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            //handleTrade(player, this);
            onCursed(player);
        }
    }

}
