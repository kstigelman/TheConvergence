package com.stiggles.smp5.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.PortalCreateEvent;


/**
 * Detect when player enters or exits the Nether world to save to database.
 */
public class NetherListener implements Listener {

    @EventHandler
    public void onLightPortal(PortalCreateEvent e) {
        if (e.getEntity() == null) {
            e.setCancelled(true);
        } else if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (!p.isOp())
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void onNetherEvent(PlayerPortalEvent e) {
        World from = e.getFrom().getWorld();

        if (e.getTo() == null || from == null)
            return;

        World to = e.getTo().getWorld();

        if (to == null)
            return;

        Player p = e.getPlayer();


        if (to.getEnvironment().equals(World.Environment.NETHER)) {
            //Swamp
            if (p.getLocation().getBlockX() <= 713 && e.getPlayer().getLocation().getBlockX() >= 709) {
                e.setTo(new Location(Bukkit.getWorld("world_nether"), -165, 192, 609));
            }
            //Snow
            else if (p.getLocation().getBlockZ() >= -572 && e.getPlayer().getLocation().getBlockZ() <= -568) {
                e.setTo(new Location(Bukkit.getWorld("world_nether"), 143, 184, -457));
            }
            //Sand
            else if (p.getLocation().getBlockZ() >= -282 && e.getPlayer().getLocation().getBlockZ() <= -278) {
                e.setTo(new Location(Bukkit.getWorld("world_nether"), -479, 198, 144));
            } else {
                e.setTo(new Location(Bukkit.getWorld("world_nether"), 68, 194, 64));
            }
            return;
        }

        if (from.getEnvironment().equals(World.Environment.NETHER)) {
            if (p.getLocation().getBlockZ() <= 611 && e.getPlayer().getLocation().getBlockZ() >= 607) {
                e.setTo(new Location(Bukkit.getWorld("world"), 711, 69, 1363));
            } else if (p.getLocation().getBlockZ() >= -458 && e.getPlayer().getLocation().getBlockZ() <= -455) {
                e.setTo(new Location(Bukkit.getWorld("world"), 1511, 93, -570));
            } else if (p.getLocation().getBlockZ() >= 142 && e.getPlayer().getLocation().getBlockZ() <= 146) {
                e.setTo(new Location(Bukkit.getWorld("world"), -981, 69, -280));
            } else {
                e.setTo(new Location(Bukkit.getWorld("world"), 518, 73, 325));
            }
        }


        //if (to.getEnvironment().equals(World.Environment.NETHER) || to.getEnvironment().equals(World.Environment.THE_END))
        //    e.setCancelled(true);

        //if (to.getEnvironment().equals(World.Environment.THE_END))
        //    return;

        // if (from.getEnvironment().equals(World.Environment.NORMAL)) {

        //Save database location of player

        //    return;
        // }
        /*if (from.getEnvironment().equals(World.Environment.NETHER)) {

        }*/
    }
}
