package com.stiggles.smp5.items;


import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Random;

public class Swords implements Listener {
    private int rollNumber(int min, int max){
        Random rand = new Random();
        int randomNumber = rand.nextInt(max) + min;

        return randomNumber;
    }

    private ItemStack getEmeraldDagger(){
        /*
        Emerald Dagger (Emerald)
        Has a 10% chance to drop 1-3 Emeralds on a kill.
        When paired with Sharpness V, the dagger has a 20% chance to deal 50% more damage.

        Magma Cutlass (Magma Cream)
        When right-clicked, if not on the 15-second cooldown, will send a flaming arrow in the direction you are facing.
        When taking fire damage, the cutlass's damage will deal 5% more per damage taken(while on fire, max being 50% more)
        */
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.GREEN + "Emerald Dagger");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.GOLD +  "-- SPECIAL ITEM --",
                ChatColor.GRAY + "Has a random chance to drop",
                ChatColor.GRAY + "multiple emeralds on a kill. ",
                ChatColor.GRAY + "",
                ChatColor.GRAY + "When enchanted with Sharpness V",
                ChatColor.GRAY + "the dagger unlocks a special",
                ChatColor.GRAY + "ability."));
        meta.setLocalizedName("emerald_dagger");
        item.setItemMeta(meta);
        return item;
    }
    private ItemStack getShopEmeraldDagger(){
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.GREEN + "Emerald Dagger");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.GOLD +  "-- SPECIAL ITEM --",
                ChatColor.GRAY + "Has a random chance to drop",
                ChatColor.GRAY + "multiple emeralds on a kill. ",
                ChatColor.GRAY + "",
                ChatColor.GRAY + "Reveals a special ability once",
                ChatColor.GRAY + "purchased.",
                ChatColor.AQUA + "Costs: 16 Emerald Blocks"));
        meta.setLocalizedName("emerald_dagger");
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getTheEmeraldDagger(){
        return getEmeraldDagger();
    }
    public ItemStack getTheShopsEmeraldDagger(){
        return getShopEmeraldDagger();
    }
    private boolean isEmeraldDagger(ItemStack item) {
        if (item == null) {
            return false;
        } else {
            if(item.hasItemMeta()){
                return (item.getItemMeta().getLocalizedName().equals("emerald_dagger"));
            }
            return false;
        }
    }
    private boolean hasSharpV(ItemStack item) {
        if (isEmeraldDagger(item)){
            if (item.getItemMeta().hasEnchant(Enchantment.DAMAGE_ALL)) {
                if (item.getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ALL) == 5){
                    return true;
                }
            }
        }
        return false;
    }

    private ItemStack getMagmaCutlass(){
        ItemStack item = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.GOLD + "Magma Cutlass");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.GOLD +  "-- SPECIAL ITEM --",
                ChatColor.GRAY + "When right-clicked, it uses 1",
                ChatColor.GRAY + "experience level to shoot a",
                ChatColor.GRAY + "flaming arrow in the direction",
                ChatColor.GRAY + "you are facing.",
                        "",
                ChatColor.GRAY + "While taking damage by fire,",
                ChatColor.GRAY + "you will deal significantly more",
                ChatColor.GRAY + "damage to all entities."));
        meta.setLocalizedName("magma_cutlass");
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getTheMagmaCutlass(){
        return getMagmaCutlass();
    }
    private boolean isMagmaCutlass(ItemStack item){
        if (item == null){
            return false;
        } else {
            if(item.hasItemMeta()){
                return (item.getItemMeta().getLocalizedName().equals("magma_cutlass"));
            }
            return false;
        }
    }

    // LISTENERS -- ABILITIES

    @EventHandler
    public void onDamageWithEmeraldDagger(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            if (isEmeraldDagger(p.getInventory().getItemInMainHand())){
                if (hasSharpV(p.getInventory().getItemInMainHand())){
                    int numberRoll = rollNumber(2,2);
                    if (numberRoll == 2) {
                        e.setDamage(Math.round(Math.addExact(rollNumber(5,15), Math.multiplyExact(10, rollNumber(1,2)))));
                    }
                } else {
                    e.setDamage(10);
                }
            }
        }
    }
    @EventHandler
    public void onEntityDeathEmeraldDagger(EntityDeathEvent e){
        if (e.getEntity().getKiller() instanceof Player){
            Player p = e.getEntity().getKiller();
            if (isEmeraldDagger(p.getInventory().getItemInMainHand())){
                int numberRoll = rollNumber(1,6);
                if (numberRoll == 1){
                    int emeralds = rollNumber(1,6);
                    p.getInventory().addItem(new ItemStack(Material.EMERALD, emeralds));
                    p.sendMessage(ChatColor.GREEN + "You just received " + emeralds + " emeralds from killing with the Emerald Dagger.");
                }
            }
        }
    }

    @EventHandler
    public void onDamageWithMagmaCutlass(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            if (isMagmaCutlass(p.getInventory().getItemInMainHand())){
                if(Math.floorDiv(p.getFireTicks(), 20) >= 1) {
                    e.setDamage(Math.addExact(7, Math.multiplyExact(p.getFireTicks(), 1)));
                } else {
                    e.setDamage(7);
                }
            }
        }
    }
    @EventHandler
    public void onClickMagmaCutlass(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.hasItem()) {
                if (isMagmaCutlass(e.getItem())) {
                    Location loc = e.getPlayer().getLocation();
                    Vector dir = loc.getDirection();
                    Arrow arrow = e.getPlayer().getWorld().spawn(loc.add(0, 2, 0), Arrow.class);
                    arrow.setFireTicks(100);
                    arrow.setVisualFire(true);
                    double speed = 1.5;
                    arrow.setVelocity(dir.multiply(speed));
                }
            }

        }
    }


}
