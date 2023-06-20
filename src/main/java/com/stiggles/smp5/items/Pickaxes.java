package com.stiggles.smp5.items;


import com.stiggles.smp5.stats.Quest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Warden;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Random;

public class Pickaxes implements Listener {

    private int rollNumber(int min, int max){
        Random rand = new Random();
        int randomNumber = rand.nextInt(max) + min;

        return randomNumber;
    }
    private ItemStack getHandyToolPickaxe(){
        /*
        Smurfs Handy Tool (Netherite Pickaxe)
        Mines stone, coal ore, iron ore, lapis lazuli, gold ore, diamond ore, and ancient debris 30% faster.
        */
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.AQUA + "Smurf's Handy Tool");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.GOLD +  "-- SPECIAL ITEM --",
                ChatColor.GRAY + "When mining stone, coal ore,",
                ChatColor.GRAY +  "iron ore, lapis lazuli,",
                ChatColor.GRAY +  "gold ore, diamond ore, and",
                ChatColor.GRAY +  "ancient debris, you mine it faster"));
        meta.setLocalizedName("smurf_handy_tool");
        item.setItemMeta(meta);
        return item;
    }
    private ItemStack getSHOPPPERHandyToolPickaxe(){
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.AQUA + "Smurf's Handy Tool");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.GOLD +  "-- SPECIAL ITEM --",
                ChatColor.GRAY + "When mining stone, coal ore,",
                ChatColor.GRAY +  "iron ore, lapis lazuli,",
                ChatColor.GRAY +  "gold ore, diamond ore, and",
                ChatColor.GRAY +  "ancient debris, you mine it faster",
                ChatColor.AQUA + "Costs: 32 Blue Wool"));
        meta.setLocalizedName("smurf_handy_tool");
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack giveHandyToolPickaxe(){
        return getHandyToolPickaxe();
    }
    public ItemStack giveShopTool(){ return getSHOPPPERHandyToolPickaxe(); }

    private ItemStack getWardenWeaknessPickaxe(){
        /*
        Smurfs Handy Tool (Netherite Pickaxe)
        Mines stone, coal ore, iron ore, lapis lazuli, gold ore, diamond ore, and ancient debris 30% faster.
        */
        ItemStack item = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.BLUE + "The Warden's Weakness");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.GOLD +  "-- SPECIAL ITEM --",
                ChatColor.GRAY + "Deals double your experience as",
                ChatColor.GRAY +  "damage to the warden.",
                ChatColor.GRAY + "Reduces the amount of coins you",
                ChatColor.GRAY + "earn from killing the warden.",
                ChatColor.GRAY +  "",
                ChatColor.GRAY + "When paired with fortune III,",
                ChatColor.GRAY + "the pickaxe allows anywhere from",
                ChatColor.GRAY +  "2x - 4x drops."));
        meta.setLocalizedName("warden_weakness");
        item.setItemMeta(meta);
        return item;
}
    public ItemStack giveWardenWeaknessPickaxe(){
        return getWardenWeaknessPickaxe();
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            if (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("warden_weakness")
                    && e.getEntity().getType().equals(EntityType.WARDEN)){

                int experience = p.getExpToLevel();
                e.setDamage(experience/2);
            }
        }
    }

    @EventHandler
    public void onEntityDeath (EntityDeathEvent e) {
        if (!(e.getEntity() instanceof Warden))
            return;

        if (e.getEntity().getKiller() == null)
            return;

        Player p = e.getEntity().getKiller();
        if (Quest.isQuestComplete(p, Quest.QuestName.WARDEN_KILL))
            return;
        e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), giveWardenWeaknessPickaxe());
        Quest.questComplete(p, Quest.QuestName.WARDEN_KILL, p.getName() + ", Slayer of Wardens", 0);
    }
    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        if (e.getPlayer() != null && e.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null){
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("warden_weakness")
                    && p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)){
                int level = p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                if (level == 3){
                    Material block = e.getBlock().getType();
                    if (block.equals(Material.COAL_ORE) || block.equals(Material.DEEPSLATE_COAL_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.COAL, Math.multiplyExact(Math.addExact(rollNumber(0,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.DIAMOND_ORE) || block.equals(Material.DEEPSLATE_DIAMOND_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.DIAMOND, Math.multiplyExact(Math.addExact(rollNumber(0,1), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.REDSTONE_ORE) || block.equals(Material.DEEPSLATE_REDSTONE_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.REDSTONE, Math.multiplyExact(Math.addExact(rollNumber(0,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.EMERALD_ORE) || block.equals(Material.DEEPSLATE_EMERALD_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.EMERALD, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(2,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.LAPIS_ORE) || block.equals(Material.DEEPSLATE_LAPIS_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.LAPIS_LAZULI, Math.multiplyExact(Math.addExact(rollNumber(2,3), rollNumber(2,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.NETHER_QUARTZ_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.QUARTZ, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.MELON)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.MELON_SLICE, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.CLAY)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.CLAY_BALL, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.GLOWSTONE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.GLOWSTONE_DUST, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.ANCIENT_DEBRIS)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.ANCIENT_DEBRIS, Math.multiplyExact(Math.addExact(1, rollNumber(1,2)), rollNumber(1,2))));

                    } else if (block.equals(Material.WHEAT)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.WHEAT, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.CARROT)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.GLOWSTONE_DUST, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.SUGAR_CANE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.GLOWSTONE_DUST, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.NETHER_GOLD_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.GLOWSTONE_DUST, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.BEETROOTS)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.BEETROOT, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.POTATOES)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.POTATOES
                                        , Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));
                    }
                }
            }
        }
    }
    @EventHandler
    public void playerInCombat(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            if (p.getInventory().getItemInMainHand().getItemMeta() != null) {
                if (p.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("smurf_handy_tool")){
                    Material block = e.getClickedBlock().getBlockData().getMaterial();
                    if (block.equals(Material.STONE) || block.equals(Material.IRON_ORE) || block.equals(Material.COAL_ORE) || block.equals(Material.GOLD_ORE)
                            || block.equals(Material.LAPIS_ORE) || block.equals(Material.DIAMOND_ORE) || block.equals(Material.ANCIENT_DEBRIS)){
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 2, false, false, true));

                    }
                }
            }
        }
    }

}