package com.stiggles.smp5.managers;

import com.stiggles.smp5.player.CoinBank;

import java.util.HashMap;
import java.util.UUID;

public class CoinBankManager {

    private final static HashMap<UUID, CoinBank> banks = new HashMap<>();

    public void onEnable () {
        //Load the CoinBank from database
    }
    public void onDisable () {
        //Save to database

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
