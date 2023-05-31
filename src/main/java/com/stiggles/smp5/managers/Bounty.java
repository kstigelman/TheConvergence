package com.stiggles.smp5.managers;

import com.stiggles.smp5.main.Database;
import com.stiggles.smp5.main.SMP5;
import net.citizensnpcs.Citizens;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Bounty {

    private static SMP5 main;
    private static Database db;
    public Bounty (SMP5 main) {
        this.main = main;
        db = main.getDatabase();
    }
    public static void update (UUID uuid) {
        calculateBounty(uuid);
        findLeader();
        Player p = Bukkit.getPlayer(uuid);

        if (p != null)
            setTabName(p);
    }
    public static void setTabName (Player p) {
        String leader = "";
        if (findLeader().equals(p.getUniqueId()))
            leader = ChatColor.RED + ChatColor.BOLD.toString() + "LEADER";
        p.setPlayerListFooter(ChatColor.GOLD + String.valueOf(calculateBounty(p)) + "g" + leader);
    }
    public static int calculateBounty (Player p) {
        return calculateBounty(p.getUniqueId());
    }

    public static int calculateBounty (UUID uuid) {
        //TO-DO: Check if player's death is more recent than their death.
        int total = 0;

        try {
            //Find the player's kills and deaths within the past week.
            LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
            ResultSet kills = db.query("SELECT count (killer) FROM kills WHERE killer = '" + uuid + "' AND timestamp > '" + oneWeekAgo.format(main.getFormatter()) + "';");
            ResultSet deaths = db.query("SELECT count (victim) FROM kills WHERE victim = '" + uuid + "' AND timestamp > '" + oneWeekAgo.format(main.getFormatter()) + "';");

            //For a brand new player, the starting bounty is 50 coins
            int kR = 0;
            int dR = 0;

            if (kills != null && deaths != null) {
                if (kills.next ())
                    kR = kills.getInt(1);

                if (deaths.next ())
                    dR = kills.getInt(1);

                if (dR >= 3 * kR)
                    kR = 0;

                if (kR == 0 && dR == 0)
                    kR = 1;
            }
            total = (50 * kR) / (dR + 1);
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Bounty: Could not calculate bounty");
        }
        return total;
    }

    public static UUID findLeader () {
        UUID leader = null;
        int max = -1;
        try {
            //Retrieve all players who have logged in within the past week
            LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
            ResultSet rs = db.query("SELECT DISTINCT uuid FROM log WHERE log_time > '" + oneWeekAgo.format(main.getFormatter()) + "';");

            //For each UUID in the results, calculate the bounty for that player.
            if (rs != null) {
                while (rs.next()) {
                    UUID playerUUID = UUID.fromString(rs.getString(1));
                    int bounty = calculateBounty(playerUUID);
                    if (bounty > max) {
                        max = bounty;
                        leader = playerUUID;
                    }
                }
                rs.close();
            }
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Bounty: Could not compute bounty leader");
        }
        return leader;
    }
}