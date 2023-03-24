package com.stiggles.smp5.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;


public class StigglesPlayer
{

    private Player player;
    private CoinBank coinBank;
    //private StigglesInventory inv;
    private int bounty;
    private int killstreak;

    public StigglesPlayer () {
        player = null;
        coinBank = new CoinBank(null);
    }

    public StigglesPlayer (Player player) {
        this.player = player;
        coinBank = new CoinBank(this.player.getUniqueId ());
    }

    public Player getPlayer () {
        return player;
    }

    public CoinBank getCoinBank () {
        return coinBank;
    }


    public String getName () {
        return player.getName ();
    }



    public Location getLocation () {
        return player.getLocation ();
    }


    public int getBounty () {
        return bounty;
    }

    @Override
    public String toString () {
        return getName () + ": " +
                          "\n\tLocation: " + getLocation () +
                          "\n\tCoins: " + getCoinBank().getBalance ();
    }



}
