package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.managers.Bounty;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class BountyListeners implements Listener {

    private SMP5 main;
    private Bounty bounty;
    public BountyListeners (SMP5 main) {
        this.main = main;
        bounty = new Bounty(main);
    }

    @EventHandler
    public void OnPlayerKill (PlayerDeathEvent e) {
        if (e.getEntity().getKiller() == null)
            return;
        if (e.getEntity().equals (e.getEntity().getKiller()))
            return;

        Player victim = e.getEntity();
        Player killer = victim.getKiller();
        try {
            main.getDatabase().execute("INSERT INTO kills VALUES ('" +
                    killer.getUniqueId() + "', '" +
                    victim.getUniqueId() + "', '" +
                    LocalDateTime.now().format(main.getFormatter()) + "');"
            );
        }
        catch (SQLException x) {
            Bukkit.getConsoleSender().sendMessage("BountyListeners: Could not insert player kill");
        }
        int reward = Bounty.getBounty(victim);
        BankManager.deposit (killer, reward);
        killer.sendMessage (ChatColor.GOLD + "You were rewarded " + reward + " coins for killing " + victim.getName() + "!");

        Bounty.update (killer, Bounty.getKillstreak(killer));
        Bounty.update (killer, 0);
    }
        /* TO-DO:
         * if player is bountyLeader
         * then
         *      killer gets world leader status
         *      give killer bounty from player
         *      Reset bounty on victim
         *      Update bounty for killer
         */


}
