package com.stiggles.smp5.stats;

import com.stiggles.smp5.main.Database;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Quest {

    private final static SMP5 main = SMP5.getPlugin();

    public enum QuestName {
        NATALIES_REDEMPTION
    }
    public static void questComplete (Player p, QuestName q) {
       try {
            Database db = main.getDatabase();
            p.sendMessage(ChatColor.WHITE + "You have completed the quest " + ChatColor.GREEN + q);
            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1.f, 1.f);
            db.execute ("INSERT INTO quest VALUES ('" + q + "', '" + p.getUniqueId() + "', '" + LocalDateTime.now().format(main.getFormatter()) + "');");
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Quest: Could not update quest completion for " + p.getUniqueId());
        }
    }
    public static boolean isQuestComplete (Player p, QuestName q) {
        try {
            Database db = main.getDatabase();
            ResultSet rs = db.query("SELECT name FROM quest WHERE uuid = '" + p.getUniqueId() + "' AND name = '" + q.toString() + "';");
            return rs.next();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Quest: Could not check for quest completion for " + p.getUniqueId());
        }
        return false;
    }
}
