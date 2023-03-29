package com.stiggles.smp5.managers;

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
    private SMP5 main;


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
    public static void deposit (Player p, int amount) {
        banks.get (p.getUniqueId()).deposit(amount);
    }
    public static void deposit (UUID uuid, int amount) {
        banks.get (uuid).deposit(amount);
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
        try {
            ResultSet rs = main.getDatabase().query("SELECT * FROM bank");
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
    public void onDisable () {
        //Save to database
        try {
            Iterator iter = banks.entrySet().iterator();
            while (iter.hasNext()) {
                CoinBank mapElement = (CoinBank) iter.next();
                int balance = mapElement.getBalance();
                String uuid = mapElement.getOwner().toString();

                main.getDatabase().execute(
                        "UPDATE bank SET coins = " + balance + " WHERE uuid = '" + uuid + "')"
                );
            }
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("CoinBankManager: Could not insert into database");
        }
    }
}
