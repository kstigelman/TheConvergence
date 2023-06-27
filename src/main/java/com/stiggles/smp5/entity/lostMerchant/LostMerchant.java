package com.stiggles.smp5.entity.lostMerchant;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.UUID;

public class LostMerchant {

    InventoryManager inventoryManager = new InventoryManager();
    ArrayList<UUID> merchantEntities = new ArrayList<>();

    public Villager spawnLostMerchant(Location loc) {
        Villager merchant = loc.getWorld().spawn(loc, Villager.class);
        merchant.setCustomName(ChatColor.AQUA + "Merchant Marketeer");
        merchant.setCustomNameVisible(true);
        merchant.setAdult();
        merchant.setInvulnerable(true);
        merchant.setGravity(true);
        merchant.setVillagerType(Villager.Type.DESERT);
        merchant.setProfession(Villager.Profession.NITWIT);
        merchant.setVillagerLevel(1);
        merchant.setVillagerExperience(1);
        merchant.setCanPickupItems(false);
        merchant.setCustomNameVisible(true);

        merchant.setPersistent(true);

        return merchant;
    }

    public Villager spawnMerchantRep(Location loc) {
        Villager merchant = loc.getWorld().spawn(loc, Villager.class);
        merchant.setCustomName(ChatColor.AQUA + "Merchant Representative");
        merchant.setCustomNameVisible(true);
        merchant.setAdult();
        merchant.setInvulnerable(true);
        merchant.setAI(false);
        merchant.setGravity(true);
        merchant.setVillagerType(Villager.Type.DESERT);
        merchant.setProfession(Villager.Profession.NITWIT);
        merchant.setVillagerLevel(1);
        merchant.setVillagerExperience(1);
        merchant.setCanPickupItems(false);
        merchant.setCustomNameVisible(true);

        merchant.setPersistent(true);

        return merchant;
    }

    public ArrayList<UUID> getMerchantEntities() {
        return merchantEntities;
    }

}
