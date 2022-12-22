package com.stiggles.smp5.listeners;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BountyListeners implements Listener {


    @EventHandler
    public void OnPlayerDeath (PlayerDeathEvent e) {
        //Return if player was killed by a mob
        if (e.getEntity ().getKiller () == null)
            return;
        Player victim = e.getEntity ();
        Player killer = victim.getKiller ();

        /** if player is bountyLeader
         * then
         *      killer gets world leader status
         *
         */

    }


}
