package com.stiggles.smp5.listeners;

import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import com.stiggles.smp5.items.Cooldown;
import com.stiggles.smp5.items.NetheriteQuestItems;
import com.stiggles.smp5.items.Pickaxes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AllMiscEvents implements Listener {

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
                World world_nether = p.getWorld();
                if(checkNetherite(block)) {
                    e.setCancelled(true);
                    block.getLocation().getWorld().dropItemNaturally(block.getLocation().add(0,1,0), NetheriteQuestItems.reinforcedAncientDebris());

                } else if(checkNetherGold(block)) {
                    e.setCancelled(true);
                    block.getLocation().getWorld().dropItemNaturally(block.getLocation().add(0,1,0), NetheriteQuestItems.hardenedGold());

                } else if(checkNetherDiamond(block)) {
                    e.setCancelled(true);
                    block.getLocation().getWorld().dropItemNaturally(block.getLocation().add(0,1,0), NetheriteQuestItems.hardenedDiamond());

                } else if(checkOby(block)){
                    e.setCancelled(true);
                    block.getLocation().getWorld().dropItemNaturally(block.getLocation().add(0,1,0), NetheriteQuestItems.toughenedObsidian());

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

}
