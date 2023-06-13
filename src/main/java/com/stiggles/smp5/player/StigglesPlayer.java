package com.stiggles.smp5.player;

import com.stiggles.smp5.main.Database;
import com.stiggles.smp5.main.PlayerManager;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.managers.Bounty;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private HashSet<Quest.QuestName> convergenceFound = new HashSet<>();

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

        ResultSet rs = db.query(
                "SELECT * FROM player_info WHERE uuid = '" + uuid.toString() + "';"
        );

        if (rs.next()) {
            killstreak = Bounty.getKillstreak(player);
            cursed = rs.getBoolean(6);
            chatToggledOn = rs.getBoolean(7);
        }
        else {
            Bounty.addToMap(player);
            Bounty.setKillstreak(player, 1);
            Bounty.setTabName(player);

            BankManager.addPlayer (player);

            main.getDatabase().execute("INSERT INTO player_info VALUES ('" +
                uuid + "', '" +
                player.getName () + "', " +
                0 + ", " +
                killstreak + ", " +
                0 + ", " +
                cursed + ", " +
                chatToggledOn + ");"
            );

        }
        rs.close ();
        Bukkit.getConsoleSender().sendMessage("Stiggles Player [" + player.getName()+ "]: Failed to register");


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


    public void setKillstreak (int amount) {
        killstreak = amount;
        Bounty.setKillstreak(player, amount);
        try {
            main.getDatabase().execute("UPDATE player_info SET killstreak = " + killstreak + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setCursed (boolean val) {
        cursed = val;
        try {
            main.getDatabase().execute("UPDATE player_info SET cursed = " + cursed + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setChatToggledOn (boolean val) {
        chatToggledOn = val;
        try {
            main.getDatabase().execute("UPDATE player_info SET chatToggledOn = " + chatToggledOn + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void withdraw (int amount) {
        if (!BankManager.withdraw(player, amount))
            return;
        try {
            main.getDatabase().execute("UPDATE player_info SET balance = " + getBalance() + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deposit (int amount) {
        BankManager.deposit (player, amount);
        try {
            main.getDatabase().execute("UPDATE player_info SET balance = " + getBalance() + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Player getPlayer () {
        return player;
    }
    public int getBalance () {
        return BankManager.getBalance(player);
    }
    public String getName () {
        return player.getName ();
    }
    public Location getLocation () {
        return player.getLocation ();
    }
    public int getBounty () {
        return killstreak * 50;
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
        return getName () + ": " +
                          "\n\tLocation: " + getLocation () +
                          "\n\tCoins: " + getBalance ();
    }



}
