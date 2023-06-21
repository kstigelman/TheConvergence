package com.stiggles.smp5.player;

import com.stiggles.smp5.main.Database;
import com.stiggles.smp5.main.PlayerManager;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.managers.Bounty;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;


public class StigglesPlayer
{
    private SMP5 main;

    private UUID uuid; //
    private String name;

    private Player player;
    private CoinBank coinBank;

    private HashSet<String> npcTalks = new HashSet<>();
    private HashSet<Quest.QuestName> questsCompleted = new HashSet<>();
    private HashSet<Integer> convergenceFound = new HashSet<>();

    private int killstreak;

    boolean cursed;
    boolean chatToggledOn;

    public StigglesPlayer (SMP5 main, Player player) throws SQLException {
        this.main = main;

        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();

        killstreak = 0;
        cursed = false;
        chatToggledOn = true;

        Database db = main.getDatabase();
        db.connect ();


        ResultSet info = db.query(
                "SELECT * FROM player WHERE uuid = '" + uuid.toString() + "';"
        );
        if (info.next()) {
            this.coinBank = new CoinBank (player.getUniqueId(), info.getInt(3));
            killstreak = info.getInt(4);
            cursed = info.getBoolean(6);
            chatToggledOn = info.getBoolean(7);

            if (!chatToggledOn)
                main.getToggledChatPlayers().add(uuid.toString());

            ResultSet cv = db.query(
                    "SELECT hash_id FROM convergence WHERE uuid = '" + uuid.toString() + "';"
            );
            while (cv.next ())
                convergenceFound.add (cv.getInt(1));
            cv.close();

            ResultSet quest = db.query(
                    "SELECT name FROM quest WHERE uuid = '" + uuid.toString() + "';"
            );
            while (quest.next ())
                questsCompleted.add (Quest.QuestName.valueOf(quest.getString(1)));
            quest.close();

            ResultSet npc = db.query(
                    "SELECT npc_name FROM npc_talks WHERE uuid = '" + uuid.toString() + "';"
            );
            while (npc.next ())
                npcTalks.add (npc.getString (1));
            npc.close();
        }
        else {
            killstreak = 1;
            this.coinBank = new CoinBank (player.getUniqueId(), 0);

            main.getDatabase().execute("INSERT INTO player VALUES ('" +
                uuid + "', '" +                 //uuid
                player.getName () + "', " +     //name
                0 + ", " +                      //balance
                killstreak + ", " +             //killstreak
                0 + ", " +                      //playtime
                cursed + ", " +                 //cursed
                chatToggledOn + ");"            //chatToggledOn
            );
        }
        player.setPlayerListName(player.getDisplayName() + " " + ChatColor.GOLD + getBounty() + "c");
        info.close ();

        //Bukkit.getConsoleSender().sendMessage("Stiggles Player [" + player.getName()+ "]: Failed to register");


        //Check if player has joined the server before
        //Fetch StigglesPlayer from player
        //If null, they are a new player.
        //Otherwise, store this info in the player object
        //coinBank = new CoinBank(this.player.getUniqueId ());
        /*
        Database db = main.getDatabase();
        try {
            ResultSet rs = db.query("SELECT * FROM player");
            if (!rs.next()) {
                coinBank = new CoinBank(this.player.getUniqueId ());

                try {
                    main.getDatabase().execute("INSERT INTO player VALUES ('" +
                            uuid + "', '" +
                            player.getName () + "', " +
                            0 + ", " +
                            0 + ", " +
                            0 + ", " +
                            0 + ", " +
                            0 + ");"
                    );
                }
                catch (SQLException e) {

                }
            }
            else {
                coinBank = new CoinBank(uuid, rs.getInt(3));
                killstreak = rs.getInt(4);
                cursed = rs.getBoolean(6);
                chatToggledOn = rs.getBoolean(7);
            }
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Stiggles Player [" + player.getName()+ "]: Failed to register");
        }*/
    }

    public void addQuest (Quest.QuestName q) {
        if (!questsCompleted.add (q))
            return;
        try {
            main.getDatabase().execute("INSERT INTO quest VALUES ('" + q.toString() + "', '" + uuid + "', " + LocalDateTime.now().format(main.getFormatter()) + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addConvergence (int hash_id) {
        if (!convergenceFound.add(hash_id))
            return;
        try {
            main.getDatabase().execute("INSERT INTO convergence VALUES ('" + uuid + "', " + hash_id + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addNPC (String name) {
        if (!npcTalks.add(name))
            return;
        try {
            main.getDatabase().execute("INSERT INTO npc_talks VALUES ('" + uuid + "', '" + name + "', 0);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setKillstreak (int amount) {
        killstreak = amount;
        try {
            main.getDatabase().execute("UPDATE player SET killstreak = " + killstreak + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setCursed (boolean val) {
        cursed = val;
        try {
            main.getDatabase().execute("UPDATE player SET cursed = " + cursed + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setChatToggledOn (boolean val) {
        chatToggledOn = val;

        if (!chatToggledOn)
            main.getToggledChatPlayers().remove (uuid.toString());

        try {
            main.getDatabase().execute("UPDATE player SET chatToggledOn = " + chatToggledOn + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean withdraw (int amount) {
        if (!coinBank.withdraw(amount))
            return false;
        try {
            main.getDatabase().execute("UPDATE player SET balance = " + getBalance() + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void deposit (int amount) {
        coinBank.deposit (amount);
        try {
            main.getDatabase().execute("UPDATE player SET balance = " + getBalance() + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Player getPlayer () {
        return player;
    }
    public int getBalance () {
        return coinBank.getBalance();
    }
    public String getName () {
        return player.getName ();
    }
    public CoinBank getCoinBank () { return coinBank; }
    public Location getLocation () {
        return player.getLocation ();
    }
    public int getBounty () {
        return killstreak * 200;
    }
    public int getKillstreak () {
        return killstreak;
    }
    public UUID getUUID () {
        return uuid;
    }
    public boolean isCursed () {
        return cursed;
    }
    public boolean hasChatToggledOn () {
        return chatToggledOn;
    }
    public HashSet<String> getNPCsTalkedTo () {
        return npcTalks;
    }
    public HashSet<Quest.QuestName> getQuestsCompleted () {
        return questsCompleted;
    }

    public void initializeNewPlayer (SMP5 main, Player p) {
        Database db = main.getDatabase();
    }

    @Override
    public String toString () {
        return "[" + uuid + "]:" +
                "\nName: " + name +
                "\nCoins: " + getBalance() +
                "\nKillstreak: " + killstreak +
                "\nQuests: " + getQuestsCompleted().toString() +
                "\nNPCs Talked To: " + getNPCsTalkedTo().toString();
    }



}
