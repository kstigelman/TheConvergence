package com.stiggles.smp5.items.armor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class PeacesSymphony implements Listener {


    private String helm;
    private String chest;
    private String legs;
    private String boot;
    private int nearEntities;

    public ItemStack getPeaceHelmet() {
        ItemStack item = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(102, 255, 255));
        meta.setDisplayName(ChatColor.AQUA + "The Symphony's Helmet");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.AQUA + "-- SPECIAL ARMOR --",
                ChatColor.AQUA + "-= PEACES SYMPHONY =-",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of PEACES SYMPHONY you",
                ChatColor.GRAY + "gain the following buffs",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "NOT IN COMBAT",
                ChatColor.GRAY + "- Haste I",
                ChatColor.GRAY + "- Speed I",
                ChatColor.GRAY + "- Regeneration I"));
        meta.setLocalizedName("symp_helmet");
        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getPeaceChestplate() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(102, 255, 255));
        meta.setDisplayName(ChatColor.AQUA + "The Symphony's Chestplate");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.AQUA + "-- SPECIAL ARMOR --",
                ChatColor.AQUA + "-= PEACES SYMPHONY =-",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of PEACES SYMPHONY you",
                ChatColor.GRAY + "gain the following buffs",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "NOT IN COMBAT",
                ChatColor.GRAY + "- Haste I",
                ChatColor.GRAY + "- Speed I",
                ChatColor.GRAY + "- Regeneration I"));
        meta.setLocalizedName("symp_chestplate");

        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 8, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getPeaceLeggings() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(102, 255, 255));
        meta.setDisplayName(ChatColor.AQUA + "The Symphony's Leggings");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.AQUA + "-- SPECIAL ARMOR --",
                ChatColor.AQUA + "-= PEACES SYMPHONY =-",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of PEACES SYMPHONY you",
                ChatColor.GRAY + "gain the following buffs",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "NOT IN COMBAT",
                ChatColor.GRAY + "- Haste I",
                ChatColor.GRAY + "- Speed I",
                ChatColor.GRAY + "- Regeneration I"));
        meta.setLocalizedName("symp_leggins");

        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 6, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);


        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getPeaceBoots() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(102, 255, 255));
        meta.setDisplayName(ChatColor.AQUA + "The Symphony's Boots");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.AQUA + "-- SPECIAL ARMOR --",
                ChatColor.AQUA + "-= PEACES SYMPHONY =-",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of PEACES SYMPHONY you",
                ChatColor.GRAY + "gain the following buffs",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "NOT IN COMBAT",
                ChatColor.GRAY + "- Haste I",
                ChatColor.GRAY + "- Speed I",
                ChatColor.GRAY + "- Regeneration I"));
        meta.setLocalizedName("symp_boots");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);

        item.setItemMeta(meta);
        return item;
    }

    public Boolean isPeaceSet(Player p) {

        helm = "placeholder1";
        chest = "placeholder2";
        legs = "placeholder3";
        boot = "placeholder4";

        if (p.getInventory().getHelmet() != null) {
            helm = p.getInventory().getHelmet().getItemMeta().getLocalizedName();
        }
        if (p.getInventory().getChestplate() != null) {
            chest = p.getInventory().getChestplate().getItemMeta().getLocalizedName();
        }
        if (p.getInventory().getLeggings() != null) {
            legs = p.getInventory().getLeggings().getItemMeta().getLocalizedName();
        }
        if (p.getInventory().getBoots() != null) {
            boot = p.getInventory().getBoots().getItemMeta().getLocalizedName();
        }

        if (helm.equals("symp_helmet") && chest.equals("symp_chestplate") && legs.equals("symp_leggins") && boot.equals("symp_boots")) {
            helm = "placeholder1";
            chest = "placeholder2";
            legs = "placeholder3";
            boot = "placeholder4";
            return true;
        }
        return false;
    }

    public void checkForPeaceArmor() {
        nearEntities = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            for (Entity e : p.getNearbyEntities(5, 5, 5)) {
                if (e instanceof Player) {
                    nearEntities = nearEntities + 1;
                }
            }
            if (nearEntities == 0 && isPeaceSet(p)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0, true, false, true));
                p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 100, 2, false, false, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 0, true, false, true));
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, true, false, true));
            }
        }
    }

    public void getItems(Player p) {
        p.getInventory().addItem(getPeaceHelmet(), getPeaceChestplate(), getPeaceLeggings(), getPeaceBoots());
    }

}

