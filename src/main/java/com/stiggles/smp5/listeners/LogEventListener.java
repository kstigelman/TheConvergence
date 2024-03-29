package com.stiggles.smp5.listeners;

import com.stiggles.smp5.stats.Database;
import com.stiggles.smp5.main.SMP5;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/** Listen to player connect/disconnect events. Log player information to SQL database.
 *
 * @author Kyler Stigelman & Kael Hufford
 */
public class LogEventListener implements Listener {

    private final SMP5 main;
    private final ArrayList<UUID> registeredUUIDs;


    public LogEventListener(SMP5 main) {
        this.main = main;

        //Load all players that have previously joined the server
        registeredUUIDs = new ArrayList<>();

        try {
            Database db = main.getDatabase();
            ResultSet rs = db.query("SELECT uuid FROM player;");
            if (rs != null) {
                while (rs.next())
                    registeredUUIDs.add(UUID.fromString(rs.getString(1)));
                rs.close();
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("LogEventListener: Could not query database");
        }
    }

    /** Catches PlayerJoinEvent and adds player to player manager and logs connection in database.
     *
     * @param e The event thrown by a player join.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        //Check if player has joined the server before
        try {
            main.getPlayerManager().addStigglesPlayer(e.getPlayer().getUniqueId(), new StigglesPlayer(main, e.getPlayer()));
        } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage("Failed to add Stiggles Player");
        }
        //logTimes.put (p.getUniqueId(), LocalDateTime.now());
        if (registeredUUIDs.contains(p.getUniqueId())) {
            log(e.getPlayer(), "LOGIN");
            e.setJoinMessage(ChatColor.LIGHT_PURPLE + p.getName() + " has entered The Convergence");

            //Cool SFX, Really just for fun -- accnt
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player, Sound.BLOCK_BEACON_POWER_SELECT, 1, 2);
            }
            p.playSound(p, Sound.BLOCK_BEACON_ACTIVATE, 1, 2);

            //Bounty.setTabName(p);
            if (p.getWorld().getName().equals("testdungeon")) {
                p.teleport(Bukkit.getWorld("world").getSpawnLocation());
                //p.setInvisible(false);
                p.setGameMode(GameMode.SURVIVAL);
                p.setInvisible(false);
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
            db.connect();
            //Register player record
            // db.execute("INSERT INTO player VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', " + 0 + ");");
            //Register bank record
            // db.execute("INSERT INTO bank VALUES ('" + p.getUniqueId() + "', " + 0 + ");");


        } catch (SQLException event) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to register new player.");
        }
        //Bounty.addToMap(p);
        //Bounty.setTabName(p);
        registeredUUIDs.add(p.getUniqueId());
        //BankManager.addPlayer (p);
        //Bukkit.getConsoleSender().sendMessage("Added " + p.getName () + "to bank");
        e.setJoinMessage(ChatColor.LIGHT_PURPLE + p.getName() + " has fallen into The Convergence");

        //Bounty.setKillstreak(p, 1);
        //Bounty.setTabName (p);

        log(e.getPlayer(), "LOGIN");

        if (Bukkit.getWorld("sanctuary") != null)
            cutscene(p);
    }

    /** Catches PlayerQuitEvent and removes player from player manager and logs disconnect in database.
     *
     * @param e The event thrown by a player disconnect.
     */
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.LIGHT_PURPLE + e.getPlayer().getName() + " has left The Convergence");
        log(e.getPlayer(), "LOGOUT");
        main.getPlayerManager().removeStigglesPlayer(e.getPlayer().getUniqueId());
    }

    /** Inserts a player login or logout to the database.
     *
     * @param p The player who triggered the log event.
     * @param logType The type of log (Login or logout).
     */
    public void log(Player p, String logType) {
        String world = p.getWorld().getName();
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
            if (logType.equals("LOGOUT")) {
                int time = p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
                db.execute("UPDATE player SET playtime = " + time + " WHERE uuid = '" + p.getUniqueId() + "';");
            }
        } catch (SQLException event) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to log player " + logType);
        }

    }

    /** Teleports a player to a separate world to show an introductory cutscene to new players.
     *
     * @param p The player that needs to view the introduction.
     */
    public void cutscene(Player p) {
        p.teleport(new Location(Bukkit.getWorld("sanctuary"), 8.5, -59, 6.5));
        p.setInvisible(true);
        p.setGameMode(GameMode.ADVENTURE);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10000000, 1, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, 10, true));
    }
}