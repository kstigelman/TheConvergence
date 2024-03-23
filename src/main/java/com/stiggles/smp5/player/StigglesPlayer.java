package com.stiggles.smp5.player;

import com.stiggles.smp5.stats.CoinBank;
import com.stiggles.smp5.stats.Database;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

/** Represents a player interface between the server and
 *    database.
 */
public class StigglesPlayer
{
    private final SMP5 main;

    private final UUID uuid;
    private final String name;

    private final Player player;
    private final CoinBank coinBank;

    private final HashSet<String> npcTalks;
    private final HashSet<Quest.QuestName> questsCompleted;
    private final HashSet<Integer> convergenceFound;
    private final HashSet<String> dungeonsCompleted;

    boolean cursed;
    boolean chatToggledOn;
    private int killstreak;
    private int convergenceBalance;

    public StigglesPlayer(SMP5 main, Player player) throws SQLException {
        this.main = main;

        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();

        killstreak = 0;
        convergenceBalance = 0;
        cursed = false;
        chatToggledOn = true;

        npcTalks = new HashSet<>();
        questsCompleted = new HashSet<>();
        convergenceFound = new HashSet<>();
        dungeonsCompleted = new HashSet<>();

        Database db = main.getDatabase();
        db.connect();

        ResultSet info = db.query(
                "SELECT * FROM player WHERE uuid = '" + uuid + "';"
        );
        // Load existing player data, if it exists
        if (info.next()) {
            this.coinBank = new CoinBank(player.getUniqueId(), info.getInt(3));
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
            // No player data found, so create new player data
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
        // The Curse of Clato
        if (cursed) {
            player.setDisplayName(org.bukkit.ChatColor.DARK_PURPLE + "Cursed " + ChatColor.WHITE + player.getName());
            if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null)
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
        }
        player.setPlayerListName(player.getDisplayName() + " " + ChatColor.GOLD + getBounty() + "c");
        info.close ();

    }

    /** Add a Dungeon attempt to the Dungeon database table.
     *
     * @param name The player's username
     * @param beganAt Timestamp
     * @param length the duration of the attempt
     * @param difficulty The dungeon's difficulty rating
     * @param won Whether or not the attempt was a success
     */
    public void addDungeonComplete (String name, String beganAt, int length, int difficulty, boolean won) {
        dungeonsCompleted.add (name);
        try {
            main.getDatabase().execute(
                    "INSERT INTO dungeon VALUES ('" +
                    name + "', '" + uuid + "', '" +
                    beganAt + "', " + length + ", " + difficulty + ", " + won + ");");
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Adds a quest completion to the Quest table
     *
     * @param q The enum corresponding to the completed quest
     */
    public void addQuest (Quest.QuestName q) {
        if (!questsCompleted.add (q))
            return;
        try {
            main.getDatabase().execute("INSERT INTO quest VALUES ('" + q.toString() + "', '" + uuid + "', '" + LocalDateTime.now().format(main.getFormatter()) + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Returns how many convergence crystals the player has found.
     *
     * @return The number of crystals
     */
    public int totalConvergenceFound() {
        return convergenceFound.size();
    }

    /** Add a found convergence crystal to the Convergence table.
     *
     * @param hash_id Value computed by the crystal's location
     */
    public void addConvergence(int hash_id) {
        if (!convergenceFound.add(hash_id))
            return;
        try {
            main.getDatabase().execute("INSERT INTO convergence VALUES ('" + uuid + "', " + hash_id + ");");
            convergenceBalance += 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Attempts to remove from the Convergence balance, if possible (unused).
     *
     * @param amount The amount of Convergence to be withdrawn
     * @return True or false
     */
    public boolean withdrawConvergence (int amount) {
        if (convergenceBalance - amount >= 0) {
            convergenceBalance -= amount;

            /*try {
                Database db = main.getDatabase();
                db.execute("");
            }
            catch (SQLException e) {

            }*/
            return true;
        }
        return false;
    }

    /** Add a npc to the list of NPCs that the player has talked to.
     *
     * @param name The name of the NPC
     */
    public void addNPC (String name) {
        if (!npcTalks.add(name))
            return;
        try {
            main.getDatabase().execute("INSERT INTO npc_talks VALUES ('" + uuid + "', '" + name + "', 0);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Check if the player has spoken with an NPC.
     *
     * @param npcName The NPC's name
     * @return True or false
     */
    public boolean hasTalkedTo (String npcName) {
        return npcTalks.contains (npcName);
    }

    /** Check if the player has already found a specific Convergence crystal.
     *
     * @param hash The hash id of the crystal
     * @return True or false
     */
    public boolean hasFoundConvergence (int hash) {
        return convergenceFound.contains(hash);
    }

    /** Set/toggle player coin gain notifications in chat.
     *
     * @param val True or false
     */
    public void setChatToggledOn(boolean val) {
        chatToggledOn = val;

        if (!chatToggledOn)
            main.getToggledChatPlayers().remove (uuid.toString());

        try {
            main.getDatabase().execute("UPDATE player SET chatToggledOn = " + chatToggledOn + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Attempts to withdraw coins from the player's bank.
     *
     * @param amount The amount to withdraw
     * @return True or false
     */
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

    /** Deposits a given amount into the player's bank.
     *
     * @param amount The amount to deposit
     */
    public void deposit (int amount) {
        coinBank.deposit (amount);
        try {
            main.getDatabase().execute("UPDATE player SET balance = " + getBalance() + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Get the Player class linked to this StigglesPlayer.
     *
     * @return The player
     */
    public Player getPlayer () {
        return player;
    }

    /** Get the player's coin balance.
     *
     * @return An integer balance
     */
    public int getBalance () {
        return coinBank.getBalance();
    }

    /** Get the player's username.
     *
     * @return username
     */
    public String getName () {
        return player.getName ();
    }

    /** Get the CoinBank object that belongs to the player.
     *
     * @return CoinBank
     */
    public CoinBank getCoinBank () { return coinBank; }

    /** Get the player's current location in the world.
     *
     * @return The player's location
     */
    public Location getLocation () {
        return player.getLocation ();
    }

    /** Get the current amount of bounty placed on a player.
     *
     * @return Numeric bounty amount
     */
    public int getBounty() {
        return killstreak * 200;
    }

    /** Get the player's current killstreak.
     *
     * @return killstreak
     */
    public int getKillstreak() {
        return killstreak;
    }

    /** Set the player's current killstreak
     *
     * @param amount The killstreak number to be set
     */
    public void setKillstreak(int amount) {
        killstreak = amount;
        try {
            main.getDatabase().execute("UPDATE player SET killstreak = " + killstreak + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Get the player's associated UUID.
     *
     * @return UUID
     */
    public UUID getUUID() {
        return uuid;
    }

    /** Check if the player has the Curse of Clato.
     *
     * @return True or false
     */
    public boolean isCursed() {
        return cursed;
    }

    /** Set a new value for the player's Curse of Clato.
     *
     * @param val True or false
     */
    public void setCursed(boolean val) {
        cursed = val;
        try {
            main.getDatabase().execute("UPDATE player SET cursed = " + cursed + " WHERE uuid = '" + uuid + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Check if the player has chat coin notifications turned on.
     *
     * @return True or false
     */
    public boolean hasChatToggledOn() {
        return chatToggledOn;
    }

    /** Get a list of the names of all NPCs the player has talked to.
     *
     * @return A list of names
     */
    public HashSet<String> getNPCsTalkedTo() {
        return npcTalks;
    }

    /** Get a list of all quests that the player has completed.
     *
     * @return The list of quests as enums
     */
    public HashSet<Quest.QuestName> getQuestsCompleted() {
        return questsCompleted;
    }

    /** Returns if the player has completed a given quest
     *
     * @param questName The name of the quest to be checked
     * @return True or false
     */
    public boolean hasQuestCompleted (Quest.QuestName questName) {
        return questsCompleted.contains(questName);
    }

    @Override
    public String toString() {
        return "[" + uuid + "]:" +
                "\nName: " + name +
                "\nCoins: " + getBalance() +
                "\nKillstreak: " + killstreak +
                "\nQuests: " + getQuestsCompleted().toString() +
                "\nNPCs Talked To: " + getNPCsTalkedTo().toString();
    }
}
