package com.stiggles.smp5.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EnderSignal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EndEyeListener implements Listener {

    @EventHandler
    public void eyeOfEnderUse(EntitySpawnEvent e) {
        if (e.getEntity().equals(EntityType.ENDER_SIGNAL)){
            EnderSignal enderSignal = (EnderSignal) e.getEntity();
            if (e.getEntity().getWorld().equals("world")){
                enderSignal.setTargetLocation(new Location(Bukkit.getWorld("world"), -820, -7, -672));
            }
        }
    }

    @EventHandler
    public void interact(PlayerInteractEvent e){
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player p = e.getPlayer();
            if(p.getInventory().getItemInMainHand().equals(Material.ENDER_EYE)){
                Location targetLocation = new Location(Bukkit.getWorld("world"), -820, -7, -672);

                EnderSignal enderSignal = Bukkit.getWorld("world").spawn(p.getLocation().add(0,2,0), EnderSignal.class);
                enderSignal.setDropItem(false);
                enderSignal.setTargetLocation(targetLocation);
                int eyeAmount = p.getInventory().getItemInMainHand().getAmount();
                enderSignal.setDespawnTimer(0);
                int newEyeAmount = eyeAmount-1;
                p.getInventory().getItemInMainHand().setAmount(newEyeAmount);
            }
        }
    }

}
