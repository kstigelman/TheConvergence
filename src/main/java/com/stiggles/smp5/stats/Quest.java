package com.stiggles.smp5.stats;

import com.stiggles.smp5.main.Database;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.player.StigglesPlayer;
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
        NOUVEAU_INTRO,
        NATALIES_REDEMPTION,
        MORABITO_RECIPE,
        THE_GOOD_HUNT,
        WARDEN_KILL,
        SMALL_STEP,
        BLUEPRINTS,
        APPLE_A_DAY
    }
    public static void questComplete (Player p, QuestName q, String questMessage, int amount) {
       try {
            Database db = main.getDatabase();

           if (q.equals(QuestName.NOUVEAU_INTRO)) {
               db.execute ("INSERT INTO quest VALUES ('" + q + "', '" + p.getUniqueId() + "', '" + LocalDateTime.now().format(main.getFormatter()) + "');");
               return;
           }
           StigglesPlayer stigglesPlayer = main.getPlayerManager().getStigglesPlayer(p.getUniqueId());
           if (questMessage != null) {
               stigglesPlayer.addQuest(q);
               p.sendMessage(ChatColor.WHITE + "You have completed the quest " + ChatColor.GREEN + questMessage);
               p.playSound(p, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.f, 1.f);
           }
           if (amount != 0) {
               p.sendMessage(ChatColor.GOLD + "+" + amount + " coins");
               stigglesPlayer.deposit(amount);
               //BankManager.deposit(p, amount);
           }

            //main.getPlayerManager().getStigglesPlayer(p.getUniqueId()).addQuest(q);
            //db.execute ("INSERT INTO quest VALUES ('" + q + "', '" + p.getUniqueId() + "', '" + LocalDateTime.now().format(main.getFormatter()) + "');");

       }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Quest: Could not update quest completion for " + p.getUniqueId());
        }
    }

    public static boolean isQuestComplete (Player p, QuestName q) {
        return main.getPlayerManager().getStigglesPlayer(p.getUniqueId()).getQuestsCompleted().contains(q);
        /*try {
            Database db = main.getDatabase();
            ResultSet rs = db.query("SELECT name FROM quest WHERE uuid = '" + p.getUniqueId() + "' AND name = '" + q.toString() + "';");
            return rs.next();

        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Quest: Could not check for quest completion for " + p.getUniqueId());
        }
        return false;*/
    }
}
