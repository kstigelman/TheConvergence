package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.managers.Bounty;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
        //Move this line to the if statement below if we decide to reward skulls on bounty kills.


        int reward = Bounty.getBounty(victim);
        if (reward != 0) {
            BankManager.deposit(killer, reward);
            victim.getWorld().dropItem(victim.getLocation(), getPlayerSkull(victim, killer.getName()));
            killer.sendMessage(ChatColor.GOLD + "You were rewarded " + reward + " coins for killing " + victim.getName() + "!");
            Bounty.update (killer, Bounty.getKillstreak(killer) + 1);
            Bounty.update (victim, 0);
        }
        //if (Bounty.getKillstreak(killer) == null)


    }
        /* TO-DO:
         * if player is bountyLeader
         * then
         *      killer gets world leader status
         *      give killer bounty from player
         *      Reset bounty on victim
         *      Update bounty for killer
         */

    public ItemStack getPlayerSkull (Player victim, String killer) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwnerProfile(victim.getPlayerProfile());
        meta.setOwningPlayer(victim);
        meta.setDisplayName(ChatColor.YELLOW + victim.getName() + "'s Head");
        meta.setLore(Arrays.asList (ChatColor.DARK_GRAY + "Killed by " + killer + " on " + LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        skull.setItemMeta(meta);
        return skull;
    }
}
