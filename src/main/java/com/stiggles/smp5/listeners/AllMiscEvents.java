package com.stiggles.smp5.listeners;

import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import com.stiggles.smp5.items.Cooldown;
import com.stiggles.smp5.items.HuntQuestItems;
import com.stiggles.smp5.items.NetheriteQuestItems;
import com.stiggles.smp5.items.Pickaxes;
import com.stiggles.smp5.items.bows.BoomBow;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class AllMiscEvents implements Listener {

    HashMap<UUID, Boolean> gottenWheel = new HashMap<>();
    SMP5 main;
    public AllMiscEvents (SMP5 main) {
        this.main = main;
    }
    Pickaxes pickaxes = new Pickaxes();

    Cuboid goldSpot = new Cuboid(
            new Location(Bukkit.getWorld("world_nether"), -88, 135, 14),
            new Location(Bukkit.getWorld("world_nether"), -102, 149, 0));
    Cuboid netheriteSpot = new Cuboid(
            new Location(Bukkit.getWorld("world_nether"), -148, 128, 15),
            new Location(Bukkit.getWorld("world_nether"), -133, 146, 1));
    Cuboid diamondSpot = new Cuboid(
            new Location(Bukkit.getWorld("world_nether"), -96, 143, -34),
            new Location(Bukkit.getWorld("world_nether"), -109, 157, -21));
    Cuboid obsidianSpot = new Cuboid(
            new Location(Bukkit.getWorld("world_nether"), -126, 124, -78),
            new Location(Bukkit.getWorld("world_nether"), -140, 141, -68));

    /***
    This event is for checking if they are trying to use the grappling
     hooks ability.
     */
    @EventHandler
    public void playerRod(PlayerFishEvent e){
        if (e.getState().equals(PlayerFishEvent.State.REEL_IN) || e.getState().equals(PlayerFishEvent.State.IN_GROUND) || e.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)){
            Player p = e.getPlayer();
            ItemStack item = p.getInventory().getItemInMainHand();
            if (item.getItemMeta() != null){
                ItemMeta meta = item.getItemMeta();
                if (meta.getLocalizedName() != null && meta.getLocalizedName().equals("grappling_hook")){
                    if (Cooldown.checkCooldown(p)) {
                        Location playerLocation = p.getLocation();
                        Location hookLocation = e.getHook().getLocation();
                        Location change = hookLocation.subtract(playerLocation);
                        p.setVelocity(change.toVector().multiply(0.3));
                        Cooldown.setCooldown(p, 2);
                    } else {
                        p.sendMessage(ChatColor.RED +"Your grappling hook is currently on a cool down, please wait!");
                    }
                }
            }
        }
    }
    /***
     This event is for checking if players are breaking a block that relates to the
     netherite upgrade obtainment quest.
     */
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        Block block = e.getBlock();
        if (p.getInventory().getItemInMainHand().equals(pickaxes.hardenedPickaxe())){
            if (e.getBlock().getWorld().equals(Bukkit.getWorld("world_nether"))){
                if(checkNetherite(block)) {
                    e.setCancelled(true);

                    if (block.getType().equals(Material.ANCIENT_DEBRIS)) {
                        p.playSound(p, Sound.BLOCK_DECORATED_POT_SHATTER, 2, 1);
                        block.getLocation().getWorld().dropItemNaturally(block.getLocation(), NetheriteQuestItems.reinforcedAncientDebris());
                        block.setType(Material.BEDROCK);
                        new BukkitRunnable() {
                            public void run() {
                                block.setType(Material.ANCIENT_DEBRIS);
                            }
                        }.runTaskLater(main, 20 * (60));
                    }

                } else if(checkNetherGold(block)) {
                    e.setCancelled(true);

                    if (block.getType().equals(Material.DEEPSLATE_GOLD_ORE)) {
                        p.playSound(p, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 2, 1.5F);
                        block.getLocation().getWorld().dropItemNaturally(block.getLocation(), NetheriteQuestItems.hardenedGold());
                        block.setType(Material.BEDROCK);
                        new BukkitRunnable() {
                            public void run() {
                                block.setType(Material.DEEPSLATE_GOLD_ORE);
                            }
                        }.runTaskLater(main, 20 * (60));
                    }

                } else if(checkNetherDiamond(block)) {
                    e.setCancelled(true);

                    if (block.getType().equals(Material.DEEPSLATE_DIAMOND_ORE)) {
                        p.playSound(p, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 2, 1.5F);
                        block.getLocation().getWorld().dropItemNaturally(block.getLocation(), NetheriteQuestItems.hardenedDiamond());
                        block.setType(Material.BEDROCK);
                        new BukkitRunnable() {
                            public void run() {
                                block.setType(Material.DEEPSLATE_DIAMOND_ORE);
                            }
                        }.runTaskLater(main, 20 * (60));
                    }

                } else if(checkOby(block)){
                        e.setCancelled(true);
                    if (block.getType().equals(Material.OBSIDIAN)) {
                        p.playSound(p, Sound.BLOCK_DECORATED_POT_SHATTER, 2, 1);
                        block.getLocation().getWorld().dropItemNaturally(block.getLocation(), NetheriteQuestItems.toughenedObsidian());
                        block.setType(Material.BEDROCK);
                        new BukkitRunnable() {
                            public void run() {
                                block.setType(Material.OBSIDIAN);
                            }
                        }.runTaskLater(main, 20 * (60));
                    }

                }


            }
        } else {
            if (e.getBlock().getWorld().equals(Bukkit.getWorld("world_nether"))){
                if(checkNetherite(block)) {
                    e.setCancelled(true);
                    e.setDropItems(false);
                    p.sendMessage(ChatColor.RED + "You may not break this due to you not having the right pickaxe!");
                    p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 1 ,1);

                } else if(checkNetherGold(block)) {
                    e.setCancelled(true);
                    e.setDropItems(false);
                    p.sendMessage(ChatColor.RED + "You may not break this due to you not having the right pickaxe!");
                    p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 1 ,1);

                } else if(checkNetherDiamond(block)) {
                    e.setCancelled(true);
                    e.setDropItems(false);
                    p.sendMessage(ChatColor.RED + "You may not break this due to you not having the right pickaxe!");
                    p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 1 ,1);

                } else if(checkOby(block)){
                    e.setCancelled(true);
                    e.setDropItems(false);
                    p.sendMessage(ChatColor.RED + "You may not break this due to you not having the right pickaxe!");
                    p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 1 ,1);

                }
            }
        }
    }

    private boolean checkNetherGold(Block block){
        for(Block cuboidBlock : goldSpot.getBlocks()){
            if (cuboidBlock.equals(block)){
                return true;
            }
        }
        return false;
    }
    private boolean checkNetherite(Block block){
        for(Block cuboidBlock : netheriteSpot.getBlocks()){
            if (cuboidBlock.equals(block)){
                return true;
            }
        }
        return false;
    }
    private boolean checkNetherDiamond(Block block){
        for(Block cuboidBlock : diamondSpot.getBlocks()){
            if (cuboidBlock.equals(block)){
                return true;
            }
        }
        return false;
    }
    private boolean checkOby(Block block){
        for(Block cuboidBlock : obsidianSpot.getBlocks()){
            if (cuboidBlock.equals(block)){
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getClickedBlock() != null) {
            Block block = event.getClickedBlock();
            if (block.getType().equals(Material.SUSPICIOUS_SAND) && block.getLocation().equals(new Location(Bukkit.getWorld("world"), -144, 43, 865))) { //
                if (gottenWheel.get(p.getUniqueId()) == null || !gottenWheel.get(p.getUniqueId())) {
                    if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && p.getInventory().getItemInMainHand().equals(new ItemStack(Material.BRUSH))) {
                        p.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.ITALIC + "You've dug up something old and broken... looks- looks like a ship artifact...");
                        p.getInventory().addItem(HuntQuestItems.theDiversWheel());
                        gottenWheel.put(p.getUniqueId(), true);
                    } else {
                        p.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.ITALIC + "You should try using a new, unused and clean item to search here...");
                    }
                } else {
                    p.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.ITALIC + "You've already dug up the artifact.");
                }
            }
        }
    }

    @EventHandler
    public void prepareAnvil(PrepareAnvilEvent e){
        if (e.getResult().getItemMeta() != null && e.getResult().getItemMeta().getLocalizedName().equals("warden_weakness")){
            if (e.getResult().getItemMeta().hasEnchant(Enchantment.DURABILITY) || e.getResult().getItemMeta().hasEnchant(Enchantment.MENDING)){
                e.setResult(new ItemStack(Material.AIR));
            }

        }
        if (e.getInventory().contains(NetheriteQuestItems.hardenedDiamond())) {
            e.setResult(new ItemStack(Material.AIR));
            return;
        }
        if (e.getInventory().contains (NetheriteQuestItems.hardenedGold())) {
            e.setResult(new ItemStack(Material.AIR));
            return;
        }
        if (e.getInventory().contains (NetheriteQuestItems.reinforcedAncientDebris())) {
            e.setResult(new ItemStack(Material.AIR));
            return;
        }
        if (e.getInventory().contains (NetheriteQuestItems.toughenedObsidian())) {
            e.setResult(new ItemStack(Material.AIR));
            return;
        }
        if (e.getInventory().contains (NetheriteQuestItems.questTemplate())) {
            e.setResult(new ItemStack(Material.AIR));
            return;
        }
    }

}
