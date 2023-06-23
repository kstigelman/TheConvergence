package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.StigglesPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class OnArmorStandInteract implements Listener {

    SMP5 main;

    public OnArmorStandInteract (SMP5 main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerInteract (PlayerArmorStandManipulateEvent e) {
        ArmorStand entity = e.getRightClicked ();
        if (entity.getName().contains ("convergence")) {
            e.setCancelled(true);
            if (e.getPlayer().getInventory().firstEmpty() == -1)
                return;
            if (e.getHand().equals(EquipmentSlot.OFF_HAND))
                return;

            StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(e.getPlayer().getUniqueId());
            int hash = calculateHash(entity.getLocation());


            if (!sp.hasTalkedTo("Dr. Trog")) {
                e.getPlayer().sendMessage(ChatColor.GRAY + "This looks like a strange substance...");
                return;
            }
            for (ItemStack i : e.getPlayer().getInventory()) {
                if (i == null || !i.hasItemMeta())
                    continue;

                if (i.getItemMeta().hasLocalizedName() && i.getItemMeta().getLocalizedName().equals("convergence_" + hash))
                    return;
            }
            e.getPlayer().getInventory().addItem(getConvergence(entity.getLocation()));

            if (!sp.hasFoundConvergence (hash)) {
                e.getPlayer().sendMessage(ChatColor.GRAY + "You have found a Convergence Crystal! Someone may want to see this...");
                sp.addConvergence(calculateHash(entity.getLocation()));
            }
            return;
        }
        if (entity.getName().contains ("morabito")) {
            e.setCancelled(true);
            if (e.getPlayer().getInventory().firstEmpty() == -1)
                return;
            if (e.getHand().equals(EquipmentSlot.OFF_HAND))
                return;

            for (ItemStack i : e.getPlayer().getInventory()) {
                if (i == null || !i.hasItemMeta())
                    continue;
                if (i.getItemMeta().getLocalizedName().equals("recipe"))
                    return;
            }
            e.getPlayer().sendMessage(ChatColor.GRAY.toString() + "You have found the Secret Recipe! Someone was looking for this...");
            e.getPlayer().getInventory().addItem(getRecipe());
                //    getName().contains(ChatColor.DARK_GRAY + "Natalie's Breath (Decayed)"))

        }
        if (entity.getName().contains("drem_sword")) {
            e.setCancelled(true);
            if (e.getPlayer().getInventory().firstEmpty() == -1)
                return;
            if (e.getHand().equals(EquipmentSlot.OFF_HAND))
                return;

            for (ItemStack i : e.getPlayer().getInventory()) {
                if (i == null || !i.hasItemMeta())
                    continue;

                if (i.getItemMeta().hasLocalizedName() && i.getItemMeta().getLocalizedName().equals("nats_breath"))
                    return;
                    //    getName().contains(ChatColor.DARK_GRAY + "Natalie's Breath (Decayed)"))
            }
            e.getPlayer().sendMessage(ChatColor.ITALIC + ChatColor.GRAY.toString() + "You have found the sword of Natalie's Breath! It looks decayed...");
            e.getPlayer().getInventory().addItem(getSword());

            /*
            /give @p stone_sword{Damage:50,AttributeModifiers:[{AttributeName:"generic.attack_damage",Amount:0,Slot:mainhand,Name:"generic.attack_damage",
            UUID:[I;-12351,60527,105915,-121054]},{AttributeName:"generic.attack_damage",Amount:0,Slot:offhand,Name:"generic.attack_damage",
            UUID:[I;-12351,60627,105915,-121254]}],display:{Name:'[{"text":"Natalie\'s Breath (Decayed)","italic":false,"color":"light_purple"}]',
            Lore:['[{"text":"Quest item","italic":false,"color":"blue"},{"text":"","italic":false,"color":"dark_purple"}]',
            '[{"text":"Belonged to a hero from long ago. Perhaps there is someone who knows who knows more about it?","italic":true,"color":"gray"}]']},
            HideFlags:1} 1



            /summon minecraft:armor_stand ~-0.4 ~ ~
            {Pose:{RightArm:[90f,90f,0f]},ShowArms:1b,Invisible:1b,NoBasePlate:1b,CustomName:'[{"text":"drem_sword"}]',
            Invulnerable:1b,NoGravity:1b,PersistenceRequired:1b,HandItems:[{id:stone_sword,Count:1}],HandDropChances:[0f]}
             */

        }
        if (entity.getName().contains("meteor")) {
            e.setCancelled(true);
            if (e.getPlayer().getInventory().firstEmpty() == -1)
                return;
            if (e.getHand().equals(EquipmentSlot.OFF_HAND))
                return;

            for (ItemStack i : e.getPlayer().getInventory()) {
                if (i == null || !i.hasItemMeta())
                    continue;

                if (i.getItemMeta().hasLocalizedName() && i.getItemMeta().getLocalizedName().equals("moon_rocks"))
                    return;
                //    getName().contains(ChatColor.DARK_GRAY + "Natalie's Breath (Decayed)"))
            }
            e.getPlayer().sendMessage(ChatColor.ITALIC + ChatColor.GRAY.toString() + "You picked up some debris from the meteor strike!");
            e.getPlayer().getInventory().addItem(getRocks());
        }
    }
    public ItemStack getSword () {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.DARK_GRAY + "Natalie's Breath (Decayed)");
        im.setLocalizedName("nats_breath");
        im.setLore (Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + ChatColor.ITALIC.toString() + "Belonged to a hero from long ago.",
                ChatColor.GRAY + ChatColor.ITALIC.toString() + "Perhaps there is someone who knows more about it?"));
        if (im instanceof Damageable)
            ((Damageable) im).setDamage(128);
        im.setUnbreakable(true);
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 0.0, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
        item.setItemMeta(im);
        return item;
    }
    public ItemStack getRecipe () {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Moonshine Secret Recipe");
        im.setLocalizedName("recipe");
        im.setLore (Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + "Spectral Saloon's famous recipe.",
                ChatColor.GRAY + ChatColor.ITALIC.toString() + "It looks like it was written in a code, so it is unreadable to you."));
        im.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        return item;
    }
    public ItemStack getRocks () {
        ItemStack item = new ItemStack(Material.END_STONE);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.AQUA + "Meteor Debris");
        im.setLocalizedName("moon_rocks");
        im.setLore (Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + "A piece of the meteor.",
                ChatColor.GRAY + ChatColor.ITALIC.toString() + "Give this to someone who knows more about the meteor strike."));
        im.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        im.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        return item;
    }
    public ItemStack getConvergence (Location loc) {
        ItemStack item = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.LIGHT_PURPLE + "Convergence Crystal");
        im.setLocalizedName("convergence_" + calculateHash(loc));
        im.setLore (Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + "A sample of Convergence",
                ChatColor.DARK_GRAY + "Found at (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ")",
                ChatColor.GRAY + ChatColor.ITALIC.toString() + "Convergence is a substance created by ",
                ChatColor.GRAY + ChatColor.ITALIC.toString() + "EGO Labs. They used their research on ",
                ChatColor.GRAY + ChatColor.ITALIC.toString() + "it to create the world of Convergence."));
        im.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        im.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        return item;
    }

    public int calculateHash (Location location) {
        return location.getBlockX() + (location.getBlockY() * 2) + (location.getBlockZ() * 3);
    }
}
