package com.stiggles.smp5.managers;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.CoinBank;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Iterator;

import java.util.UUID;

public class CoinBankManager {

    private final static HashMap<UUID, CoinBank> banks = new HashMap<>();
    private SMP5 main;

    public CoinBankManager (SMP5 main) {
        this.main = main;
    }
    public void onEnable () {
        //Load the CoinBank from database
        try {
            ResultSet rs = main.getDatabase().query("SELECT * FROM bank");
            if (rs != null) {
                while (rs.next()) {
                    UUID uuid = UUID.fromString(rs.getString(1));
                    int balance = rs.getInt(2);
                    banks.put(uuid, new CoinBank(uuid, balance));
                }
                rs.close();
            }
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("CoinBankManager: Could not query database");
        }
    }
    public void onDisable () {
        //Save to database
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

    public static boolean hasAccount (String user) {
        return banks.containsKey(user);
    }
    public static CoinBank getAccount (String user) {
        return banks.get (user);
    }

    public static boolean registerNewAccount (UUID user) {
        if (banks.containsKey (user))
            return false;
        banks.put (user, new CoinBank (user));
        return true;
    }

    public static int getAccountBalance (UUID user) {
        return banks.get (user).getBalance ();
    }
}
