package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.player.StigglesPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class ConnectionListener implements Listener {

    private SMP5 main;
    private StigglesPlayer customPlayer;

    public ConnectionListener (SMP5 main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {
        //main.packetListener.inject (e.getPlayer());
        Bukkit.getServer ().broadcastMessage("Injecting packets!");

        Player player = e.getPlayer();

        try {
            CustomPlayer playerData = new CustomPlayer(main, player);
            //main.getPlayerManager().addStigglesPlayer(player.getUniqueId(), playerData);

        } catch (SQLException ex) {
            //If we get an error about PlayerQuitEvent, we need to make sure that playerLeave () knows that the player
            //got kicked. We need to exit the function early.
            player.kickPlayer("The data that you have requested could not be gotten. Please contact administration.");
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerQuit (PlayerQuitEvent e) {
        //main.packetListener.stop (e.getPlayer());

        customPlayer = main.getPlayerManager().getStigglesPlayer(e.getPlayer().getUniqueId());

        if (customPlayer == null)
            return;


        Player p = e.getPlayer();
        String w = e.getPlayer().getWorld().getName();
        int x = Math.round((int) e.getPlayer().getLocation().getX());
        int y = Math.round((int) e.getPlayer().getLocation().getY());
        int z = Math.round((int) e.getPlayer().getLocation().getZ());
        int c = BankManager.getBalance(e.getPlayer());

        //customPlayer.setLogOffWorld(w);
        //customPlayer.setLogOffX(x);
        //customPlayer.setLogOffY(y);
        //customPlayer.setLogOffZ(z);
        //customPlayer.setCoins(c);

        main.getPlayerManager().removeStigglesPlayer(e.getPlayer().getUniqueId());
    }
}
