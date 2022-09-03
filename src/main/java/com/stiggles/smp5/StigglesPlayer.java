package com.stiggles.smp5;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class StigglesPlayer //implements Player
{
    private Player player;
    private CoinBank coinBank;
    //private StigglesInventory inv;

    public StigglesPlayer () {
        player = null;
        coinBank = new CoinBank(null);
    }

    public StigglesPlayer (Player player) {
        this.player = player;
        coinBank = new CoinBank(this.player);
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





}
