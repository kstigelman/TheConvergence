package com.stiggles.smp5.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.UUID;

public class HuntQuestItems {


    public static ItemStack theFriendsPendant(){
        ItemStack pendant = new ItemStack(Material.HEART_OF_THE_SEA);
        ItemMeta pendantMeta = pendant.getItemMeta();
        pendantMeta.setDisplayName(ChatColor.AQUA+"The Friend's Pendant");
        pendantMeta.setLore(Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + ChatColor.ITALIC.toString() + "Once was the center of a great friendship,",
                ChatColor.GRAY + ChatColor.ITALIC.toString() + "now it's just a relic of a memory..."));
        pendantMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        pendantMeta.setLocalizedName("the_friends_pendant");

        pendant.setItemMeta(pendantMeta);
        return pendant;
    }

    public static ItemStack theDiversWheel(){
        ItemStack wheel = new ItemStack(Material.EXPLORER_POTTERY_SHERD);
        ItemMeta itemMeta = wheel.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + "Scuber's Artifact");
        itemMeta.setLore(Arrays.asList(ChatColor.BLUE + "Quest Item",
                ChatColor.GRAY + ChatColor.ITALIC.toString() + "A shard that is now just a memory of a ship."));
        itemMeta.setLocalizedName("scuber_artifact");

        wheel.setItemMeta(itemMeta);
        return wheel;
    }

    public static ItemStack petRock(){
        ItemStack pendant = new ItemStack(Material.LIGHT_GRAY_DYE);
        ItemMeta pendantMeta = pendant.getItemMeta();
        pendantMeta.setDisplayName(ChatColor.AQUA+"Shrek's Pet Rock");
        pendantMeta.setLore(Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + ChatColor.ITALIC.toString() + "Very special pet rock."));
        pendantMeta.setLocalizedName("pet_rock");

        pendant.setItemMeta(pendantMeta);
        return pendant;
    }

    public static ItemStack getNetherArtifact(){
        ItemStack netherArtifact = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta itemMeta = netherArtifact.getItemMeta();
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Nether Artifact");
        itemMeta.setLore(Arrays.asList(ChatColor.BLUE + "Quest Item",
                ChatColor.GRAY + ChatColor.ITALIC.toString() + "The End...",
                ChatColor.GRAY + ChatColor.ITALIC.toString() + "But also a new way to start..."));
        itemMeta.setLocalizedName("nether_artifact");
        itemMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);


        netherArtifact.setItemMeta(itemMeta);
        return netherArtifact;
    }



}
