package com.stiggles.smp5.managers;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.CoinBank;
import it.unimi.dsi.fastutil.Hash;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;

public class BankManager {

    private static HashMap<String, Integer> coinAmounts;
    private static HashMap<String, CoinBank> playerBanks;
    private SMP5 main;


    public BankManager (SMP5 main) {

        this.main = main;
        coinAmounts = new HashMap<>();
        playerBanks = new HashMap<>();

        FileConfiguration coins = main.getConfig();

        System.out.println(coins.getKeys(false));
        for (String key : coins.getKeys(true)) {
            coinAmounts.put (key, coins.getInt (key));
            System.out.println("Key: " + key);
        }
    }

    public static Integer getAmount (String key) {
            return coinAmounts.get (key);
    }

    public static void addPlayer (Player p) {
        playerBanks.put (p.getName (), new CoinBank (p.getUniqueId()));
    }

    public static int getBalance (Player p) {
        return playerBanks.get (p.getName()).getBalance();
    }
    public static void deposit (Player p, int amount) {
        playerBanks.get (p.getName ()).deposit(amount);
    }
    public static void withdraw (Player p, int amount) {
        playerBanks.get (p.getName()).withdraw (amount);
    }

    public static boolean hasSufficientFunds (Player p, int amount) {
        return getBalance(p) > amount;
    }
}
