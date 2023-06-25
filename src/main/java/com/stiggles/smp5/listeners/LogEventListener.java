package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.Database;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.managers.Bounty;
import com.stiggles.smp5.player.StigglesPlayer;
import com.stiggles.smp5.stats.Quest;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Listen to player connect/disconnect events. Log player information to SQL database.
 *
 */
public class LogEventListener implements Listener {

    private SMP5 main;
    private final ArrayList<UUID> registeredUUIDs;
    private final HashMap<UUID, LocalDateTime> logTimes;
    public LogEventListener (SMP5 main) {
        this.main = main;

        //Load all players that have previously joined the server
        registeredUUIDs = new ArrayList<>();
        logTimes = new HashMap<>();
        try {
            Database db = main.getDatabase();
            ResultSet rs = db.query("SELECT uuid FROM player;");
            if (rs != null) {
                while (rs.next())
                    registeredUUIDs.add(UUID.fromString(rs.getString(1)));
                rs.close();
            }
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("LogEventListener: Could not query database");
        }
    }
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {
        /*if (!main.isOpen() && !e.getPlayer().isOp()) {
            e.getPlayer().kickPlayer(ChatColor.RED + "Server is not yet open!");
            return;
        }*/
        Player p = e.getPlayer();
        //Check if player has joined the server before
        try {
            main.getPlayerManager().addStigglesPlayer(e.getPlayer().getUniqueId(), new StigglesPlayer(main, e.getPlayer()));
        }
        catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage("Failed to add Stiggles Player");
        }
        //logTimes.put (p.getUniqueId(), LocalDateTime.now());
        if (registeredUUIDs.contains(p.getUniqueId())) {
            log (e.getPlayer(), "LOGIN");
            e.setJoinMessage(ChatColor.LIGHT_PURPLE + p.getName() + " has entered The Convergence");

            //Cool SFX, Really just for fun -- accnt
            for(Player player : Bukkit.getOnlinePlayers()){
                player.playSound(player, Sound.BLOCK_BEACON_POWER_SELECT, 1, 2);
            }
            p.playSound(p, Sound.BLOCK_BEACON_ACTIVATE, 1, 2);

            //Bounty.setTabName(p);

            StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(p.getUniqueId());

            if (sp.isCursed()) {
                if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null)
                    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
            }

            if (p.getWorld().getName().equals("sanctuary")) {
                if (!Quest.isQuestComplete(p, Quest.QuestName.NOUVEAU_INTRO))
                    return;

                p.teleport(Bukkit.getWorld("world").getSpawnLocation());
                //p.setInvisible(false);
                p.setGameMode(GameMode.SURVIVAL);
                p.setInvisible(false);
                p.removePotionEffect(PotionEffectType.BLINDNESS);
                p.removePotionEffect(PotionEffectType.SLOW);
            }
            return;
        }
        //New player
        try {
            Database db = main.getDatabase();
            db.connect ();
            //Register player record
            // db.execute("INSERT INTO player VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', " + 0 + ");");
            //Register bank record
            // db.execute("INSERT INTO bank VALUES ('" + p.getUniqueId() + "', " + 0 + ");");


        }
        catch (SQLException event) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to register new player.");
        }
        //Bounty.addToMap(p);
        //Bounty.setTabName(p);
        registeredUUIDs.add (p.getUniqueId());
        //BankManager.addPlayer (p);
        //Bukkit.getConsoleSender().sendMessage("Added " + p.getName () + "to bank");
        e.setJoinMessage(ChatColor.LIGHT_PURPLE + p.getName() + " has fallen into The Convergence");

        //Bounty.setKillstreak(p, 1);
        //Bounty.setTabName (p);

        log (e.getPlayer(), "LOGIN");

        if (Bukkit.getWorld ("sanctuary") != null)
            cutscene (p);
    }

    @EventHandler
    public void onPlayerLeave (PlayerQuitEvent e) {
        //if (!main.isOpen() && !e.getPlayer().isOp())
        //   return;

        e.setQuitMessage(ChatColor.LIGHT_PURPLE + e.getPlayer().getName() + " has left The Convergence");
        log (e.getPlayer(), "LOGOUT");
        main.getPlayerManager().removeStigglesPlayer(e.getPlayer().getUniqueId());
    }

    public void log (Player p, String logType){
        String world = p.getWorld ().getName();
        if (p.getWorld().getEnvironment() == World.Environment.NETHER)
            world = "nether";
        if (p.getWorld().getEnvironment() == World.Environment.THE_END)
            world = "end";

        try {
            Database db = main.getDatabase();
            db.connect();
            db.execute(
                    "INSERT INTO log VALUES ('"
                            + p.getUniqueId() + "', '"
                            + LocalDateTime.now().format(main.getFormatter()) + "', '"
                            + logType + "', '"
                            + world + "', "
                            + p.getLocation().getBlockX() + ", "
                            + p.getLocation().getBlockY() + ", "
                            + p.getLocation().getBlockZ() + ");"
            );
            if (logType.equals ("LOGOUT")) {
                int time = p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
                //db.execute("UPDATE player SET playtime = " + time + " WHERE uuid = '" + p.getUniqueId() + "';");
                db.execute("UPDATE player SET playtime = " + time + " WHERE uuid = '" + p.getUniqueId() + "';");
                //db.execute("UPDATE bank SET balance = " + BankManager.getBalance(p) + " WHERE uuid = '" + p.getUniqueId() + "';");
            }
        }
        catch (SQLException event) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to log player " + logType);
        }

    }
    public void cutscene (Player p) {
        p.teleport(new Location (Bukkit.getWorld("sanctuary"), 8.5, -59, 6.5));
        p.setInvisible(true);
        p.setGameMode(GameMode.ADVENTURE);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10000000, 1, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, 10, true));

    }
}

/*package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.Database;
import com.stiggles.smp5.main.PlayerManager;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.managers.Bounty;
import com.stiggles.smp5.player.StigglesPlayer;
import com.stiggles.smp5.stats.Quest;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class LogEventListener implements Listener {

    private SMP5 main;
    private final ArrayList<UUID> registeredUUIDs;
    private final HashMap<UUID, LocalDateTime> logTimes;
    public LogEventListener (SMP5 main) {
        this.main = main;

        //Load all players that have previously joined the server
        registeredUUIDs = new ArrayList<>();
        logTimes = new HashMap<>();
        try {
            Database db = main.getDatabase();
            ResultSet rs = db.query("SELECT uuid FROM player;");
            //ResultSet rs = db.query("SELECT uuid FROM player_info;");
            if (rs != null) {
                while (rs.next())
                    registeredUUIDs.add(UUID.fromString(rs.getString(1)));
                rs.close();
            }
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("LogEventListener: Could not query database");
        }
    }
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {

        Player p = e.getPlayer();
        //Check if player has joined the server before
        //Fetch StigglesPlayer from player
        //If null, they are a new player.
        //Otherwise, store this info in the player object
        StigglesPlayer playerData;
        try {
            playerData = new StigglesPlayer(main, p);
            main.getPlayerManager().addStigglesPlayer(p.getUniqueId(), playerData);
        }
        catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage("Failed to load stiggles player");
        }

        if (registeredUUIDs.contains(p.getUniqueId())) {
            log (e.getPlayer(), "LOGIN");
            e.setJoinMessage(ChatColor.LIGHT_PURPLE + p.getName() + " has entered The Convergence");
            Bounty.setTabName(p);

            if (p.getWorld().getName().equals("sanctuary")) {
                if (!Quest.isQuestComplete(p, Quest.QuestName.NOUVEAU_INTRO))
                    return;

                p.teleport(Bukkit.getWorld("world").getSpawnLocation());
                //p.setInvisible(false);
                p.setGameMode(GameMode.SURVIVAL);
                p.setInvisible(false);
                p.removePotionEffect(PotionEffectType.BLINDNESS);
                p.removePotionEffect(PotionEffectType.SLOW);
            }
            return;
        }
        //New player
        try {
            Database db = main.getDatabase();
            db.connect();
            //Register player record
            db.execute("INSERT INTO player VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', " + 0 + ");");
            //Register bank record
            db.execute("INSERT INTO bank VALUES ('" + p.getUniqueId() + "', " + 0 + ");");

        }
        catch (SQLException event) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to register new player.");
        }
        registeredUUIDs.add (p.getUniqueId());

        Bounty.addToMap(p);
        Bounty.setTabName(p);
        BankManager.addPlayer(p);

        e.setJoinMessage(ChatColor.LIGHT_PURPLE + p.getName() + " has fallen into The Convergence");

        Bounty.setKillstreak(p, 1);
        Bounty.setTabName (p);

        log (e.getPlayer(), "LOGIN");

        if (Bukkit.getWorld ("sanctuary") != null)
            cutscene (p);
    }

    @EventHandler
    public void onPlayerLeave (PlayerQuitEvent e) {
        //if (!main.isOpen() && !e.getPlayer().isOp())
         //   return;
        main.getPlayerManager().removeStigglesPlayer(e.getPlayer().getUniqueId());
        e.setQuitMessage(ChatColor.LIGHT_PURPLE + e.getPlayer().getName() + " has left The Convergence");
        log (e.getPlayer(), "LOGOUT");
    }

    public void log (Player p, String logType){
        String world = p.getWorld ().getName();
        if (p.getWorld().getEnvironment() == World.Environment.NETHER)
            world = "nether";
        if (p.getWorld().getEnvironment() == World.Environment.THE_END)
            world = "end";

        try {
            Database db = main.getDatabase();
            db.connect();
            //Change foreign key
            db.execute(
                    "INSERT INTO log VALUES ('"
                            + p.getUniqueId() + "', '"
                            + LocalDateTime.now().format(main.getFormatter()) + "', '"
                            + logType + "', '"
                            + world + "', "
                            + p.getLocation().getBlockX() + ", "
                            + p.getLocation().getBlockY() + ", "
                            + p.getLocation().getBlockZ() + ");"
            );
            if (logType.equals ("LOGOUT")) {
                int time = p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
                //db.execute("UPDATE player SET playtime = " + time + " WHERE uuid = '" + p.getUniqueId() + "';");
                db.execute("UPDATE bank SET balance = " + BankManager.getBalance(p) + " WHERE uuid = '" + p.getUniqueId() + "';");

                db.execute("UPDATE player_info SET playtime = " + time + " WHERE uuid = '" + p.getUniqueId() + "';");
                db.execute("UPDATE player_info SET balance = " + BankManager.getBalance(p) + " WHERE uuid = '" + p.getUniqueId() + "';");

                //db.execute("UPDATE player SET playtime = " + time + " WHERE uuid = '" + p.getUniqueId() + "';");

            }
        }
        catch (SQLException event) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to log player " + logType);
        }

    }
    public void cutscene (Player p) {
        p.teleport(new Location (Bukkit.getWorld("sanctuary"), 8.5, -59, 6.5));
        p.setInvisible(true);
        p.setGameMode(GameMode.ADVENTURE);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10000000, 1, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, 10, true));

    }
}*/