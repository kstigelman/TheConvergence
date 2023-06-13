package com.stiggles.smp5.main;

import com.stiggles.smp5.listeners.CustomPlayer;
import com.stiggles.smp5.player.StigglesPlayer;
import org.bukkit.Statistic;
import org.bukkit.plugin.Plugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private HashMap<UUID, StigglesPlayer> players = new HashMap<>();

    public StigglesPlayer getStigglesPlayer(UUID uuid) {
        return players.get(uuid);
    }
    public void addStigglesPlayer(UUID uuid, StigglesPlayer player){
        players.put(uuid, player);
    }
    public void removeStigglesPlayer (UUID uuid) {
        players.remove(uuid);
    }
}