package com.stiggles.smp5.items.armor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class LunarArmor {

    private String helm;
    private String chest;
    private String legs;
    private String boot;
    private int nearEntities;

    public ItemStack getLunarHelmet(){
        ItemStack item = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(204, 255, 255));
        meta.setDisplayName(ChatColor.AQUA + "Lunar Helmet");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.AQUA +  "-- SPECIAL ARMOR --",
                ChatColor.AQUA +  "-=  LUNAR ARMOR  =-",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of LUNAR ARMOR you",
                ChatColor.GRAY + "gain the following buffs:",
                ChatColor.GRAY + "- Jump Boost I",
                ChatColor.GRAY + "- Slow Falling I"));
        meta.setLocalizedName("lunar_helmet");
        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 2, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getLunarChestplate(){
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(204, 255, 255));
        meta.setDisplayName(ChatColor.AQUA + "Lunar Chestplate");
        meta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.AQUA +  "-- SPECIAL ARMOR --",
                ChatColor.AQUA +  "-=  LUNAR ARMOR  =-",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of LUNAR ARMOR you",
                ChatColor.GRAY + "gain the following buffs:",
                ChatColor.GRAY + "- Jump Boost I",
                ChatColor.GRAY + "- Slow Falling I"));
        meta.setLocalizedName("symp_chestplate");

        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 6, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getLunarLeggings(){
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(204, 255, 255));
        meta.setDisplayName(ChatColor.AQUA + "Lunar Leggings");
        meta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.AQUA +  "-- SPECIAL ARMOR --",
                ChatColor.AQUA +  "-=  LUNAR ARMOR  =-",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of LUNAR ARMOR you",
                ChatColor.GRAY + "gain the following buffs:",
                ChatColor.GRAY + "- Jump Boost I",
                ChatColor.GRAY + "- Slow Falling I"));
        meta.setLocalizedName("symp_leggins");

        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 5, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);


        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getLunarBoots(){
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(204, 255, 255));
        meta.setDisplayName(ChatColor.AQUA + "Lunar Boots");
        meta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.AQUA +  "-- SPECIAL ARMOR --",
                ChatColor.AQUA +  "-=  LUNAR ARMOR  =-",
                ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                ChatColor.GRAY + "When paired with the full",
                ChatColor.GRAY + "set of LUNAR ARMOR you",
                ChatColor.GRAY + "gain the following buffs:",
                ChatColor.GRAY + "- Jump Boost I",
                ChatColor.GRAY + "- Slow Falling I"));
        meta.setLocalizedName("symp_boots");

        AttributeModifier genericArmor = new AttributeModifier("generic.armor", 2, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getMoonShards(int amount) {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(false);
        meta.setDisplayName(ChatColor.AQUA + "Moon Shards");
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.AQUA +  "-- SPECIAL ITEM --",
                ChatColor.GRAY + "This is pointless by itself,",
                ChatColor.GRAY + "but can be used to craft",
                ChatColor.GRAY + "the Lunar Armor Set."));
        meta.setLocalizedName("moon_shard");
        item.setItemMeta(meta);

        return item;
    }
    public ItemStack getShoppingMoonShards(int amount) {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(false);
        meta.setDisplayName(ChatColor.AQUA + "Moon Shards");
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.AQUA +  "-- SPECIAL ITEM --",
                ChatColor.AQUA + "Costs: 64 End Stone"));
        meta.setLocalizedName("moon_shard");
        item.setItemMeta(meta);

        return item;
    }

    public Boolean isLunarSet(Player p){

        helm = "placeholder1";
        chest = "placeholder2";
        legs = "placeholder3";
        boot = "placeholder4";

        if (p.getInventory().getHelmet() != null){
            helm  = p.getInventory().getHelmet().getItemMeta().getLocalizedName();
        }
        if (p.getInventory().getChestplate() != null){
            chest = p.getInventory().getChestplate().getItemMeta().getLocalizedName();
        }
        if (p.getInventory().getLeggings() != null){
            legs = p.getInventory().getLeggings().getItemMeta().getLocalizedName();
        }
        if (p.getInventory().getBoots() != null){
            boot = p.getInventory().getBoots().getItemMeta().getLocalizedName();
        }

        if (helm.equals("lunar_helmet") && chest.equals("lunar_chestplate") && legs.equals("lunar_leggings") && boot.equals("lunar_boots")){
            helm = "placeholder1";
            chest = "placeholder2";
            legs = "placeholder3";
            boot = "placeholder4";
            return true;
        }
        return false;
    }

    public void checkForLunarArmor(){
        nearEntities = 0;
        for (Player p : Bukkit.getOnlinePlayers()){
            if (nearEntities == 0 && isLunarSet(p)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 0, true, false, true));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 2, false, false, false));
            }
        }
    }
}
