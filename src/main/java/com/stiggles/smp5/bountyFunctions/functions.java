package com.stiggles.smp5.bountyFunctions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class functions {

    public boolean isWorldLeader(Player player){

        return false;
    }

    /** Send a message from console to player.
     *
     * @param p Player to be messaged.
     * @param m The message to be sent
     */
    public void SendMessage (Player p, String m) {
        //Code to send a specific message to a player
    }

    /** Send a message to all players currently online.
     *
     * @param m The message to be sent
     */
    public void SendMessage (String m) {
        Bukkit.broadcastMessage(m);
    }

}
