package com.stiggles.smp5.listeners;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.PortalCreateEvent;


/**
 * Detect when player enters or exits the Nether world to save to database.
 */
public class NetherListener implements Listener {

    @EventHandler
    public void onLightPortal (PortalCreateEvent e) {
        if (e.getEntity() == null || !e.getEntity().isOp ())
            e.setCancelled(true);
    }
    @EventHandler
    public void onNetherEvent (PlayerPortalEvent e) {
        World from = e.getFrom ().getWorld ();

        if (e.getTo () == null || from == null)
            return;

        World to = e.getTo ().getWorld();

        if (to == null)
            return;

        if (to.getEnvironment().equals(World.Environment.NETHER) || to.getEnvironment().equals(World.Environment.THE_END))
            e.setCancelled(true);

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
