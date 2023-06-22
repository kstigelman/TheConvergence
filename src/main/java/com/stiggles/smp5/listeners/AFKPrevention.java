package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AFKPrevention implements Listener {

    Map<UUID, Integer> playerBlocksWalked = new HashMap<UUID, Integer>();
    Map<UUID, Integer> playerBlocksRan = new HashMap<UUID, Integer>();
    //Map<UUID, Integer> playerTimePlayed = new HashMap<UUID, Integer>();

    private static SMP5 main;
    public AFKPrevention (SMP5 main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        new BukkitRunnable() { public void run() {

            Player p = e.getPlayer();
            UUID uuid = p.getUniqueId();

            if (playerBlocksWalked.get(uuid) != null && playerBlocksRan.get(uuid) != null) {
                int playerWalkedBlocks = playerBlocksWalked.get(uuid);
                int playerRanBlocks = playerBlocksRan.get(uuid);

                if (playerWalkedBlocks <= p.getStatistic(Statistic.WALK_ONE_CM) &&
                        playerRanBlocks <= p.getStatistic(Statistic.SPRINT_ONE_CM)) {
                    p.kickPlayer(ChatColor.RED + "You've been kicked for being away for to long!");
                }

            } else {
                playerBlocksWalked.put(uuid, p.getStatistic(Statistic.WALK_ONE_CM));
                playerBlocksRan.put(uuid, p.getStatistic(Statistic.SPRINT_ONE_CM));
                //playerTimePlayed.put(uuid, p.getStatistic(Statistic.PLAY_ONE_MINUTE));

            }

        }}.runTaskTimer(main, 20*(60*32), 20*(60*30));
    }
}
