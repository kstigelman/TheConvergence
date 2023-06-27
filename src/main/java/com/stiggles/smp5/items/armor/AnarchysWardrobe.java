package com.stiggles.smp5.items.armor;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class AnarchysWardrobe implements Listener {

    private String helm;
    private String chest;
    private String legs;
    private String boot;


    private ItemStack getAnarchysHelmet() {
        ItemStack item = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(166, 0, 199));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Anarchy's Helmet");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.LIGHT_PURPLE + "-- SPECIAL ARMOR --",
                ChatColor.LIGHT_PURPLE + "- ANARCHY'S WARDROBE -",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of ANARCHY'S WARDROBE you",
                ChatColor.GRAY + "gain the following buffs",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "IN COMBAT",
                ChatColor.GRAY + "- Regeneration 1",
                ChatColor.GRAY + "- Strength 1",
                ChatColor.GRAY + "- Speed 1"));
        meta.setLocalizedName("a_helmet");
        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack getAnarchysChestplate() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(166, 0, 199));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Anarchy's Chestplate");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.LIGHT_PURPLE + "-- SPECIAL ARMOR --",
                ChatColor.LIGHT_PURPLE + "- ANARCHY'S WARDROBE -",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of ANARCHY'S WARDROBE you",
                ChatColor.GRAY + "gain the following buffs",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "IN COMBAT",
                ChatColor.GRAY + "- Regeneration 1",
                ChatColor.GRAY + "- Strength 1",
                ChatColor.GRAY + "- Speed 1"));
        meta.setLocalizedName("a_chestplate");

        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 8, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack getAnarchysLeggings() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(166, 0, 199));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Anarchy's Leggings");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.LIGHT_PURPLE + "-- SPECIAL ARMOR --",
                ChatColor.LIGHT_PURPLE + "- ANARCHY'S WARDROBE -",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of ANARCHY'S WARDROBE you",
                ChatColor.GRAY + "gain the following buffs",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "IN COMBAT",
                ChatColor.GRAY + "- Regeneration 1",
                ChatColor.GRAY + "- Strength 1",
                ChatColor.GRAY + "- Speed 1"));
        meta.setLocalizedName("a_leggins");

        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 6, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack getAnarchysBoots() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(166, 0, 199));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Anarchy's Boots");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.LIGHT_PURPLE + "-- SPECIAL ARMOR --",
                ChatColor.LIGHT_PURPLE + "- ANARCHY'S WARDROBE -",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of ANARCHY'S WARDROBE you",
                ChatColor.GRAY + "gain the following buffs",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "IN COMBAT",
                ChatColor.GRAY + "- Regeneration 1",
                ChatColor.GRAY + "- Strength 1",
                ChatColor.GRAY + "- Speed 1"));
        meta.setLocalizedName("a_boots");

        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 2, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    private Boolean isAnarchySet(Player p) {
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

        if (helm.equals("a_helmet") && chest.equals("a_chestplate") && legs.equals("a_leggins") && boot.equals("a_boots")) {
            helm = "placeholder1";
            chest = "placeholder2";
            legs = "placeholder3";
            boot = "placeholder4";
            return true;
        }
        return false;
    }

    /*
SET: Anarchy's Wardrobe
Helmet: Anarchy's Helmet
USELESS BY ITSELF
When paired with the full set of Anarchy's Wardrobe, this along with the full set will provide the following effects while in combat:
Regeneration I
Slight Speed Increase

Chestplate: Anarchy's Chestplate
USELESS BY ITSELF
When paired with the full set of Anarchy's Wardrobe, this along with the full set will provide the following effects while in combat:
Regeneration I
Slight Speed Increase

Leggings: Anarchy's Leggings
USELESS BY ITSELF
When paired with the full set of Anarchy's Wardrobe, this along with the full set will provide the following effects while in combat:
Regeneration I
Slight Speed Increase

Boots: Anarchy's Boots
USELESS BY ITSELF
When paired with the full set of Anarchy's Wardrobe, this along with the full set will provide the following effects while in combat:
Regeneration I
Slight Speed Increase
     */

    @EventHandler
    public void playerInCombat(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (isAnarchySet(p)) {

            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 0, true, false, true));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50, 0, true, false, true));

            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 50, 0, true, false, true));
        }
    }


    public void getItems(Player p) {
        p.getInventory().addItem(getAnarchysHelmet(), getAnarchysChestplate(), getAnarchysLeggings(), getAnarchysBoots());
    }

}

