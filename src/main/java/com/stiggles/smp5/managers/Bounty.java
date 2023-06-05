package com.stiggles.smp5.managers;

import com.stiggles.smp5.main.Database;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.UUID;

public class Bounty {

    private static SMP5 main;
    private static Database db;
    private static UUID leader;
    private static final HashMap<UUID, Integer> killstreak = new HashMap<>();

    public Bounty (SMP5 main) {
        this.main = main;
        db = main.getDatabase();
    }
    public static void initializeMap (SMP5 smp_main) {
        main = smp_main;
        db = main.getDatabase();

        try {
            ResultSet rs = db.query("SELECT uuid FROM player;");

            if (rs != null) {
                while (rs.next()) {
                    String uuid = rs.getString(1);
                    ResultSet lastDeath = db.query ("(SELECT DISTINCT timestamp FROM kills WHERE victim ='" + uuid +"' ORDER BY timestamp desc);");

                    ResultSet kills;

                    if (lastDeath.next()) {
                        String prev = lastDeath.getString(1);
                        kills =  db.query("SELECT COUNT(killer) FROM kills WHERE killer = '" + uuid + "' AND timestamp > '" + prev + "';");
                    }
                    else {
                        kills =  db.query("SELECT COUNT(killer) FROM kills WHERE killer = '" + uuid + "';");
                        //No kills & no deaths == New player
                        if (kills.next () && kills.getInt(1) == 0) {
                            killstreak.put (UUID.fromString(uuid), 1);
                            kills.close ();
                            lastDeath.close ();
                            return;
                        }
                    }

                    if (kills.next ())
                        killstreak.put (UUID.fromString(uuid), kills.getInt(1));
                    else
                        killstreak.put (UUID.fromString(uuid), 0);

                    kills.close ();
                    lastDeath.close ();
                    //ResultSet deaths = db.query("SELECT COUNT(victim) FROM kills WHERE victim = '" + uuid + "';");

                            /*
                    int kR = 0;
                    int dR = 0;

                    if (kills != null && deaths != null) {
                        if (kills.next ())
                            kR = kills.getInt(1);
                        if (deaths.next ())
                            dR = deaths.getInt(1);

                        if (kR == 0 && dR == 0) {
                            killstreak.put(UUID.fromString(uuid), 50);
                            return;
                        }
                    }
                    if (kills != null && kills.next ())
                        amounts.put (UUID.fromString(uuid), kills.getInt(1) * 50);*/
                }
                rs.close ();
            }

        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Bounty: Could not calculate bounty");
        }
        findLeader();
    }
    public static void update (Player p, int newAmount) {
        setKillstreak (p, newAmount);

        if (newAmount > getKillstreak(p))
            leader = p.getUniqueId();

        if (p != null)
            setTabName(p);
    }
    public static void update (UUID uuid, int newAmount) {
        setKillstreak (uuid, newAmount);
        if (newAmount > getKillstreak(leader))
            leader = uuid;

        Player p = Bukkit.getPlayer(uuid);

        if (p != null)
            setTabName(p);
    }
    public static void setTabName (Player p) {
        if (p == null)
            return;
        String thisLeader = "";
        if (leader != null && leader.equals(p.getUniqueId()))
            thisLeader = ChatColor.RED + ChatColor.BOLD.toString() + " LEADER";
        p.setPlayerListName(p.getDisplayName() + " " + ChatColor.GOLD + getBounty(p) + "c" + thisLeader);
    }
    public static int getBounty (Player p) {
        return getBounty(p.getUniqueId());
    }

    public static int getBounty (UUID uuid) {
        return getKillstreak(uuid) * 50;
    }
    public static int getKillstreak (Player p) {
        return getKillstreak(p.getUniqueId());
    }
    public static int getKillstreak (UUID uuid) {
        return killstreak.get (uuid);
    }
    public static void setKillstreak (Player p, int amount) {
        setKillstreak(p.getUniqueId(), amount);
    }
    public static void setKillstreak (UUID uuid, int amount) {
        killstreak.put (uuid, amount);
    }

    public static UUID findLeader () {
        int max = -1;

        try {
            //Retrieve all players who have logged in within the past week
            LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
            ResultSet rs = db.query("SELECT DISTINCT uuid FROM log WHERE log_time > '" + oneWeekAgo.format(main.getFormatter()) + "';");
            //For each UUID in the results, calculate the bounty for that player.
            while (rs.next()) {
                UUID playerUUID = UUID.fromString(rs.getString(1));
                int bounty = getBounty(playerUUID);
                if (bounty > max) {
                    max = bounty;
                    leader = playerUUID;
                }
            }
            rs.close();
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Bounty: Could not compute bounty leader");
        }
        return leader;
    }
}
