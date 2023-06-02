package com.stiggles.smp5.managers;

import com.stiggles.smp5.main.Database;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.CoinBank;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class BankManager {

    private static HashMap<String, Integer> coinAmounts;
    private static HashMap<UUID, CoinBank> banks;
    private static SMP5 main;


    public BankManager (SMP5 main) {
        this.main = main;
        onEnable();
    }

    public static Integer getAmount (String key) {
        return coinAmounts.get (key);
    }

    public static void addPlayer (Player p) {
        banks.put (p.getUniqueId(), new CoinBank (p.getUniqueId()));
    }
    public static void addPlayer (UUID uuid) {
        banks.put (uuid, new CoinBank (uuid));
    }

    public static int getBalance (Player p) {
        return banks.get (p.getUniqueId()).getBalance();
    }
    public static int getBalance (UUID uuid) {
        return banks.get (uuid).getBalance();
    }
    public static boolean deposit (Player p, int amount) {
        return deposit (p.getUniqueId(), amount);
    }
    public static boolean deposit (UUID uuid, int amount) {
        if (banks.get (uuid) == null)
            return false;
        banks.get (uuid).deposit(amount);
        return true;
    }
    public static boolean withdraw (Player p, int amount) {
        return banks.get (p.getUniqueId()).withdraw (amount);
    }
    public static boolean withdraw (UUID uuid, int amount) {
        return banks.get (uuid).withdraw (amount);
    }
    public static boolean hasSufficientFunds (Player p, int amount) {
        return getBalance(p) > amount;
    }
    public static boolean hasSufficientFunds (UUID uuid, int amount) {
        return getBalance(uuid) > amount;
    }
    public static boolean hasAccount (UUID user) {
        return banks.containsKey(user);
    }
    public static boolean hasAccount (Player p) {
        return banks.containsKey(p.getUniqueId());
    }
    public static CoinBank getAccount (UUID user) {
        return banks.get (user);
    }
    public static CoinBank getAccount (Player p) {
        return banks.get (p.getUniqueId());
    }

    public static boolean registerNewAccount (UUID user) {
        if (banks.containsKey (user))
            return false;
        banks.put (user, new CoinBank (user));
        return true;
    }
    public static boolean registerNewAccount (Player p) {
        UUID uuid = p.getUniqueId();
        if (banks.containsKey (uuid))
            return false;
        banks.put (uuid, new CoinBank (uuid));
        return true;
    }

    public void onEnable () {
        coinAmounts = new HashMap<>();
        banks = new HashMap<>();

        FileConfiguration coins = main.getConfig();
        //Grab all the coin amounts for config file and save in hashmap.
        for (String key : coins.getKeys(true))
            coinAmounts.put (key, coins.getInt (key));

        //Load the CoinBank from database
        if (!main.getDatabase().isConnected())
            return;

        try {
            Database db = main.getDatabase();
            if (db.getConnection() == null)
                return;

            ResultSet rs = db.query("SELECT * FROM bank;");

            if (rs == null)
                return;

            while (rs.next ()) {
                UUID uuid = UUID.fromString(rs.getString(1));
                int balance = rs.getInt (2);
                banks.put (uuid, new CoinBank(uuid, balance));
            }
            rs.close ();
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("CoinBankManager: Could not query database");
        }
    }
    public static void onDisable () {
        //Save to database
        if (!main.getDatabase().isConnected())
            return;
        for (java.util.Map.Entry<UUID, CoinBank> uuidCoinBankEntry : banks.entrySet()) {
            CoinBank mapElement = uuidCoinBankEntry.getValue();
            int balance = mapElement.getBalance();
            String uuid = mapElement.getOwner().toString();
            try {
                Database db = main.getDatabase();
                db.execute(
                        "UPDATE bank SET balance = " + balance + " WHERE uuid = '" + uuid + "';"
                );
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("Failed to update");
            }
        }
    }
}
